package level.chunk;

import java.io.Serializable;
import java.util.*;
import level.LevelGen;
import utils.containers.pos.*;

public class Region implements Serializable {
 private final Map<ChunkPos, Chunk> map = new HashMap<>();
 private final RegionPos pos;

 public Region ( RegionPos pox ) {
//  this.dir = main.Main.DIR + "saves/" + nm + "/rg/";
//  fl = "region" + x + "" + y + ".rg";

  this.pos = pox;
  for ( int ix = 0 ; ix < 8 ; ix++ ) {
   for ( int iy = 0 ; iy < 8 ; iy++ ) {
    for ( int iz = 0 ; iz < 8 ; iz++ ) {
     map.put(new ChunkPos(pos.gX() * 8 + ix, pos.gY() * 8 + iy, iz), new Chunk(new ChunkPos(pos.gX() * 8 + ix, pos.gY() * 8 + iy, iz)));
    }
   }
  }
 }

 public void gen ( LevelGen gen ) {
  map.values().stream().
     forEach(( ch ) -> {
      System.out.println(ch.getId().toString());
      ch.gen(gen);
     });
 }

 public RegionPos getPos () {
  return pos;
 }

 public String getName () {
  return pos.getName();
 }
}
