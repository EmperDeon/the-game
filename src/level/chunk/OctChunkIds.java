package level.chunk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import main.Main;
import utils.TermEx;

public final class OctChunkIds implements Serializable {
 private ArrayList<OctChunkId> chids;
 private final String dir;
 private final String file;   
 
public void load() throws TermEx{
 File d = new File(this.dir);
 if(d.canRead()){
  File f = new File(this.file);   
  if(f.canRead()){ 
   try{
    ObjectInputStream serial = new ObjectInputStream(new FileInputStream(new File(this.file)));
    this.chids = ((ArrayList<OctChunkId>)serial.readObject());
    System.out.println("Loaded");
   }catch(IOException | ClassNotFoundException ex){
    main.Main.err.add("ChunkContainer . OctChunkIds . load()", ex);
    throw new TermEx("ChunkContainer . OctChunkIds . load() - error read OctChunk");
   }
  }else{
   this.chids = new ArrayList();
   File[] oct = d.listFiles(); 

   for (File oct1 : oct) {
    try{
     ObjectInputStream serial = new ObjectInputStream(new FileInputStream(oct1));
     chids.add(  ((OctChunk)serial.readObject()).getId() );
    }catch(IOException | ClassNotFoundException ex){
     main.Main.err.add("ChunkContainer . OctChunkIds . load()", ex);
     throw new TermEx("ChunkContainer . OctChunkIds . load() - no ChIds");
    }  
   }
  System.out.println("Loaded"); 
  save();
  }
 }else{
  throw new TermEx("ChunkContainer . OctChunkIds . load() - no rg/");
 }
}
 
public void save(){
 try {
  ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(this.file));
  serial.writeObject(chids);
  serial.flush();
  System.out.println("Saved");
 }catch (IOException ex) {
  Main.err.add("OctChunkIds . Save()", ex);
 }
} 
 
public OctChunkIds(String dir) throws TermEx{// if not created
 this.dir  = dir;
 this.file = dir + "OctChIds.db";
 load();
}
 
public OctChunkIds (ArrayList<OctChunk> chs,String dir){
 for(OctChunk ch : chs){
  chids.add(ch.getId());
 } 
 this.dir  = dir;
 this.file = dir + "OctChIds.db";
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
