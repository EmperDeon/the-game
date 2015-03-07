package level.chunk;

import java.io.Serializable;
import java.util.*;
import utils.containers.pos.*;

public class Region implements Serializable {
 private final Map<ChunkPos, Chunk> map = new HashMap<>();
 private final OctChunkPos pos;

 public Region ( String nm, int x, int y ) {
//  this.dir = main.Main.DIR + "saves/" + nm + "/rg/";
//  fl = "region" + x + "" + y + ".rg";

  pos = new OctChunkPos(x, y);

  for ( int ix = 0 ; ix <= 7 ; ix++ ) {
   for ( int iy = 0 ; iy <= 7 ; iy++ ) {
    for ( int iz = 0 ; iz <= 7 ; iz++ ) {
     map.put(new ChunkPos(pos.gX() * 8 + ix, pos.gY() * 8 + iy, iz), new Chunk(new ChunkPos(pos.gX() * 8 + ix, pos.gY() * 8 + iy, iz)));
    }
   }
  }
 }

 
 
}
