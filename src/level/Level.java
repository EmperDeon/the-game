package level;

import java.io.File;
import java.util.*;
import level.chunk.*;
import level.player.Player;
import static main.Main.LOG;
import utils.containers.json.JSONObject;

public class Level {
 private final Map<String, JSONObject> map;


//private final ChunkContainer lch;
 protected final ChunkContainer rch;
 protected String name = "";
 protected String dir = "";
 protected String dbfile = "";
 protected final Player player;
 protected final JSONObject options;

 public Level () {
  this.rch = new ChunkContainer();
  this.options = new JSONObject();
  this.player = new Player();
  this.map = new TreeMap<>(new LevelComparator());
  updateDir();
 }

 public void clear () {
  name = "";
  dbfile = "";
  dir = "";
 }

 public void create ( String name ) {
  this.name = name;
  this.dir = main.Main.DIR + "saves/" + name + "/";
  this.dbfile = dir + "level.json";
  new File(dir).mkdirs();
  
  options.put("name", name);
  options.put("last", new Date().getTime());
  rch.create(dir);
 }
 
 public void destroy () {
  rch.destroy(dir);
  options.save(dbfile);
  options.clear();
  clear();
 }

 public synchronized void load ( String name ) {
  if ( !"".equals(name) ) {
   destroy();
  }
  this.name = name;
  this.dir = main.Main.DIR + "saves/" + name + "/";
  this.dbfile = dir + "level.json";
  init();
 }

 public void init () {
  LOG.addD("Started init level " + name);
  if ( new File(dbfile).canRead() ) {
   this.options.load(map.get(name));
   this.rch.load(dir);

   LOG.addD("Ended init level " + name);
  } else {
   LOG.addE("Level " + name + " does not have level.json");
   clear();
  }
 }

 public Collection<JSONObject> getMap () {
  return this.map.values();
 }

 private void updateDir () {
  File dir1 = new File(main.Main.DIR + "saves/");
  for ( File f : dir1.listFiles() ) {
   if ( new File(main.Main.DIR + "saves/" + f.getName() + "/level.db").canRead() ) {
    this.map.put(f.getName(), new JSONObject(main.Main.DIR + "saves/" + f.getName() + "/level.db"));
   } else {
    main.Main.LOG.addE("Level " + f.getName() + " does not have level.json");
   }
  }
 }

 private final class LevelComparator implements Comparator {
  @Override
  public int compare ( Object o1, Object o2 ) {
   if ( o1 instanceof JSONObject && o2 instanceof JSONObject ) {
    JSONObject t1 = (JSONObject) o1;
    JSONObject t2 = (JSONObject) o2;
    return Long.compare(t2.getLong("last"), t1.getLong("last"));
   }
   return 0;
  }
 }

}
