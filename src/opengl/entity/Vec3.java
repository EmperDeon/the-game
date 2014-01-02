 package opengl.entity;
 
 public class Vec3
 {
public float x;
public float y;
public float z;
 
public Vec3(float x, float y, float z)
{
  this.x = x;
  this.y = y;
  this.z = z;
}
 
public Vec3 interpolateTo(Vec3 t, float p)
{
  float xt = this.x + (t.x - this.x) * p;
  float yt = this.y + (t.y - this.y) * p;
  float zt = this.z + (t.z - this.z) * p;
 
  return new Vec3(xt, yt, zt);
}
 
public void set(float x, float y, float z)
{
  this.x = x;
  this.y = y;
  this.z = z;
}
 }

/* Location:           C:\Users\Илья\AppData\Roaming\.minecraft\versions\c0.0.11a\c0.0.11a.jar
 * Qualified Name:     minecraft.character.Vec3
 * JD-Core Version:    0.6.2
 */