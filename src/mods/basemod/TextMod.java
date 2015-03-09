package mods.basemod;

import java.io.*;
import java.net.*;
import mods.basemod.containers.*;
import mods.basemod.interfaces.BaseMod;
import utils.Unzipper;
import utils.containers.ids.Mid;
import static utils.containers.ids.Mid.Mid;
import utils.containers.json.JSONObject;

public class TextMod implements BaseMod {

 private final Mid id;
 private final Boolean props = false;
 private final JSONObject mod;
 private final JSONObject ibc;
 private final String cl;
 private final boolean isEmpty;

 public TextMod ( String file ) {
  Unzipper.unzipmod(file);
  mod = new JSONObject(main.Main.DIR + "tmp/" + file.substring(file.
     lastIndexOf("/") + 1, file.lastIndexOf(".mod")) + "/mod.json");

  isEmpty = mod.getBoolean("isEmpty");
  id = Mid(mod.getString("name"));
  // cl = mod.getString("class");
  cl = null;
  if ( isEmpty ) {
   ibc = null;
  } else {
   ibc = new JSONObject(main.Main.DIR + "tmp/" + file.substring(file.
      lastIndexOf("/") + 1, file.lastIndexOf(".mod")) + "/ibc.json");
  }

 }

 @Override
 public boolean isClass () {
  return false;
  // return !cl.isEmpty();
 }

 @Override
 public boolean isEmpty () {
  return isEmpty;
 }

 @Override
 public TextMod get ( File zip ) {
  if ( isClass() ) {
   try {
    URLClassLoader classLoader = new URLClassLoader(
       new URL[]{ zip.toURI().toURL() });
    TextMod b = (TextMod) classLoader.loadClass(cl).newInstance();
    return b;
   } catch ( IOException | IllegalArgumentException | ClassNotFoundException |
             InstantiationException | IllegalAccessException e ) {
    main.Main.LOG.addE(e);
   }
  }
  return this;
 }

 @Override
 public void init ( ModsContainer c ) {
  JSONObject t;
  if ( !isEmpty ) {
   for ( int i = 0 ; i < mod.getInt("Blocks") ; i++ ) {
    t = ibc.getJSONObject("Block" + i);
    c.put(new LevBlock(mod.getString("name"), t));
    main.Main.LOG.addI("Loaded block");
   }

   for ( int i = 0 ; i < mod.getInt("Items") ; i++ ) {
    t = ibc.getJSONObject("Item" + i);
    c.put(new IItem(mod.getString("name"), t));
    main.Main.LOG.addI("Loaded item");
   }

   for ( int i = 0 ; i < mod.getInt("Crafts") ; i++ ) {
    t = ibc.getJSONObject("Craft" + i);
    c.putCraft(t.getInt("Type"),
               t.getString("Grid"),
               t.getString("Elements")
    );
    main.Main.LOG.addI("Loaded craft");
   }
  }

  //put actions
  //Ex. c.addAction(id of Block/Item (Mid) , id of action(String) , () -> { action } (Action));  () -> {  } is a lambda expreesion
  //Or  c.addAction(Mid , action (String) , ( int act , boolean shift ) -> { action } (Action)); for multiaction with mouse
  c.initF(id);
 }

 @Override
 public void postinit ( ModsContainer c ) {

  c.postinitF(id);
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
