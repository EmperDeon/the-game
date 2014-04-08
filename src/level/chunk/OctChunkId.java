package level.chunk;
public class OctChunkId {
 private final String name;   
 private final ChunkId[] ids;
 
 public OctChunkId(String name, ChunkId[] ch){
  this.ids = ch;   
  this.name = name;
 }
 
 public String test(int x,int y) {
  for(ChunkId id : ids)
   if(id.test(x, y))
    return this.name;
  
  return null;
 }
}
