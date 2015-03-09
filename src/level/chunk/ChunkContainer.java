package level.chunk;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import level.LevelGen;
import static main.Main.*;
import utils.containers.pos.*;

public final class ChunkContainer {
 private final Map<RegionPos, Region> reg;
 private final Set<ChunkPos> rch;// Edited chunks
 private final RegionIds oids;

 public ChunkContainer () {
  reg = new HashMap();
  rch = new HashSet<>();
  oids = new RegionIds();
 }

 public void create ( String dir ) {
  new File(dir + "region/").mkdirs();
  for ( int px = -2 ; px <= 2 ; px++ ) {
   for ( int py = -2 ; py <= 2 ; py++ ) {
    reg.put(new RegionPos(px, py), new Region(new RegionPos(px, py)));
   }
  }
  gen(SERVER.getWorldGen());
  save(dir);
 }

 private void gen ( LevelGen gen ) {
  reg.values().stream().
     forEach(( r ) -> {
      r.gen(gen);
      System.gc();
     });
 }

 public void destroy ( String dir ) {
  save(dir);
 }

 public void load ( String dir ) {
  Region t;
  for ( File f : new File(dir + "region/").listFiles() ) {
   try ( ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(new FileInputStream(f))) ) {
    t = (Region) in.readObject();
    reg.put(t.getPos(), t);
   } catch ( Exception ex ) {
    LOG.addE(ex);
   }
  }

  reg.keySet().stream().
     forEach(( r ) -> {
      oids.put(r);
     });
  LOG.addD("Loaded " + reg.size() + " regions");
 }

 public void save ( String dir ) {
  List<Region> t = new ArrayList<>();
  RegionPos t1;
  for ( ChunkPos r : rch ) {
   t1 = oids.search(r);
   if ( !(t1.isNull() || t.contains(reg.get(t1))) ) {
    t.add(reg.get(t1));
   }
  }

  t.stream().
     forEach(( r ) -> {
      if ( new File(dir + r.getName()).canRead() ) {
       new File(dir + r.getName()).delete();
      }
      try ( ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(dir + r.getName()))) ) {
       out.writeObject(r);
       out.flush();
      } catch ( Exception ex ) {
       LOG.addE(ex);
      }
     });
  LOG.addD("Writed " + t.size() + " regions");
 }

 public void edit ( ChunkPos pos ) {
  rch.add(pos);
 }
}
