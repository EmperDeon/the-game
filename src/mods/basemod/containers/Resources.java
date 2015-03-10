package mods.basemod.containers;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import static main.Main.LOG;
import mods.basemod.*;
import mods.basemod.resources.*;
import utils.containers.ids.*;
import static utils.containers.ids.Rid.Rid;
import utils.containers.json.JSONObject;

public class Resources {
 private final TreeMap<Rid, Sound> sounds = new TreeMap<>();
 private final TreeMap<Rid, Model> models = new TreeMap<>();
 private final TreeMap<Rid, Resource> map = new TreeMap<>();
 private final List<Rid> list = new ArrayList<>();

 public Resources () {

  LOG.addI("Resource inited");
 }

 public synchronized Resource getResource ( Rid id ) {
  return map.get(id);
 }

 public synchronized void putResource ( Rid k, Resource v ) {
  this.map.put(k, v);

 }

 public synchronized void putAll ( String mid ) {
  String dirname = main.Main.DIR + "tmp/" + mid + "/res/";
  JSONObject t = new JSONObject(dirname + "map.json");
  t.getMap().keySet().stream().forEach(( s ) -> {
   this.map.put(Rid(s), Resource.getResource(Rid(s), t.getString(s)));
  });
 }

 public synchronized void putAll ( Resources res ) {
  this.map.putAll(res.map);
 }

 public void reScan () {

 }

 public boolean containsR ( Rid id, Resource.Type type, String url ) {
  return models.values().stream().anyMatch(( r ) -> (id.equals(r.getId()) && type.equals(r.getType()) && url.equals(r.getUrl())));
 }

 public boolean containsI ( Mid id, Resource.Type type, String rid ) {
  return list.stream().anyMatch(( r ) -> (id.equals(r.getId()) && type.equals(r.getType()) && rid.equals(r.getRid())));
 }

 public Model getModel ( Rid id, Resource.Type type, String url ) {
  for ( Model r : models.values() ) {
   if ( id.equals(r.getId()) && type.equals(r.getType()) && url.equals(r.getUrl()) ) {
    return r;
   }
  }
  return null;
 }

 public Sound getSound ( Rid id, Resource.Type type, String url ) {
  for ( Sound r : sounds.values() ) {
   if ( id.equals(r.getId()) && type.equals(r.getType()) && url.equals(r.getUrl()) ) {
    return r;
   }
  }
  return null;
 }
 
 public Rid getRid ( Mid id, Resource.Type type, String rid ) {
  for ( Rid r : list ) {
   if ( id.equals(r.getId()) && type.equals(r.getType()) && rid.equals(r.getRid()) ) {
    return r;
   }
  }
  return null;
 }

 public synchronized void load () {
  LOG.addI("Load started");
  try ( ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(
     new FileInputStream(main.Main.DIR + "res/map.res"))) ) {
   Map t = (Map) in.readObject();
   this.map.putAll(t);

  } catch ( Exception ex ) {

  }
  LOG.addI("Load ended");
 }

 public synchronized void save () {
  LOG.addI("Save started");
  try ( ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(
     new FileOutputStream(main.Main.DIR + "res/map.res"))) ) {
   out.writeObject(map);
   out.flush();
  } catch ( Exception ex ) {

  }
  LOG.addI("Save ended");
 }

}
