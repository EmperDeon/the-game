package level;

import level.chunk.*;
import utils.containers.json.JSONObject;
import utils.exceptions.TermEx;

public class Level {
 private final LevelGen gen = new LevelGen();
//private final ChunkContainer lch;
 private final ChunkContainer rch;
 private String name;

 private JSONObject options;

 public Level () throws TermEx {
  this.name = "";
  this.rch = new ChunkContainer();
 }

}
