package utils.containers.pos;
import level.chunk.Chunk;

public class ChunkPos extends Position {
 
 public ChunkPos ( Chunk c ) {
  super(c.getId().x, c.getId().y, c.getId().z);
 }

 public ChunkPos ( int x, int y, int z ) {
  super(x, y, z);
 }

 @Override
 public String toString () {
  return "Block: x = " + x + "; y = " + y + "; z = " + z + ";";
 }
}
