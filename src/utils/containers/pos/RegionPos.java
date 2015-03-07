package utils.containers.pos;

public class RegionPos extends Position {

 public RegionPos ( int x, int y ) {
  super(x, y, 0);
 }

 @Override
 public String toString () {
  return "OctChunk: x = " + x + "; y = " + y + "; z = " + z + ";";
 }
}
