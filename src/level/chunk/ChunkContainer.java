package level.chunk;

import java.util.*;
import utils.containers.pos.*;

public final class ChunkContainer {
 private final Map<ChunkPos, Chunk> chs;
 private final Set<ChunkPos> rch ;// Edited chunks
 private final RegionIds oids ;
 
 public ChunkContainer(){
  chs = new HashMap();
  rch = new HashSet<>();
  oids = new RegionIds();
 }
 
 
}
