package utils.containers.ids;

import java.io.Serializable;

public class OctChunkId implements Serializable {

 private final String name;
 private final ChunkId[] ids;

 public OctChunkId ( String name, ChunkId[] ids ) {
  this.ids = ids;
  this.name = name;
 }

}
