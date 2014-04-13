package level.chunk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import main.Main;
import utils.TermEx;
import utils.vec.Vec2f;

public final class ChunkContainer {
 
    
 private final ArrayList<Chunk> chs = new ArrayList();
 private final ChunkIds ids = new ChunkIds();
 private final OctChunkIds oids;
 private final ArrayList<ChunkId> red= new ArrayList();
 
 private int last = 0;
 private final String dir;//  game/save/name/

 //private int[] free = new int [1];
 
 public ChunkContainer(String dir) throws TermEx{
  this.dir = dir; 
  if(!new File(main.Main.mdir+"saves/World1/rg/").canRead())
   gen("World1",8);
  oids = new OctChunkIds(dir);
 }
 
 public void gen(String name, int chpr){
  ArrayList<OctChunk> oct = new ArrayList();   
  for(int x = -(chpr/8);x<=(chpr/8);x++)  
   for(int y = -(chpr/8);y<=(chpr/8);y++){
    oct.add(new OctChunk(name,x,y)); 
   }
  
  File f = new File(main.Main.mdir+"saves/"+name+"/rg/");
  f.mkdirs();
  
  for(OctChunk oc : oct){
   addAll(oc);   
   
   try {
    ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(oc.getD()));
    serial.writeObject(oc);
    serial.flush();
   }catch (IOException ex) {
    Main.err.add("OctChunkIds . Save()", ex);
    System.err.println(oc.getD());
   }
  }
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