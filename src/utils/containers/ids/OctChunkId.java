package utils.containers.ids;

import utils.containers.pos.ChunkPos;
import java.io.Serializable;

public class OctChunkId implements Serializable {

 private final String name;
 private final ChunkPos[] ids;

 public OctChunkId ( String name, ChunkPos[] ids ) {
  this.ids = ids;
  this.name = name;
 }

}
