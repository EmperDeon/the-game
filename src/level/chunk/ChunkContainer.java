package level.chunk;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import static main.Main.LOG;
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

 public void create () {

 }

 public void destroy () {

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

  for ( RegionPos r : reg.keySet() ) {
   oids.put(r);

  }
 }

 public void save ( String dir ) {
  reg.keySet().stream().
     forEach(( r ) -> {
      try ( ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(dir + r.getName()))) ) {
       out.writeObject(this);
       out.flush();
      } catch ( Exception ex ) {
       LOG.addE(ex);
      }
     });
 }

 public void edit ( ChunkPos pos ) {
  rch.add(pos);
 }
}
