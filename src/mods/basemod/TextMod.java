package mods.basemod;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import mods.basemod.containers.Mid;
import mods.basemod.containers.ModsContainer;
import mods.basemod.interfaces.BaseMod;
import utils.Unzipper;
import utils.json.JSONObject;

public class TextMod implements BaseMod {

 private final Mid id;
 private final Boolean props = false;
 private final JSONObject mod;
 private final JSONObject ibc;
 private final String cl;
 private final boolean isEmpty;

 public TextMod ( String file ) {
  Unzipper.unzipmod(file);
  mod = new JSONObject(main.Main.mdir + "tmp/" + file.substring(file.
          lastIndexOf("/") + 1 , file.lastIndexOf(".mod")) + "/mod.json");
  isEmpty = mod.getBoolean("isEmpty");
  id = new Mid(mod.getString("name"));
  cl = mod.getString("class");
  if ( !isEmpty ) {
   ibc = null;
  } else {
   ibc = new JSONObject(main.Main.mdir + "tmp/" + file.substring(file.
           lastIndexOf("/") + 1 , file.lastIndexOf(".mod")) + "/ibc.json");
  }

 }

 @Override
 public boolean isClass () {
  return !cl.isEmpty();
 }

 @Override
 public boolean isEmpty () {
  return isEmpty;
 }

 @Override
 public TextMod get ( File zip ) {
  try {
   URLClassLoader classLoader = new URLClassLoader(
           new URL[]{zip.toURI().toURL()});
   TextMod b = ( TextMod ) classLoader.loadClass(cl).newInstance();
   return b;
  } catch ( IOException | IllegalArgumentException | ClassNotFoundException |
            InstantiationException | IllegalAccessException e ) {
   main.Main.LOG.addE("Containers.loadDir()" , e);
  }
  return this;
 }

 @Override
 public void init ( ModsContainer c ) {
  JSONObject t;
  if ( !isEmpty ) {
   for ( int i = 0 ; i < ibc.getInt("Blocks") ; i++ ) {
    t = ibc.getJSONObject("Block" + i);
    c.put(new LevBlock(ibc.getString("name") , t));
    main.Main.LOG.addI("mods.containers.ModsContainer.loadDir" , "Loaded block");
   }

   for ( int i = 0 ; i < ibc.getInt("Items") ; i++ ) {
    t = ibc.getJSONObject("Item" + i);
    c.put(new IItem(ibc.getString("name") , t));
    main.Main.LOG.addI("mods.containers.ModsContainer.loadDir" , "Loaded item");
   }

   for ( int i = 0 ; i < ibc.getInt("Crafts") ; i++ ) {
    t = ibc.getJSONObject("Craft" + i);
    c.putCraft(t.getInt("Type") ,
               t.getString("Grid") ,
               t.getString("Elements")
    );
    main.Main.LOG.addI("mods.containers.ModsContainer.loadDir" , "Loaded craft");
   }
  }

  //put actions
  //Ex. c.addAction(id of Block/Item (Mid) , id of action(String) , () -> { action } (Action));  () -> {  } is a lambda expreesion
  //Or  c.addAction(Mid , action (String) , ( int act , boolean shift ) -> { action } (Action)); for multiaction with mouse
 }

 @Override
 public void postinit ( ModsContainer c ) {

 }

 @Override
 public Mid getId () {
  return this.id;
 }

 @Override
 public boolean isProps () {
  return false;
 }

 @Override
 public void reinit ( ModsContainer c ) {

 }
}
