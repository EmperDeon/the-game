package level.chunk;

import java.io.Serializable;
import java.util.ArrayList;

public class OctChunkIds implements Serializable {
 private final ArrayList<OctChunkId> chids = new ArrayList();
    
 public OctChunkIds(String dir){// if not created
  
 }
 public OctChunkIds (ArrayList<OctChunk> chs){
  for(OctChunk ch : chs){
   chids.add(ch.getId());
  }    
 }
 
 public String search(int x,int y){
  for(OctChunkId id : chids) {
   String r = id.test(x, y);
   if(r != null) 
    return r;
  }  
  return null;
 }
}
