package level.chunk;

import java.io.Serializable;

public class OctChunkId implements Serializable {

 private final String name;
 private final ChunkId[] ids;

 public OctChunkId ( String name, ChunkId[] ids ) {
  this.ids = ids;
  this.name = name;
 }

 public String test ( int x, int y ) {
  for ( ChunkId id : ids ) {
   if ( id.test(x, y) ) {
    return this.name;
   }
  }

  return null;
 }
}
