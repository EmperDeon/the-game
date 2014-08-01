package utils.vec;
public class Vec5 <T>{
 public T x;
 public T y;
 public T z;
 public T dx;
 public T dy;
 
 public Vec5(){ this.x = null; this.y = null; this.z = null; this.dx = null; this.dy = null; }
 public Vec5(T x, T y, T z, T dx, T dy){ this.x = x; this.y = y; this.z = z; this.dx = dx; this.dy = dy; }
 
 public void setX(T x){ this.x = x; }
 public void setY(T y){ this.y = y; }
 public void setZ(T z){ this.z = z; }
 public void setDx(T dx){ this.dx = dx; }
 public void setDy(T dy){ this.dy = dy; }
 
 public T getX(){ return this.x; }
 public T getY(){ return this.y; }
 public T getZ(){ return this.z; }
 public T getDx(){ return this.dx; }
 public T getDy(){ return this.dy; }
}
