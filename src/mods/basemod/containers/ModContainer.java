package mods.basemod.containers;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import mods.basemod.BaseMod;
import render.Tex;
import utils.Options;

public class ModContainer {

 private final TreeMap<Mid , BaseMod> cont;

 private final BlocksContainer bcont = new BlocksContainer();
 private final ItemsContainer icont = new ItemsContainer();

 private final ArrayList<Mid> init = new ArrayList<>();
 private boolean loaded = false;

 public ModContainer () {
  this.cont = new TreeMap<>();
 }

 public void add ( Mid id , BaseMod b ) {
  cont.put(id , b);
 }

 public Tex getITex ( Mid id ) {
  return icont.getTex(id);
 }

 public Tex getBTex ( Mid id ) {
  return bcont.getTex(id);
 }

 public void test () {
  cont.values().stream().
          forEach(( m ) -> {
           System.out.println(main.Main.IdMap.getMid(m.id));
          });
 }

 public void init () {
  cont.values().stream().forEach(( m ) -> {
   m.init();
  });
  test();
  postinit();
 }

 public void postinit () {
  init.clear();
  cont.values().stream().forEach(( m ) -> {
   m.postinit();
  });
  loaded = true;
 }

 public void destroy () {

 }

 public void loadDir () {
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
    Options opt = getPluginProps(f);

    URLClassLoader classLoader = new URLClassLoader(new URL[]{f.toURI().toURL()});
    BaseMod b = ( BaseMod ) classLoader.loadClass(opt.get("main.class")).
            newInstance();
    cont.put(b.id , b);
   } catch ( IOException | IllegalArgumentException | ClassNotFoundException |
             InstantiationException | IllegalAccessException e ) {
    System.out.println(e.toString());
   }
  }
  init();
 }

//Fast Save, Load
 public void load () {
  
 }

 public void save () {

 }

 private Options getPluginProps ( File file ) throws IOException ,
                                                     ClassNotFoundException {

  JarFile jar = new JarFile(file);
  Enumeration entries = jar.entries();
  while ( entries.hasMoreElements() ) {
   JarEntry entry = ( JarEntry ) entries.nextElement();
   if ( entry.getName().equals("props.opt") ) {
    try ( ObjectInputStream is = new ObjectInputStream(jar.getInputStream(entry)) ) {
     Options opt = ( Options ) is.readObject();
     return opt;
    }
   }
  }
  return null;
 }

 public boolean isLoaded () {
  return loaded;
 }

 public synchronized void initF ( Mid id ) {
  this.init.add(id);
  if ( init.size() == cont.size() ) {
   postinit();
  }
 }

 public synchronized void postinitF ( Mid id ) {
  this.init.add(id);
  if ( init.size() == cont.size() ) {
   loaded = true;
  }
 }
}
