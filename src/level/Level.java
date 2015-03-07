package level;

import level.chunk.*;
import utils.containers.json.JSONObject;

public class Level {
 private static final LevelGen gen = new LevelGen();
//private final ChunkContainer lch;
 private final ChunkContainer rch;
 private String name;

 private JSONObject options;

 public Level () {
  this.name = "";
  this.rch = new ChunkContainer();
 }

}
