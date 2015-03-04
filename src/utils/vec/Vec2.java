package utils.vec;

import java.io.Serializable;

public class Vec2<T> implements Serializable {

 private T x;
 private T y;

 public Vec2 () {
  x = null;
  y = null;
 }

 public Vec2 ( T x, T y ) {
  this.x = x;
  this.y = y;
 }

 public void sX ( T x ) {
  this.x = x;
 }

 public void sY ( T y ) {
  this.y = y;
 }

 public T gX () {
  return x;
 }

 public T gY () {
  return y;
 }

}
