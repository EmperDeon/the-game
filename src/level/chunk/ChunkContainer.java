package level.chunk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ChunkContainer {
 private final ArrayList<Chunk> chs = new ArrayList();
 private final ChunkIds ids = new ChunkIds();
 //private int[] free = new int [1];
 
 public ChunkContainer(){
  //
 }
 
 public void add(int i,Chunk ch){
  if(!test(ch.idx,ch.idy)) {  
   chs.add(i,ch);
   ids.add(i, ch.idx, ch.idy);
  } 
 }
 public void addAll(OctChunk ch){
  ArrayList<Chunk> c = ch.getAllCh();  
  for (Chunk c1 : c) {
   if(!test(c1.idx,c1.idy))  
    chs.add(c1);
  }
 }
 
 public void loadAll(String dir){
 File  f= new File(dir+"rg/");
 File[] dr = f.listFiles();
 
ArrayList<OctChunk> chs = new ArrayList();
OctChunk ch ;
 for (File dr1 : dr) {
  try {
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(dr1));
   ch = ((OctChunk)serial.readObject());
   addAll(ch);
  }catch (IOException | ClassNotFoundException ex) {
   //err.add("Level.load()",ex);
  } 
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
  ids.free(x,y);
 }
 
 public void save(){
  ArrayList<OctChunk> r = new ArrayList();
  int i = chs.size();
    for(int i1 = 0;i1<i/64;i1++){
     Chunk[][] ch = new Chunk[8][8];   
     int i2 = 0;
     for(int cx = 0;cx<8;cx++)
      for(int cy = 0;cy<8;cy++){ 
       
       ch[cx][cy]= chs.get(i2);
       i++;
      }
     r.add(new OctChunk(i1+""+i1,i1,i1,ch));
     i++;
    }
 }
}
//  77