package level.chunk;

import java.io.*;
import java.util.ArrayList;
import main.Main;
import utils.containers.ids.OctChunkId;
import utils.containers.pos.ChunkPos;

public final class OctChunkIds implements Serializable {

 private ArrayList<OctChunkId> chids;
 private String dir;
 private String file;

 public void load ( String dir ) {
  this.file = dir + "OctChIds.db";
  this.dir = dir + "rg/";
  load();
 }

 public void load (){
  File d = new File(this.dir);
  if ( d.canRead() ) {
   File f = new File(this.file);
   if ( f.canRead() ) {
    try {
     ObjectInputStream o = new ObjectInputStream(new FileInputStream(new File(
        this.file)));
     this.chids = ((ArrayList<OctChunkId>) o.readObject());
    } catch ( IOException | ClassNotFoundException ex ) {
     main.Main.LOG.addE(ex);
    // throw new TermEx(
     //   "ChunkContainer . OctChunkIds . load() - error read OctChunk");
    }
   } else {
    this.chids = new ArrayList<>();
    File[] oct = d.listFiles();

    for ( File oc : oct ) {
     System.out.println(oc.getAbsolutePath());
     try {
      ObjectInputStream fi = new ObjectInputStream(new FileInputStream(oc));
      OctChunk o = (OctChunk) fi.readObject();

     } catch ( IOException | ClassNotFoundException ex ) {
     // throw new TermEx("ChunkContainer . OctChunkIds . load() - error read rg/");
     }

    }
    save();
   }
  } else {
  // throw new TermEx("ChunkContainer . OctChunkIds . load() - no rg/");
  }
 }

 public void save () {
  try {
   ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(
      this.file));
   serial.writeObject(chids);
   serial.flush();
   System.out.println("Saved");
  } catch ( IOException ex ) {
   Main.LOG.addE(ex);
  }
 }

 public OctChunkIds () {

 }

 public OctChunkIds ( ArrayList<OctChunk> chs, String dir ) {
  for ( OctChunk ch : chs ) {
   chids.add(ch.getId());
  }
  this.dir = dir + "rg/";
  this.file = dir + "OctChIds.db";
 }

 public String search ( ChunkPos pos) {
  for ( OctChunkId id : chids ) {
   
  }
  return null;
 }
}
/*
 *
 * for (File oc : oct) { System.out.println(oc.getAbsolutePath()); try{ ObjectInputStream serial = new ObjectInputStream(new FileInputStream(oc)); OctChunk o = (OctChunk)serial.readObject();
 * OctChunkId i = o.getId(); chids.add( i ); }catch(IOException | ClassNotFoundException ex){ throw new TermEx("ChunkContainer . OctChunkIds . load() - no ChIds"); } *
 */
