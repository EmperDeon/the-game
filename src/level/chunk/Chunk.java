package level.chunk;

import java.io.Serializable;
import java.util.ArrayList;
import mods.basemod.LevBlock;
import render.*;
import utils.vec.Vec3;

public class Chunk implements Serializable {

 public LevBlock[][][] blocks;
 public int idx;
 public int idy;
 public ArrayList<RendTexCoord> Texrend;
 public ChunkId id;

 public Chunk ( int x, int y ) {
  this.idx = x;
  this.idy = y;
  this.id = new ChunkId(x, y);
  //gen();
  blocks = new LevBlock[16][16][256];
  //[16][16][128]
 }

 public void heightmap () {
  int mid = 0;
  int tid = 0;
  int sid = 0;
  for ( int ix = 0 ; ix < 15 ; ix++ ) {
   for ( int iy = 0 ; iy < 15 ; iy++ ) {
    for ( int iz = 0 ; iz < 127 ; iz++ ) {
     if ( blocks[ix][iy][iz] != null ) {
//      mid = blocks[ix][iy][iz].getId().getMid();
//      tid = blocks[ix][iy][iz].getId().getIid();

      if ( blocks[ix][iy - 1][iz] == null ) {// 0
       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 0, new TId(mid,
                                                                       tid)));
      }
      if ( blocks[ix][iy + 1][iz] == null ) {// 1
       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 1, new TId(mid,
                                                                       tid)));
      }
      if ( blocks[ix][iy][iz - 1] == null ) {// 2
       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 2, new TId(mid,
                                                                       tid)));
      }
      if ( blocks[ix][iy][iz + 1] == null ) {// 3
       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 3, new TId(mid,
                                                                       tid)));
      }
      if ( blocks[ix - 1][iy][iz] == null ) {// 4
       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 4, new TId(mid,
                                                                       tid)));
      }
      if ( blocks[ix + 1][iy][iz] == null ) {// 5
       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 5, new TId(mid,
                                                                       tid)));
      }
     }
    }
   }
  }
 }

 public void updheightm () {

 }

 public void Render () {
//
 }

 public void redact ( Vec3<Integer> pos, LevBlock block ) {
  this.blocks[pos.gX()][pos.gY()][pos.gZ()] = block;
 }

 public void redactObl ( Vec3<Integer> pos1, Vec3<Integer> pos2,
                         LevBlock block ) {
  this.blocks[pos1.gX()][pos1.gY()][pos1.gZ()] = block;
 }

 public void gen () {
  blocks = new LevBlock[16][16][256];
  for ( int x = 0 ; x < 16 ; x++ ) {
   for ( int y = 0 ; y < 16 ; y++ ) {
    for ( int z = 0 ; z < 255 ; z++ ) {
     if ( z < 100 ) {
      // blocks[x][y][z] = new LevBlock(null , new Mid(0 , 0 , 0));
     } else {
      blocks[x][y][z] = null;
     }
    }
   }
  }
 }

 public void tick () {
  //
 }
}
