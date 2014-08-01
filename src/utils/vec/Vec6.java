package utils.vec;
public class Vec6 <T>{
 public T x1;
 public T y1;
 public T z1;
 public T x2;
 public T y2;
 public T z2;
 
 public Vec6(){ this.x1 = null; this.y1 = null; this.z1 = null; this.x2 = null; this.y2 = null; this.z2 = null;}
 public Vec6(T x, T y, T z, T x2, T y2, T z2){ 
   this.x1 = x; this.y1 = y; this.z1 = z; this.x2 = x2; this.y2 = y2; this.z2 = z2; }
 
 public void setX(T x){ this.x1 = x; }
 public void setY(T y){ this.y1 = y; }
 public void setZ(T z){ this.z1 = z; }
 public void setDx(T dx){ this.x2 = dx; }
 public void setDy(T dy){ this.y2 = dy; }
 
 public T getX(){ return this.x1; }
 public T getY(){ return this.y1; }
 public T getZ(){ return this.z1; }
 public T getDx(){ return this.x2; }
 public T getDy(){ return this.y2; }
}
