package level;

import java.io.File;
import level.chunk.*;
import utils.containers.ids.ChunkId;
import utils.containers.json.JSONObject;
import utils.exceptions.TermEx;

public class Level {
 private final int chpr = 8;
//private final ChunkContainer lch;
 private final ChunkContainer rch;
 private final String name;
 public Boolean loaded = false;
 public ChunkId pos = new ChunkId(0, 0, 0);

 private JSONObject options;

 public Level ( String name ) throws TermEx {
  File f = new File(main.Main.DIR + "saves/" + name + "/rg");
  if ( f.canRead() && f.listFiles() != null ) { // Created ?   
   this.name = name;
   this.rch = new ChunkContainer(main.Main.DIR + "saves/" + name + "/");
   load(main.Main.DIR + "saves/" + name + "/");
  } else {
   this.name = name;

//   options = new Options(main.Main.mdir + "saves/" + name + "/level.db");
//   options.add("name" , name);
//   options.add("lchunks" , null);
//   options.add("pos_x" , "0");
//   options.add("pos_y" , "0");
   this.rch = new ChunkContainer(main.Main.DIR + "saves/" + name + "/rg/");
  //this.lch = new ChunkContainer(main.Main.mdir + "saves/"+name+"/");

   //save();
   this.loaded = true;
  }
 }

 public void render () {

 }

 public final void load ( String dir ) {
  File f = new File(dir + "level.db");
  if ( f.canRead() ) {
//   options = new Options(dir + "level.db");
//
//   pos.x = options.getI("pos_x");
//   pos.y = options.getI("pos_y");
//  } else {
//   options = new Options(dir + "level.db");
//   options.add("name" , name);
  }

  //this.rch.load(new ChunkId(0,0),8);
  this.loaded = true;
 }

 public void save () {
//  options.save();

  rch.save();

 }

 /*
  * public void tick(){ for(int i=0;i<ch.size();i++) ch.get(i).tick(); * }
  */
 public Chunk get ( int x, int y ) {
  return rch.get(x, y);
 }

}
