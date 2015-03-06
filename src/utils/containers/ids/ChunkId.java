package utils.containers.ids;

import java.io.Serializable;
import java.util.Objects;

public class ChunkId implements Serializable, Comparable {
 private Integer x;
 private Integer y;
 private Integer z;

 public ChunkId ( int x, int y, int z ) {
  this.x = x;
  this.y = y;
  this.z = z;
 }

 @Override
 public int compareTo ( Object o ) {
  if ( o instanceof ChunkId ) {
   ChunkId t = (ChunkId) o;
   if ( x != null && y != null && z != null && t.x != null && t.y != null && t.z != null ) {
    int tx = t.x.compareTo(x);
    int ty = t.y.compareTo(y);
    int tz = t.z.compareTo(z);

    if ( tz != 0 ) {
     return tz;
    } else {
     if ( ty != 0 ) {
      return ty;
     } else {
      return tx;
     }
    }
   } else {
    main.Main.LOG.addE("One of the dimensions is null");
   }
  } else {
   main.Main.LOG.addE("Parameter is not ChunkId");
  }
  return 0;
 }

 @Override
 public int hashCode () {
  int r = 17;
  r = 37 * r + (x != null ? x.hashCode() * 3 : 0);
  r = 37 * r + (y != null ? y.hashCode() * 3 : 0);
  r = 37 * r + (z != null ? z.hashCode() * 3 : 0);
  return r;
 }

 @Override
 public boolean equals ( Object obj ) {
  if ( obj == null ) {
   return false;
  }
  if ( getClass() != obj.getClass() ) {
   return false;
  }
  ChunkId t = (ChunkId) obj;
  return Objects.equals(t.x, x) && Objects.equals(t.y, y) && Objects.equals(t.z, z);
 }

 @Override
 public String toString () {
  return "Chunk id: x=" + x + "; y=" + y + "; z=" + z + ";";
 }

 public Integer gX () {
  return x;
 }

 public Integer gY () {
  return y;
 }

 public Integer gZ () {
  return z;
 }

 public void sX ( Integer x ) {
  this.x = x;
 }

 public void sY ( Integer y ) {
  this.y = y;
 }

 public void sZ ( Integer z ) {
  this.z = z;
 }
}
