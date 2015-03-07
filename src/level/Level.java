package level;

import java.io.File;
import level.chunk.*;
import utils.containers.json.JSONObject;
import utils.containers.pos.ChunkPos;
import utils.exceptions.TermEx;

public class Level {
 private final int chpr = 8;
//private final ChunkContainer lch;
 private final ChunkContainer rch;
 private String name;
 private Boolean loaded = false;
 public ChunkPos pos = new ChunkPos(0, 0, 0);

 private JSONObject options;

 public Level () throws TermEx {
  this.name = "";
  this.rch = new ChunkContainer();
 }

 public void render () {

 }

 public final void load ( String name ) {
  File f = new File(main.Main.DIR + "saves/" + name + "/rg");
  if ( f.canRead() && f.listFiles() != null ) { // Created ?   
   this.name = name;
   this.rch = new ChunkContainer(main.Main.DIR + "saves/" + name + "/");
   load(main.Main.DIR + "saves/" + name + "/");
  } else {
   this.name = name;

   this.rch = new ChunkContainer(main.Main.DIR + "saves/" + name + "/rg/");
  //this.lch = new ChunkContainer(main.Main.mdir + "saves/"+name+"/");

   //save();
   this.loaded = true;
  }
 }

 public void save () {
//  options.save();

  rch.save();

 }

 /*
  * public void tick(){ for(int i=0;i<ch.size();i++) ch.get(i).tick(); * }
  */
 public Chunk get ( ChunkPos pos ) {
  return rch.get(pos);
 }

}
