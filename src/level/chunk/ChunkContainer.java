package level.chunk;

import java.io.*;
import java.util.*;
import main.Main;
import mods.basemod.LevBlock;
import utils.containers.pos.*;
import utils.exceptions.TermEx;

public final class ChunkContainer {

 private final Map<ChunkPos, Chunk> chs = new HashMap();
 private final Set<ChunkPos> rch = new HashSet<>();// Edited chunks
 private final OctChunkIds oids = new OctChunkIds();

 private String dir;//  game/save/name/

 public ChunkContainer(){
  
 }
 
 public ChunkContainer ( String dir ) throws TermEx {
  this.dir = dir;

  if ( !new File(dir).canRead() ) {
   gen("World1", 8);
  }

  oids.load(dir);

  load(new ChunkPos(0, 0, 0), 8);

 }

 public void gen ( String name, int chpr ) {
  ArrayList<OctChunk> oct = new ArrayList<>();
  for ( int x = -(chpr / 8) ; x <= (chpr / 8) ; x++ ) {
   for ( int y = -(chpr / 8) ; y <= (chpr / 8) ; y++ ) {
    oct.add(new OctChunk(name, x, y));
   }
  }

  File f = new File(main.Main.DIR + "saves/" + name + "/rg/");
  f.mkdirs();

  for ( OctChunk oc : oct ) {
   addAll(oc);

   try {
    ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(oc.
       getD()));
    serial.writeObject(oc);
    serial.flush();
   } catch ( IOException ex ) {
    Main.LOG.addE(ex);
    System.err.println(oc.getD());
   }
  }
 }

 public void add ( Chunk ch ) {
  this.chs.put(ch.getId(), ch);
 }

 public void addAll ( OctChunk och ) {
  ArrayList<Chunk> oc = och.getAllCh();
  for ( Chunk ch : oc ) {
   add(ch);
  }
 }

 public void load ( ChunkPos pos, int chpr ) {
  int x1 = (pos.gX() - chpr) / 8;
  int x2 = (pos.gX() + chpr) / 8;
  int y1 = (pos.gY() - chpr) / 8;
  int y2 = (pos.gY() + chpr) / 8;

  for ( int i1 = x1 ; i1 <= x2 ; i1++ ) {
   for ( int i2 = y1 ; i2 <= y2 ; i2++ ) {
    loadOct("region" + i1 + "" + i2 + ".rg");
   }
  }
 }

 public void redact ( ChunkPos cid, BlockPos bpos, LevBlock block ) {
  chs.get(cid).redact(bpos, block);
  rch.add(cid);
 }

 public void redactObl ( ChunkPos cid, BlockPos pos1 , BlockPos pos2,
                         LevBlock block ) {
  chs.get(cid).redactObl(pos1, pos2, block);
  rch.add(cid);
 }

 public void loadOct ( String file ) {
  try {
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(
      this.dir + "rg/" + file));
   addAll((OctChunk) serial.readObject());
  } catch ( IOException | ClassNotFoundException ex ) {
   Main.LOG.addE(ex);
  }
  System.out.println("Loaded: " + file + " : " + chs.size());
 }

 public void clear () {
  chs.clear();
 }

 public void free (ChunkPos pos) {
  chs.remove(pos);
 }

 public void save () {
  if ( !rch.isEmpty() ) {
   for ( ChunkPos id : rch ) {
    String file = oids.search(id);

    OctChunk oc;
    try {
     ObjectInputStream serial = new ObjectInputStream(new FileInputStream(
        new File(this.dir + "rg/" + file)));
     oc = (OctChunk) serial.readObject();
    } catch ( IOException | ClassNotFoundException ex ) {
     oc = null;
    }

    if ( oc != null ) {
     oc.replaceChunk(id.gX(), id.y, chs.get(id));

     try {
      ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(
         file));
      serial.writeObject(oc);
      serial.flush();
      System.out.println("Saved:" + file);
     } catch ( IOException ex ) {
      Main.LOG.addE(ex);
     }
    }
   }
  } else {
   System.out.println("No changed");
  }
 }

 public Chunk get (ChunkPos pos) {
  return chs.get(pos);
 }
}
