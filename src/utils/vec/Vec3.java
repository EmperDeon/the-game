package utils.vec;
public class Vec3 <T> {
 private T x;
 private T y;
 private T z;
 
 public Vec3(){x = null; y = null; z = null;}
 public Vec3(T x, T y, T z){}
 
 public void sX(T x){ this.x = x; }
 public void sY(T y){ this.y = y; }
 public void sZ(T z){ this.z = z; }
 
 public T gX(){ return x; }
 public T gY(){ return y; }
 public T gZ(){ return z; }
 
}
