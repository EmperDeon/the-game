package utils.vec;

import java.io.Serializable;
import java.util.Objects;

public class Vec4<T> implements Serializable {

 private T x1;
 private T y1;
 private T x2;
 private T y2;

 public Vec4 () {
  this.x1 = null;
  this.y1 = null;
  this.x2 = null;
  this.y2 = null;
 }

 public Vec4 ( T x1 , T y1 , T x2 , T y2 ) {
  this.x1 = x1;
  this.x2 = x2;
  this.y1 = y1;
  this.y2 = y2;
 }

 public void sX1 ( T x1 ) {
  this.x1 = x1;
 }

 public void sY1 ( T y1 ) {
  this.y1 = y1;
 }

 public void sX2 ( T x2 ) {
  this.x2 = x2;
 }

 public void sY2 ( T y2 ) {
  this.y2 = y2;
 }

 public T gX1 () {
  return this.x1;
 }

 public T gY1 () {
  return this.y1;
 }

 public T gX2 () {
  return this.x2;
 }

 public T gY2 () {
  return this.y2;
 }

 @Override
 public int hashCode () {
  return x1.hashCode() + x2.hashCode() + y1.hashCode() + y2.hashCode();
 }

 @Override
 public boolean equals ( Object obj ) {
  if ( obj == null ) {
   return false;
  }
  if ( getClass() != obj.getClass() ) {
   return false;
  }
  final Vec4<?> other = ( Vec4<?> ) obj;
  if ( !Objects.equals(this.x1 , other.x1) ) {
   return false;
  }
  if ( !Objects.equals(this.y1 , other.y1) ) {
   return false;
  }
  if ( !Objects.equals(this.x2 , other.x2) ) {
   return false;
  }
  if ( !Objects.equals(this.y2 , other.y2) ) {
   return false;
  }
  return true;
 }
}
