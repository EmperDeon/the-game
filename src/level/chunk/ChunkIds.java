package level.chunk;

import java.util.ArrayList;

public class ChunkIds {

 private final ArrayList<Integer> id = new ArrayList<>();
 private final ArrayList<ChunkId> chid = new ArrayList<>();

 public ChunkIds () {
  // 
 }

 public int getIdC ( int x , int y ) {
  int i = 0;
  for ( ChunkId ch : chid ) {
   if ( ch.test(x , y) ) {
    return i;
   }
   i++;
  }
  return 0;
 }

 public void add ( int i , int x , int y ) {
  id.add(i);
  chid.add(new ChunkId(x , y));
 }

 public ChunkId getId ( int i ) {
  return chid.get(i);
 }

 public void clear () {
  id.clear();
  chid.clear();
 }

 public boolean test ( int x , int y ) {
  for ( ChunkId chid1 : chid ) {
   if ( chid1.test(x , y) ) {
    return true;
   }
  }
  return false;
 }

 public void free ( int x , int y ) {
  int i = 0;
  for ( ChunkId ch : chid ) {
   if ( ch.test(x , y) ) {
    id.remove(i);
    chid.remove(i);
   }
   i++;
  }
 }
}
