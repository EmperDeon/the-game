package level.chunk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ChunkContainer {
 private final ArrayList<Chunk> chs = new ArrayList();
 private final ChunkIds ids = new ChunkIds();
 private int last = 0;
 private final String dir;
 private int chpr;
 //private int[] free = new int [1];
 
 public ChunkContainer(String dir,int chpr){
  this.dir = dir;
  this.chpr = chpr;
 }
 
 public void gen(){
  
 }
 
 public void add(Chunk ch){
  if(!test(ch.idx,ch.idy)) {  
   chs.add(last+1,ch);
   ids.add(last+1, ch.idx, ch.idy);
   last+=1;
  } 
 }
 public void addAll(OctChunk ch){
  ArrayList<Chunk> c = ch.getAllCh();  
  for (Chunk c1 : c) {
  // if(!test(c1.idx,c1.idy))  {
    chs.add(c1);
    ids.add(last+1, c1.idx, c1.idy);
    last+=1;
 // }
  }
 }
 
 public void loadAll(){
 File  f= new File(this.dir+"rg/");
 File[] dr = f.listFiles();
 
 OctChunk ch1;
 for (File dr1 : dr) {
  try {
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(dr1));
   ch1 = ((OctChunk)serial.readObject());
   addAll(ch1);
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
//  loadAll();   
     
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
     r.add(new OctChunk(i1+"",i1,i1,ch));
     i++;
    }
 }
 
 public Chunk get(int i){
  return chs.get(i);
 }
 
 public Chunk get(int x,int y){
  return chs.get(ids.getIdC(x, y));
 }
}
//  77