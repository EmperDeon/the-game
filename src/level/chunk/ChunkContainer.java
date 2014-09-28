package level.chunk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import main.Main;
import mods.basemod.LevBlock;
import utils.exceptions.TermEx;
import utils.vec.Vec3;

public final class ChunkContainer {
 private final HashMap<ChunkId, Chunk> chs = new HashMap();
 private final HashSet<ChunkId> rch= new HashSet<>();
 private final OctChunkIds oids;

 private final String dir;//  game/save/name/

 public ChunkContainer(String dir) throws TermEx{
  this.dir = dir; 
  
  if(!new File(main.Main.mdir+"saves/World1/rg").canRead())
   gen("World1",8);
  
  oids = new OctChunkIds(dir);
  
  load(new ChunkId(0,0),8);
  
 }
 
 public void gen(String name, int chpr){
  ArrayList<OctChunk> oct = new ArrayList<>();   
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
    Main.LOG.addE("OctChunkIds . Save()", ex);
    System.err.println(oc.getD());
   }
  }
 }
 
 public void add(Chunk ch){
   chs.put(ch.id, ch);
 }
 
 public void addAll(OctChunk och){
  ArrayList<Chunk> oc = och.getAllCh();  
  for (Chunk ch : oc) {
   add(ch);
  }
 }
 
 public void load(ChunkId pos,int chpr){ 
  int x1 = (pos.x - chpr)/8;
  int x2 = (pos.x + chpr)/8;
  int y1 = (pos.y - chpr)/8;
  int y2 = (pos.y + chpr)/8;

  for(int i1 = x1 ; i1<=x2 ; i1++)
   for(int i2 = y1 ; i2<=y2 ; i2++)
    loadOct("region"+i1+""+i2+".rg");   
 }

 public void redact(ChunkId cid, Vec3<Integer> bpos, LevBlock block){
  chs.get(cid).redact(bpos,block);
  rch.add(cid);
 }
 
 public void redactObl(ChunkId cid, Vec3<Integer> pos1, Vec3<Integer> pos2, LevBlock block){
  chs.get(cid).redactObl(pos1, pos2, block);
  rch.add(cid);
 }
 
 public void loadOct(String file){
  try{
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(this.dir+"rg/"+file));
   addAll((OctChunk)serial.readObject());
  }catch(IOException | ClassNotFoundException ex){
  Main.LOG.addE("OctChunkIds.loadOct()", ex);
  }
  System.out.println("Loaded: " + file + " : " +chs.size() );
 }
 
 public boolean test(int x,int y){
  return chs.containsKey(new ChunkId(x,y));
 }
 
 public void clear(){
  chs.clear();
 }
 
 public void free(int x,int y){
  chs.remove(new ChunkId(x,y));
 }
 
 public void save(){
  if(!rch.isEmpty()){
   for(ChunkId id : rch){
    String file = oids.search(id.x, id.y);
    
    OctChunk oc;
    try{
      ObjectInputStream serial = new ObjectInputStream(new FileInputStream(new File(this.dir+"rg/"+file)));
     oc = (OctChunk)serial.readObject();
    }catch(IOException | ClassNotFoundException ex){
     oc = null;
    }
    
    if(oc != null){
     oc.replaceChunk(id.x, id.y, chs.get(id));
     
     try {
      ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(file));
      serial.writeObject(oc);
      serial.flush();
      System.out.println("Saved:" + file);
     }catch (IOException ex) {
      Main.LOG.addE("ChunkContainer . Save()", ex);
     }
    }
   }
  }else{
   System.out.println("No changed");
  }
 }
 
 public Chunk get(int x,int y){
  return chs.get(new ChunkId(x,y));
 }
}