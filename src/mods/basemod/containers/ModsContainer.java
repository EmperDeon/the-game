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
import main.IdMap;
import mods.basemod.IItem;
import mods.basemod.LevBlock;
import mods.basemod.interfaces.BaseMod;
import mods.basemod.interfaces.CoreMod;
import render.Tex;
import utils.Options;

public final class ModsContainer implements Serializable {

 transient final TreeMap<Mid , CoreMod> cmods;
 transient final TreeMap<Mid , BaseMod> mods;
 private final BlocksContainer bcont;
 private final IItemsContainer icont;
 private final Crafting ccont;
 private final IdMap idmap;

 private final ArrayList<Mid> init = new ArrayList<>();
 private boolean loaded = false;

 private final File file = new File(main.Main.mdir + "mods/modscontainer.mod");

 public ModsContainer () {
  cmods = new TreeMap<>();
  mods = new TreeMap<>();
  bcont = new BlocksContainer();
  icont = new IItemsContainer();
  ccont = new Crafting();
  idmap = new IdMap();
 }

 public void add ( Mid id , BaseMod b ) {
  mods.put(id , b);
 }

 public Tex getITex ( Mid id ) {
  return icont.getTex(id);
 }

 public Tex getBTex ( Mid id ) {
  return bcont.getTex(id);
 }

 public void test () {
  mods.values().stream().
          forEach(( m ) -> {
           main.Main.main.mmod.add(idmap.getMid(m.getId()));
           System.out.println("add " + idmap.getMid(m.getId()));
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
    Options opt = null;
    JarFile jar = new JarFile(f);
    Enumeration entries = jar.entries();
    while ( entries.hasMoreElements() ) {
     JarEntry entry = ( JarEntry ) entries.nextElement();
     if ( entry.getName().equals("props.opt") ) {
      try ( ObjectInputStream is = new ObjectInputStream(jar.getInputStream(
              entry)) ) {
       opt = ( Options ) is.readObject();
      }
     }
    }

    URLClassLoader classLoader = new URLClassLoader(new URL[]{f.toURI().toURL()});
    BaseMod b = ( BaseMod ) classLoader.loadClass(opt.get("main.class")).
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
   this.bcont.addAll(t.getBcont());
   this.ccont.addAll(t.getCcont());
   this.icont.addAll(t.getIcont());
   this.idmap.addAll(t.getIdmap());
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

 public boolean isLoaded () {
  return loaded;
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

 public void addId ( Mid k , String v ) {
  idmap.add(k , v , v , v);
 }

 public void addBlock ( MultiTex tex , Mid id ) {
  bcont.addBlock(new LevBlock(tex , id));
 }

 public void addItem ( MultiTex tex , Mid id ) {
  icont.addItem(new IItem(tex , id));
 }

 public BlocksContainer getBcont () {
  return bcont;
 }

 public IItemsContainer getIcont () {
  return icont;
 }

 public Crafting getCcont () {
  return ccont;
 }

 public IdMap getIdmap () {
  return idmap;
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

}
