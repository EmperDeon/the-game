package level.chunk;

public class ChunkId {
 public Integer x;
 public Integer y;
 
 public ChunkId(int x,int y){
  this.x=x;
  this.y=y;
 }
 
 public boolean test(int x,int y){
  return this.x==x && this.y==y;
 }
}
