package utils.containers.pos;

public class ChunkPos extends Position {
 public ChunkPos ( int x, int y, int z ) {
  super(x, y, z);
 }

 @Override
 public String toString () {
  return "Block: x = " + x + "; y = " + y + "; z = " + z + ";";
 }
}
