package mods.basemod.containers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import mods.basemod.IItem;
import mods.basemod.LevBlock;
import mods.basemod.interfaces.Action;
import mods.basemod.interfaces.ActionU;
import mods.basemod.interfaces.Base;
import mods.basemod.interfaces.BaseMod;
import mods.basemod.interfaces.CoreMod;
import utils.json.JSONObject;
import utils.vec.Vec3;

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

 public void put ( Vec3<String> k , Base v ) {
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
  File[] s = new File(main.Main.mdir + "mods/").listFiles(pathname -> {
   try {
    if ( pathname.isFile() && pathname.getCanonicalPath().lastIndexOf(".jar") != -1 ) {
     return true;
    }
   } catch ( IOException ex ) {
    main.Main.LOG.addE("ModContainer.loadDir().filter" , ex);
   }
   return false;
  });

  for ( File f : s ) {
   try {
    JSONObject opt = null;
    JarFile jar = new JarFile(f);
    Enumeration entries = jar.entries();
    while ( entries.hasMoreElements() ) {
     JarEntry entry = ( JarEntry ) entries.nextElement();
     if ( entry.getName().equals("props.json") ) {
      try ( ObjectInputStream is = new ObjectInputStream(jar.getInputStream(
              entry)) ) {
       opt = new JSONObject(is.readObject().toString());
      }
     }
    }

    URLClassLoader classLoader = new URLClassLoader(new URL[]{f.toURI().toURL()});
    BaseMod b = ( BaseMod ) classLoader.loadClass(opt.getString("main.class")).
            newInstance();
    mods.put(b.getId() , b);
   } catch ( IOException | IllegalArgumentException | ClassNotFoundException |
             InstantiationException | IllegalAccessException e ) {
    main.Main.LOG.addE("Containers.loadDir()" , e);
   }
  }
  if ( isI ) {
   init();
  }
 }

//Fast Save, Load
 public void fload () {
  loadDir(false);
  try ( ObjectInputStream o = new ObjectInputStream(new FileInputStream(file)) ) {
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
  try ( ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file)) ) {
   o.writeObject(this);
   o.flush();
  } catch ( Exception e ) {
   main.Main.LOG.addE("ModsContainer.fsave()" , e);
   System.out.println(e.toString());
  }
  System.out.println("Saved " + mods.size() + " mods");
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
