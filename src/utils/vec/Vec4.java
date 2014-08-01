package utils.vec;
public class Vec4 <T>{
 public T x1;
 public T y1;
 public T x2;
 public T y2;
 
 public Vec4(){this.x1 = null; this.y1 = null; this.x2 = null; this.y2 = null;}
 public Vec4(T x1, T y1, T x2, T y2){this.x1 = x1; this.x2 = x2; this.y1 = y1; this. y2 = y2; }
 
 public void setX1(T x1){ this.x1 = x1; }
 public void setY1(T y1){ this.y1 = y1; }
 public void setX2(T x2){ this.x2 = x2; }
 public void setY2(T y2){ this.y2 = y2; } 
 
 public T getX1(){ return this.x1; }
 public T getY1(){ return this.y1; }
 public T getX2(){ return this.x2; }
 public T getY2(){ return this.y2; }
 
}
