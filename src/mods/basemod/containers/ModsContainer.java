package mods.basemod.containers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import mods.basemod.IItem;
import mods.basemod.LevBlock;
import mods.basemod.Model;
import mods.basemod.Speeds;
import mods.basemod.interfaces.Action;
import mods.basemod.interfaces.ActionU;
import mods.basemod.interfaces.Base;
import mods.basemod.interfaces.BaseMod;
import mods.basemod.interfaces.CoreMod;
import utils.Unzipper;
import utils.json.JSONObject;

public final class ModsContainer implements Serializable {

 transient final TreeMap<Mid , CoreMod> cmods;
 transient final TreeMap<Mid , BaseMod> mods;
 private final TreeMap<Mid , LevBlock> bcont;
 private final TreeMap<Mid , IItem> icont;
 private final Crafting ccont;
 private final ActMap actmap;
 private final ArrayList<Mid> init = new ArrayList<>();
 private boolean loaded = false;

 private final File file = new File(main.Main.mdir + "mods/modscontainer.mod");

 public ModsContainer () {
  cmods = new TreeMap<>();
  mods = new TreeMap<>();
  bcont = new TreeMap<>();
  icont = new TreeMap<>();
  ccont = new Crafting();
  actmap = new ActMap();
 }

 public void add ( Mid id , BaseMod b ) {
  mods.put(id , b);
 }

 public void addAction ( Mid id , String s , Action act ) {
  actmap.add(id , s , act);
 }

 public void addActionU ( Mid id , String s , ActionU act ) {
  actmap.add(id , s , act);
 }
//
// public Tex getITex ( Mid id ) {
//  return icont.getTex(id);
// }
//
// public Tex getBTex ( Mid id ) {
//  return bcont.getTex(id);
// }

 public void put ( Base v ) {
  if ( v instanceof BaseMod ) {
   mods.put(v.getId() , ( BaseMod ) v);
  } else if ( v instanceof CoreMod ) {
   cmods.put(v.getId() , ( CoreMod ) v);
  } else if ( v instanceof IItem ) {
   bcont.put(v.getId() , ( LevBlock ) v);
  } else if ( v instanceof LevBlock ) {
   icont.put(v.getId() , ( IItem ) v);
  } else {
   main.Main.LOG.addE("ModsContainer.put()" , new Exception("v is not a Base"));
  }
 }

 // -----------------------
 public void test () {
  mods.keySet().stream().
          forEach(( m ) -> {
           main.Main.main.mmod.add(m.getMid());
          });
 }

 public void init () {
  main.Main.LOG.addI("ModsContainer" , "Init Started");
  mods.values().stream().forEach(( m ) -> {
   m.init(this);
   init.add(m.getId());
  });
  main.Main.LOG.addI("ModsContainer" , "Init Ended");
  test();
 }

 public void reinit () {
  main.Main.LOG.addI("ModsContainer" , "Reinit Started");
  mods.values().stream().forEach(m -> {
   m.reinit(this);
   init.add(m.getId());
  });
  main.Main.LOG.addI("ModsContainer" , "Reinit Ended");
  test();
 }

 public void postinit () {
  main.Main.LOG.addI("ModsContainer" , "Postinit Started");
  init.clear();
  mods.values().stream().forEach(( m ) -> {
   m.postinit(this);
   init.add(m.getId());
  });
  main.Main.LOG.addI("ModsContainer" , "Postinit Ended");
 }

 public void destroy () {

 }

 public void load () {
  if ( file.exists() ) {
   fload();
  } else {
   loadDir(true);
  }
 }

