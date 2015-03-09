package utils.containers.ids;

import java.io.Serializable;
import static main.Main.SERVER;
import utils.containers.vec.Vec3;

public class Mid implements Comparable, Serializable {

 protected final String mid;
 protected final String iid;
 protected final String sid;

 public static Mid Mid ( String m ) {
  if ( SERVER.getMids().contains(m, "", "") ) {
   return SERVER.getMids().get(m, "", "");
  } else {
   return new Mid(m);
  }
 }

 public static Mid Mid ( String m, String i ) {
  if ( SERVER.getMids().contains(m, i, "") ) {
   return SERVER.getMids().get(m, i, "");
  } else {
   return new Mid(m);
  }
 }

 public static Mid Mid ( String m, String i, String s ) {
  if ( SERVER.getMids().contains(m, i, s) ) {
   return SERVER.getMids().get(m, i, s);
  } else {
   return new Mid(m);
  }
 }

 public Mid ( Mid tid ) {
  this.mid = tid.mid;
  this.sid = tid.sid;
  this.iid = tid.iid;
 }

 protected Mid ( String m ) {
  this.mid = m;
  this.iid = "";
  this.sid = "";
 }

 protected Mid ( String m, String i ) {
  this.mid = m;
  this.iid = i;
  this.sid = "";
 }

 protected Mid ( String m, String i, String s ) {
  this.mid = m;
  this.sid = s;
  this.iid = i;
 }

 public Vec3<String> getIds () {
  return new Vec3<>(mid, sid, iid);
 }

 public String getMid () {
  return mid;
 }

 public String getIid () {
  return iid;
 }

 public String getSid () {
  return sid;
 }

 public boolean isM () {
  return (iid.equals("")) && (sid.equals(""));
 }

 public boolean isI () {
  return (sid.equals(""));
 }

 public boolean isS () {
  return !sid.equals("");
 }

 @Override
 public int compareTo ( Object m ) {
  Mid o = (Mid) m;
  Integer x, y, z;
  x = this.mid.compareTo(o.getMid());
  y = this.iid.compareTo(o.getIid());
  z = this.sid.compareTo(o.getSid());

  if ( x != 0 ) {
   return x;
  } else if ( y != 0 ) {
   return y;
  } else if ( z != 0 ) {
   return z;
  } else {
   return 0;
  }
 }

 @Override
 public String toString () {
  return "ID: " + mid + " . " + iid + " . " + sid;
 }
}
