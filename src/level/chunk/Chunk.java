package level.chunk;

import java.io.Serializable;
import java.util.*;
import mods.basemod.LevBlock;
import utils.containers.pos.*;

public class Chunk implements Serializable {
 private final ChunkPos id;
 private final Map<BlockPos, LevBlock> map = new HashMap<>();

 public Chunk ( ChunkPos pos ) {
  this.id = pos;
  //gen();

 }

 public void heightmap () {
//  int mid = 0;
//  int tid = 0;
//  int sid = 0;
//  for ( int ix = 0 ; ix < 15 ; ix++ ) {
//   for ( int iy = 0 ; iy < 15 ; iy++ ) {
//    for ( int iz = 0 ; iz < 127 ; iz++ ) {
//     if ( blocks[ix][iy][iz] != null ) {
////      mid = blocks[ix][iy][iz].getId().getMid();
////      tid = blocks[ix][iy][iz].getId().getIid();
//
//      if ( blocks[ix][iy - 1][iz] == null ) {// 0
//       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 0, new TId(mid,
//                                                                       tid)));
//      }
//      if ( blocks[ix][iy + 1][iz] == null ) {// 1
//       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 1, new TId(mid,
//                                                                       tid)));
//      }
//      if ( blocks[ix][iy][iz - 1] == null ) {// 2
//       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 2, new TId(mid,
//                                                                       tid)));
//      }
//      if ( blocks[ix][iy][iz + 1] == null ) {// 3
//       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 3, new TId(mid,
//                                                                       tid)));
//      }
//      if ( blocks[ix - 1][iy][iz] == null ) {// 4
//       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 4, new TId(mid,
//                                                                       tid)));
//      }
//      if ( blocks[ix + 1][iy][iz] == null ) {// 5
//       Texrend.add(new RendTexCoord(new Vec3<>(ix, iy, iz), 5, new TId(mid,
//                                                                       tid)));
//      }
//     }
//    }
//   }
//  }
 }

 public void updHeightMap () {

 }

 public void render () {
//
 }

 public void redact ( BlockPos pos, LevBlock block ) {
  this.map.replace(pos, block);
 }

 public void redactObl ( BlockPos pos1, BlockPos pos2, LevBlock block ) {
  if ( pos1.compareTo(pos2) != 0 ) {

  } else {
   redact(pos1, block);
  }
 }

 public void gen () {
  for ( int x = 0 ; x < 16 ; x++ ) {
   for ( int y = 0 ; y < 16 ; y++ ) {
    for ( int z = 0 ; z < 255 ; z++ ) {
     if ( z < 100 ) {
      this.map.put(new BlockPos(x, y, z), new LevBlock());
     }
    }
   }
  }
 }

 public void tick () {
  //
 }

 public ChunkPos getId () {
  return id;
 }
}
