package mods.basemod;

import mods.basemod.containers.Mid;

public class Rid extends Mid implements Comparable {

 protected final String rid;

 public Rid ( Mid mid , String id ) {
  super(mid);
  this.rid = id;
 }

 public Rid ( String m , String i , String s , String rid ) {
  super(m , i , s);
  this.rid = rid;
 }

 public String getRId () {
  return rid;
 }

 @Override
 public int compareTo ( Object m ) {
  Rid o = ( Rid ) m;
  Integer x, y, z, s;
  x = this.mid.compareTo(o.mid);
  y = this.iid.compareTo(o.iid);
  z = this.sid.compareTo(o.sid);
  s = this.rid.compareToIgnoreCase(o.rid);
  if ( x != 0 ) {
   return x;
  } else if ( y != 0 ) {
   return y;
  } else if ( z != 0 ) {
   return z;
  } else if ( s != 0 ) {
   return s;
  } else {
   return 0;
  }
 }

 @Override
 public String toString () {
  return "ID: " + mid + " . " + iid + " . " + sid + " . " + rid;
 }

}
