package utils.containers.vec;

import java.io.Serializable;

public class Vec3<T> implements Serializable {

 private T x;
 private T y;
 private T z;

 public Vec3 () {
  x = null;
  y = null;
  z = null;
 }

 public Vec3 ( T x, T y, T z ) {
  this.x = x;
  this.y = y;
  this.z = z;
 }

 public void sX ( T x ) {
  this.x = x;
 }

 public void sY ( T y ) {
  this.y = y;
 }

 public void sZ ( T z ) {
  this.z = z;
 }

 public T gX () {
  return x;
 }

 public T gY () {
  return y;
 }

 public T gZ () {
  return z;
 }

}