 public void loadDir ( boolean isI ) {
  String dir;
  JSONObject opt, t;
  File[] s = new File(main.Main.mdir + "mods/").listFiles(pathname -> {
   try {
    if ( pathname.isFile() && pathname.getCanonicalPath().lastIndexOf(".zip") != -1 ) {
     return true;
    }
   } catch ( IOException ex ) {
    main.Main.LOG.addE("ModContainer.loadDir().filter" , ex);
   }
   return false;
  });

  for ( File f : s ) {
   dir = f.getAbsolutePath();
   dir = main.Main.mdir + "tmp/" + dir.substring(dir.lastIndexOf("/") + 1 , dir.
                                                 lastIndexOf(".zip")) + "/";
   Unzipper.unzipmod(f.getAbsolutePath());
   opt = new JSONObject(dir + "properties.mod");

   for ( int i = 0 ; i < opt.getInt("Blocks") ; i++ ) {
    t = opt.getJSONObject("Block" + i);
    bcont.put(new Mid(opt.getString("name") , t.getString("Iid") + i , t.
                      getString("Sid")) ,
              new LevBlock(
                      new Mid(opt.getString("name") , t.getString("Iid") , t.
                              getString("Sid")) ,
                      t.getInt("Durab") ,
                      new Model(t.getString("Model")) ,
                      new Speeds(t.getString("Speed")) ,
                      t.getString("Dict")
              ));
    main.Main.LOG.addI("mods.containers.ModsContainer.loadDir" , "Loaded block");
   }

   for ( int i = 0 ; i < opt.getInt("Items") ; i++ ) {
    t = opt.getJSONObject("Item" + i);
    icont.put(new Mid(opt.getString("name") , t.getString("Iid") + i , t.
                      getString("Sid")) , new IItem(
                      new Mid(opt.getString("name") , t.getString("Iid") , t.
                              getString("Sid")) ,
                      t.getInt("Durab") ,
                      new Model(t.getString("Model")) ,
                      t.getInt("Type") ,
                      new Speeds(t.getString("Speed"))
              ));
    main.Main.LOG.addI("mods.containers.ModsContainer.loadDir" , "Loaded item");
   }

   for ( int i = 0 ; i < opt.getInt("Crafts") ; i++ ) {
    t = opt.getJSONObject("Craft" + i);
    ccont.add(t.getInt("Type") ,
              t.getString("Grid") ,
              t.getString("Elements")
    );
    main.Main.LOG.addI("mods.containers.ModsContainer.loadDir" , "Loaded craft");
   }
   main.Main.LOG.addI("mods.containers.ModsContainer.loadDir" ,
                      "Loaded:" + bcont.size() + " " + icont.size() + " ");
  }

  if ( isI ) {
   init();
  }
 }

//Fast Save, Load
 public void fload () {
  loadDir(false);
  try ( ObjectInputStream o = new ObjectInputStream(new GZIPInputStream(
          new FileInputStream(file))) ) {
   ModsContainer t = ( ModsContainer ) o.readObject();
   this.bcont.putAll(t.bcont);
   this.ccont.addAll(t.ccont);
   this.icont.putAll(t.icont);
   this.actmap.addAll(t.getActmap());
  } catch ( Exception e ) {
   main.Main.LOG.addE("Containers.load()" , e);
   System.out.println(e.toString());
  }
  reinit();
  System.out.println("Loaded " + mods.size() + " mods");
 }

 public void fsave () {
  try ( ObjectOutputStream o = new ObjectOutputStream(new GZIPOutputStream(
          new FileOutputStream(file))) ) {
   o.writeObject(this);
   o.flush();
  } catch ( Exception e ) {
   main.Main.LOG.addE("ModsContainer.fsave()" , e);
  }
 }

 public synchronized void initF ( Mid id ) {
  this.init.add(id);
  if ( init.size() == mods.size() ) {
   postinit();
  }
 }

 public synchronized void postinitF ( Mid id ) {
  this.init.add(id);
  if ( init.size() == mods.size() ) {
   loaded = true;
  }
 }

 public boolean isLoaded () {
  return loaded;
 }

 public Crafting getCcont () {
  return ccont;
 }

 public TreeMap<Mid , CoreMod> getCmods () {
  return cmods;
 }

 public TreeMap<Mid , BaseMod> getMods () {
  return mods;
 }

 public ArrayList<Mid> getInit () {
  return init;
 }

 public File getFile () {
  return file;
 }

 public ActMap getActmap () {
  return actmap;
 }

 public TreeMap<Mid , LevBlock> getBcont () {
  return bcont;
 }

 public TreeMap<Mid , IItem> getIcont () {
  return icont;
 }

}
