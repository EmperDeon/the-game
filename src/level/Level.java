package level;

import java.io.File;
import java.util.*;
import level.chunk.*;
import level.player.Player;
import utils.containers.json.JSONObject;

public class Level {
 private final Map<String, JSONObject> map;

 private final LevelGen gen = new LevelGen();
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

 public void destroy () {
  rch.destroy();
  options.save(dbfile);
  options.clear();
  clear();
 }

 public void init ( String name ) {
  if ( !"".equals(name) ) {
   destroy();
  }
  this.name = name;
  this.dir = main.Main.DIR + "saves/" + name + "/";
  this.dbfile = dir + "level.json";
  init();
 }

 public void init () {
  if ( checkJson() ) {
   loadJson();
   loadRegions();
  } else {
   clear();
  }
 }

 public boolean checkJson () {
  return new File(this.dbfile).canRead();
 }

 public void loadJson () {

 }
 private void loadRegions () {

 }

 private void updateDir () {
  File dir1 = new File(main.Main.DIR + "saves/");
  for(File f : dir1.listFiles()){
   this.map.put(f.getName(), new JSONObject(main.Main.DIR+"saves/"+f.getName()+"/level.db"));
  }
 }

 private static class LevelComparator implements Comparator {
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
