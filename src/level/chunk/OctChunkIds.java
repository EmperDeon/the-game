package level.chunk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import main.Main1;
import utils.exceptions.TermEx;

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
    ObjectInputStream o = new ObjectInputStream(new FileInputStream(new File(this.file)));
    this.chids = ((ArrayList<OctChunkId>)o.readObject());
   }catch(IOException | ClassNotFoundException ex){
    main.Main1.LOG.addE("ChunkContainer . OctChunkIds . load()", ex);
    throw new TermEx("ChunkContainer . OctChunkIds . load() - error read OctChunk");
   }
  }else{   
   this.chids = new ArrayList<>();
   File[] oct = d.listFiles(); 

   for (File oc : oct) {
    System.out.println(oc.getAbsolutePath());
    try{
     ObjectInputStream fi = new ObjectInputStream(new FileInputStream(oc));
     OctChunk o = (OctChunk)fi.readObject();

    }catch(IOException |ClassNotFoundException ex){
     throw new TermEx("ChunkContainer . OctChunkIds . load() - error read rg/");
    }  
    
   }
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
  Main1.LOG.addE("OctChunkIds . Save()", ex);
 }
} 
 
public OctChunkIds(String dir) throws TermEx{// if not created
 this.file = dir + "OctChIds.db";
 this.dir  = dir + "rg/";
 load();
}
 
public OctChunkIds (ArrayList<OctChunk> chs,String dir){
 for(OctChunk ch : chs){
  chids.add(ch.getId());
 } 
 this.dir  = dir + "rg/";
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
/*

for (File oc : oct) {
    System.out.println(oc.getAbsolutePath());
    try{
     ObjectInputStream serial = new ObjectInputStream(new FileInputStream(oc));
     OctChunk o = (OctChunk)serial.readObject();
     OctChunkId i = o.getId();
     chids.add( i );
    }catch(IOException | ClassNotFoundException ex){
     throw new TermEx("ChunkContainer . OctChunkIds . load() - no ChIds");
    }  

*/