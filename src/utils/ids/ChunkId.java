package utils.ids;

import java.io.Serializable;

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
  if ( x != null && y != null && z != null ) {
   if ( o instanceof ChunkId ) {
    ChunkId t = (ChunkId) o;
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
    throw new ClassCastException();
   }
  } else {
   throw new NullPointerException();
  }
 }

 @Override
 public int hashCode () {
  return (x != null ? x.hashCode() * 3 : 0) + (y != null ? y.hashCode() * 5 : 0) + (z != null ? z.hashCode() * 7 : 0);
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
  return t.x == x && t.y == y && t.z == z;
 }

 @Override
 public String toString () {
  return "Chunk id: x=" + x + "; y=" + y + "; z=" + z + ";";
 }

 public Integer getX () {
  return x;
 }
 public Integer getY () {
  return y;
 }
 public Integer getZ () {
  return z;
 }
 public void setX ( Integer x ) {
  this.x = x;
 }
 public void setY ( Integer y ) {
  this.y = y;
 }
 public void setZ ( Integer z ) {
  this.z = z;
 }
}
