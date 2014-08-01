package utils.vec;
public class Vec2 <T>{
 public T x;
 public T y;
 
 public Vec2(){x = null; y = null;}
 public Vec2(T x, T y){}
 public void setX(T x){ this.x = x; }
 public void setY(T y){ this.y = y; }
 public T getX(){ return x; }
 public T getY(){ return y;}
 
}
