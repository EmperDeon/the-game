package utils.vec;

import java.io.Serializable;

public class Vec5<T> implements Serializable {

 private T x;
 private T y;
 private T z;
 private T dx;
 private T dy;

 public Vec5() {
  this.x = null;
  this.y = null;
  this.z = null;
  this.dx = null;
  this.dy = null;
 }

 public Vec5(T x, T y, T z, T dx, T dy) {
  this.x = x;
  this.y = y;
  this.z = z;
  this.dx = dx;
  this.dy = dy;
 }

 public void sX(T x) {
  this.x = x;
 }

 public void sY(T y) {
  this.y = y;
 }

 public void sZ(T z) {
  this.z = z;
 }

 public void sDx(T dx) {
  this.dx = dx;
 }

 public void sDy(T dy) {
  this.dy = dy;
 }

 public T gX() {
  return this.x;
 }

 public T gY() {
  return this.y;
 }

 public T gZ() {
  return this.z;
 }

 public T gDx() {
  return this.dx;
 }

 public T gDy() {
  return this.dy;
 }
}
