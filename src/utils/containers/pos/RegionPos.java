package utils.containers.pos;
import java.util.*;

public class RegionPos extends Position {
 protected final Set<ChunkPos> set = new TreeSet<>();
 public final static RegionPos nullP = new RegionPos(0, 0, -1);

 private RegionPos ( int x, int y, int z ) {
  super(x, y, z);
 }

 public RegionPos ( int x, int y ) {
  super(x, y, 0);
 }

 public void addChunk ( ChunkPos pos ) {
  this.set.add(pos);
 }

 public boolean isContains ( ChunkPos pos ) {
  return this.set.contains(pos);
 }

 public boolean isNull () {
  return z.equals(-1);
 }

 @Override
 public String toString () {
  return "OctChunk: x = " + x + "; y = " + y + "; z = " + z + ";";
 }
}
