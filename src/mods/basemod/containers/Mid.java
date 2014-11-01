package mods.basemod.containers;

import java.io.Serializable;
import java.util.Objects;
import utils.vec.Vec3;

public class Mid implements Comparable , Serializable {

 private final Vec3<Integer> id;

 public Mid ( Mid tid ) {
  this.id = tid.getI();
 }

 public Mid ( Vec3<Integer> id ) {
  this.id = id;
 }

 public Mid ( Integer mid , Integer iid , Integer sid ) {
  this.id = new Vec3<>();
  this.id.sX(mid);
  this.id.sY(iid);
  this.id.sZ(sid);
 }

 public Vec3<Integer> getIds () {
  return id;
 }

 public Integer getMid () {
  return id.gX();
 }

 public Integer getIid () {
  return id.gY();
 }

 public Integer getSid () {
  return id.gZ();
 }

 public Vec3<Integer> getI () {
  return id;
 }

 @Override
 public int compareTo ( Object m ) {
  Mid o = ( Mid ) m;
  Integer x, y, z;
  x = comp(id.gX() , o.getMid());
  y = comp(id.gY() , o.getIid());
  z = comp(id.gZ() , o.getSid());

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

 private Integer comp ( Integer x , Integer y ) {
  if ( Objects.equals(x , y) ) {
   return 0;
  } else if ( x > y ) {
   return 1;
  } else if ( x < y ) {
   return -1;
  }

  main.Main.LOG.addE("Mid.comp()" , new Exception());
  return 0;
 }
}
