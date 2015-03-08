package level;

import level.chunk.*;
import level.player.Player;
import utils.containers.json.JSONObject;

public class Level {
 private final LevelGen gen = new LevelGen();
//private final ChunkContainer lch;
 private final ChunkContainer rch;
 private String name;

 private final Player player;
 private final JSONObject options;

 public Level () {
  this.name = "";
  this.rch = new ChunkContainer();
  this.options = new JSONObject();
  this.player = new Player();
 }

 public void init (String name){
  this.name = name;
  loadDb();
 }
 
 public void loadDb(){
  
 }
}
