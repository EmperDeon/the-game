package utils.vec;

import java.io.Serializable;

public class Vec6<T> implements Serializable {

 private T x1;
 private T y1;
 private T z1;
 private T x2;
 private T y2;
 private T z2;

 public Vec6() {
  this.x1 = null;
  this.y1 = null;
  this.z1 = null;
  this.x2 = null;
  this.y2 = null;
  this.z2 = null;
 }

 public Vec6(T x, T y, T z, T x2, T y2, T z2) {
  this.x1 = x;
  this.y1 = y;
  this.z1 = z;
  this.x2 = x2;
  this.y2 = y2;
  this.z2 = z2;
 }

 public void sX1(T x1) {
  this.x1 = x1;
 }

 public void sY1(T y1) {
  this.y1 = y1;
 }

 public void sZ1(T z1) {
  this.z1 = z1;
 }

 public void sX2(T x2) {
  this.x2 = x2;
 }

 public void sY2(T y2) {
  this.y2 = y2;
 }

 public void sZ2(T z2) {
  this.z2 = z2;
 }

 public T gX1() {
  return this.x1;
 }

 public T gY1() {
  return this.y1;
 }

 public T gZ1() {
  return this.z1;
 }

 public T gX2() {
  return this.x2;
 }

 public T gY2() {
  return this.y2;
 }

 public T gZ2() {
  return this.z2;
 }
}
