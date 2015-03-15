package level.chunk;

import java.io.Serializable;
import mods.basemod.LevBlock;
import utils.containers.pos.*;

public class Chunk implements Serializable {
 private final ChunkPos id;
 private LevBlock map[][][] = new LevBlock[16][16][256];

 public Chunk ( ChunkPos pos ) {
  this.id = pos;

 }

 public void heightmap () {
  HeightMap.heightMap(this);
 }

 public void updHeightMap () {
  HeightMap.updateMap(this);
 }

 public void tick () {
  //
 }

 public void tickW () {

 }

 public void render () {
//
 }

 public ChunkPos getId () {
  return id;
 }

 public void setBlock ( BlockPos pos, LevBlock block ) {
  map[pos.gX()][pos.gY()][pos.gZ()] = block;
 }

 public void setBlock ( int x, int y, int z, LevBlock block ) {
  map[x][y][z] = block;
 }

 public LevBlock[][][] getMap () {
  return map;
 }
}

// public void redact ( BlockPos pos, LevBlock block ) {
//  this.map.replace(pos, block);
// }
//
// public void redactObl ( BlockPos pos1, BlockPos pos2, LevBlock block ) {
//  if ( pos1.compareTo(pos2) != 0 ) {
//   
//  } else {
//   redact(pos1, block);
//  }
// }
