package utils.containers.pos;

public class PlayerPos extends Position {
 
 public PlayerPos () {
  super(0, 0, 0);
 }

 public PlayerPos ( int x, int y, int z ) {
  super(x, y, z);
 }
 
 public void inc ( int x, int y, int z ) {
  this.x += x;
  this.y += y;
  this.z += z;
 }

 public void dec ( int x, int y, int z ) {
  this.x -= x;
  this.y -= y;
  this.z -= z;
 }
 
 public void init (PlayerPos t){
  this.x = t.x;
  this.y = t.y;
  this.z = t.z;
 }
}
