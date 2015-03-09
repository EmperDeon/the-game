package level.chunk;
import java.io.*;
import java.util.*;
import utils.containers.pos.*;

public final class RegionIds implements Serializable {
 private final Set<RegionPos> list = new TreeSet<>();

 public RegionIds () {

 }

 public void put(RegionPos pos){
  list.add(pos);
 }
 
 public RegionPos search(ChunkPos pos){
  for(RegionPos t : list){
   if (t.isContains(pos))
    return t;
  }
  return RegionPos.nullP;
 }

}
