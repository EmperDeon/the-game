package utils.vec;
public class Vec3 <T> {
 public T x;
 public T y;
 public T z;
 
 public Vec3(){x = null; y = null; z = null;}
 public Vec3(T x, T y, T z){}
 public void setX(T x){ this.x = x; }
 public void setY(T y){ this.y = y; }
 public void setZ(T z){ this.z = z; }
 public T getX(){ return x; }
 public T getY(){ return y; }
 public T getZ(){ return z; }
 
}
