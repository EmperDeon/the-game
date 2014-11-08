package mods.basemod.containers;

import java.io.Serializable;
import utils.vec.Vec3;

public class Mid implements Comparable , Serializable {

 private final String m;
 private final String i;
 private final String s;

 public Mid ( Mid tid ) {
  this.m = tid.m;
  this.s = tid.s;
  this.i = tid.i;
 }

 public Mid ( String m ) {
  this.m = m;
  this.i = "";
  this.s = "";
 }

 public Mid ( String m , String i ) {
  this.m = m;
  this.i = i;
  this.s = "";
 }

 public Mid ( String m , String i , String s ) {
  this.m = m;
  this.s = s;
  this.i = i;
 }

 public Vec3<String> getIds () {
  return new Vec3<>(m , s , i);
 }

 public String getMid () {
  return m;
 }

 public String getIid () {
  return i;
 }

 public String getSid () {
  return s;
 }

 public boolean isM () {
  return ( i.equals("") ) && ( s.equals("") );
 }

 public boolean isI () {
  return ( s.equals("") );
 }

 public boolean isS () {
  return !s.equals("");
 }

 @Override
 public int compareTo ( Object m ) {
  Mid o = ( Mid ) m;
  Integer x, y, z;
  x = this.m.compareTo(o.getMid());
  y = this.i.compareTo(o.getIid());
  z = this.s.compareTo(o.getSid());

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
}
