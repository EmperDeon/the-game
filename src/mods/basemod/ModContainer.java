package mods.basemod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import render.Tex;
import utils.TId;

public class ModContainer {

 private final ArrayList<BaseMod> cont;
 private boolean loaded = false;

 public ModContainer () {
  this.cont = new ArrayList<>();
 }

 public void add ( BaseMod b ) {
  cont.add(b);
 }

 public Tex getTex ( TId id ) {
  return cont.get(id.getMid()).getITex(new Mid(0 , 0 , 0));
 }

 public void test () {
  
 }

 public void init () {

  postinit();
 }

 public void postinit () {

  loaded = true;
 }

 public void destroy () {

 }

 public void loadDir () {
  File[] s = new File(main.Main.mdir + "mods/").listFiles(( File pathname ) -> {
   try {
    if ( pathname.isFile() && pathname.getCanonicalPath().lastIndexOf(".jar") != -1 ) {
     return true;
    }
   } catch ( IOException ex ) {
    main.Main.ERR_LOG.addE("ModContainer.loadDir().filter" , ex);
   }
   return false;
  });

  for ( File f : s ) {
   try {
    Properties props = getPluginProps(f);
    if ( props == null ) {
     main.Main.ERR_LOG.addE("ModContainer.loadDir().no file" , new Exception());
    }

    String pluginClassName = props.getProperty("main.class");
    if ( pluginClassName == null || pluginClassName.length() == 0 ) {
     main.Main.ERR_LOG.addE("ModContainer.loadDir().no class" , new Exception());
    }

    URLClassLoader classLoader = new URLClassLoader(new URL[]{f.toURI().toURL()});
    cont.add(( BaseMod ) classLoader.loadClass(pluginClassName).newInstance());
   } catch ( IOException | IllegalArgumentException | ClassNotFoundException |
             InstantiationException | IllegalAccessException e ) {
   }
  }
  init();
 }

//Fast Save, Load
 public void load () {

 }

 public void save () {

 }

 private static Properties getPluginProps ( File file ) throws IOException {
  Properties result = null;
  JarFile jar = new JarFile(file);
  Enumeration entries = jar.entries();
  while ( entries.hasMoreElements() ) {
   JarEntry entry = ( JarEntry ) entries.nextElement();
   if ( entry.getName().equals("plugin.properties") ) {
    try ( InputStream is = jar.getInputStream(entry) ) {
     result = new Properties();
     result.load(is);
    }
   }
  }
  return result;
 }

 public boolean isLoaded () {
  return loaded;
 }
}
