package mods.basemod.containers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import static main.Main.LOG;
import mods.basemod.Resource;
import mods.basemod.Rid;
import utils.json.JSONObject;

public class Resources {

 private final TreeMap<Rid , Resource> map = new TreeMap<>();

 public Resources () {

  LOG.addI("Resource inited");
 }

 public synchronized Resource getResource ( Rid id ) {
  return map.get(id);
 }

 public synchronized void putResource ( Rid k , Resource v ) {
  this.map.put(k , v);

 }

 public synchronized void putAll ( String mid ) {
  String dirname = main.Main.mdir + "tmp/" + mid + "/res/";
  JSONObject t = new JSONObject(dirname + "map.json");
  t.getMap().keySet().stream().forEach(( s ) -> {
   this.map.put(new Rid(s) , Resource.getResource(new Rid(s) , t.getString(s)));
  });
 }

 public synchronized void putAll ( Resources res ) {
  this.map.putAll(res.map);
 }

 public void reScan () {

 }

 public synchronized void load () {
  LOG.addI("Load started");
  try ( ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(
          new FileInputStream(main.Main.mdir + "res/map.res"))) ) {
   Map t = ( Map ) in.readObject();
   this.map.putAll(t);

  } catch ( Exception ex ) {

  }
  LOG.addI("Load ended");
 }

 public synchronized void save () {
  LOG.addI("Save started");
  try ( ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(
          new FileOutputStream(main.Main.mdir + "res/map.res"))) ) {
   out.writeObject(map);
   out.flush();
  } catch ( Exception ex ) {

  }
  LOG.addI("Save ended");
 }

}
