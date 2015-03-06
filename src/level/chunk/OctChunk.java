package level.chunk;

import java.io.Serializable;
import java.util.*;
import utils.containers.ids.OctChunkId;
import utils.containers.pos.*;

public class OctChunk implements Serializable {

 private final String dir;
 private final Map<ChunkPos, Chunk> map = new HashMap<>();
 private final String fl;
 private final OctChunkPos pos;

 public OctChunk ( String nm, int x, int y ) {
  this.dir = main.Main.DIR + "saves/" + nm + "/rg/";
  fl = "region" + x + "" + y + ".rg";

  pos = new OctChunkPos(x, y);

  for ( int ix = 0 ; ix <= 7 ; ix++ ) {
   for ( int iy = 0 ; iy <= 7 ; iy++ ) {
    for ( int iz = 0 ; iz <= 7 ; iz++ ) {
     map.put(new ChunkPos(pos.gX() * 8 + ix, pos.gY() * 8 + iy, iz), new Chunk(new ChunkPos(pos.gX() * 8 + ix, pos.gY() * 8 + iy, iz)));
    }
   }
  }
 }
 
 public void tick () {
  
 }

 public String getD () {
  return dir + fl;
 }

 public Chunk getCh ( ChunkPos pos) {
  return this.map.get(pos);
 }

 public Collection<Chunk> getAllCh () {
  return this.map.values();
 }

 public OctChunkId getId () {
  ChunkPos[] ids = new ChunkPos[64];
  int i = 0;
  for ( Chunk[] chs1 : chs ) {
   for ( Chunk ch : chs1 ) {
    ids[i] = new ChunkPos(ch);
    i++;
   }
  }
  return new OctChunkId("region" + this.x + "" + this.y + ".rg", ids);
 }

 public void replaceChunk ( int x, int y, Chunk ch ) {
  this.chs[x][y] = ch;
 }
}
