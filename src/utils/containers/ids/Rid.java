package utils.containers.ids;

import static main.Main.SERVER;
import mods.basemod.Resource;
import mods.basemod.Resource.Type;

public class Rid extends Mid implements Comparable {

 protected final String rid;
 private final Type type;

 public static Rid Rid (String s){
  return Rid(s.split(":"));
 }
 
 public static Rid Rid (String[] s){
  return Rid (new Mid(s[0], s[1], s[2]), Resource.getType(s[3]), s[4]);
 }
 
 public static Rid Rid (Mid mid, Type type , String id){
  if (SERVER.getResources().containsI(mid, type, id))
   return SERVER.getResources().getRid(mid, type, id);
  else
   return new Rid(mid, type, id);
 }
 
 protected Rid ( String s ) {
  this(s.split(":"));
 }

 protected Rid ( String[] s ) {
  super(s[0], s[1], s[2]);
  type = Resource.getType(s[3]);
  rid = s[4];
 }

 protected Rid ( Mid mid, Type type, String id ) {
  super(mid);
  this.rid = id;
  this.type = type;
 }

 public String getRid () {
  return rid;
 }
 
 public Mid getId(){
  return new Mid(mid, iid, sid);
 }

 @Override
 public int compareTo ( Object m ) {
  Rid o = (Rid) m;
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
  return "ID: " + type.name() + " . " + mid + " . " + iid + " . " + sid + " . " + rid;
 }

 public String toMap () {
  return mid + ":" + iid + ":" + sid + ":" + type.name() + ":" + rid;
 }

 public Type getType () {
  return type;
 }

}
