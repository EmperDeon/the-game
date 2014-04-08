package level.chunk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import utils.TermEx;
import utils.vec.Vec2f;

public class ChunkContainer {
 private final ArrayList<Chunk> chs = new ArrayList();
 private final ChunkIds ids = new ChunkIds();
 private int last = 0;
 private final String dir;//  game/save/name/
 private final OctChunkIds oids;
 //private int[] free = new int [1];
 
 public ChunkContainer(String dir) throws TermEx{
 this.dir = dir; 
 
 File f = new File(dir+"OctChIds.db");   
 if(f.canRead()){ 
  try{
   OctChunkIds ch1;
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(new File(this.dir+"OctChIds.db")));
   oids = ((OctChunkIds)serial.readObject());
  }catch(IOException | ClassNotFoundException ex){
   main.Main.err.add("ChunkContainer . OctChunkIds . load()", ex);
   throw new TermEx();
  }
 }else{
  oids = new OctChunkIds(dir);
 }
  
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
 
 public void loadAll(Vec2f pos,int chpr){
  
 }
 
 public void loadOct(String file){
 try{
 OctChunk ch1;
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(new File(this.dir+"rg/"+file)));
   ch1 = ((OctChunk)serial.readObject());
   addAll(ch1);
 }catch(IOException | ClassNotFoundException ex){
  
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
  
 }
 
 public Chunk get(int i){
  return chs.get(i);
 }
 
 public Chunk get(int x,int y){
  return chs.get(ids.getIdC(x, y));
 }
}
//  77