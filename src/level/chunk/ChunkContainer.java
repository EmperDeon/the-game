package level.chunk;

import java.util.ArrayList;

public class ChunkContainer {
 private final ArrayList<Chunk> chs = new ArrayList();
 private final ChunkIds ids = new ChunkIds();
 private int[] free = new int [1];
 
 public ChunkContainer(){
  //
 }
 
 public void add(int i,Chunk ch){
  if(!test(ch.idx,ch.idy)) {  
   chs.add(i,ch);
   ids.add(i, ch.idx, ch.idy);
  } 
 }
 
 public boolean test(int x,int y){
  return ids.test(x,y);
 }
 
 public void clear(){
  chs.clear();
  ids.clear();
 }
 
 public void free(int x,int y){
  chs.remove(ids.getIdC(x, y));
  
 }
}
