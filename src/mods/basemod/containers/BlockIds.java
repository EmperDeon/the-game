package mods.basemod.containers;
import java.util.*;
import utils.containers.pos.BlockPos;

public class BlockIds {
 private final List<BlockPos> list = new ArrayList<>();

 public BlockIds () {

 }

 public void put ( BlockPos mid ) {
  list.add(mid);
 }

 public BlockPos get ( int m, int i, int s ) {
  for ( BlockPos id : list ) {
   if ( id.gX().equals(m) && id.gY().equals(i) && id.gZ().equals(s) ) {
    return id;
   }
  }
  return null;
 }

 public boolean contains ( int m, int i, int s ) {
  for ( BlockPos id : list ) {
   if ( id.gX().equals(m) && id.gY().equals(i) && id.gZ().equals(s) ) {
    return true;
   }
  }

  return false;
 }
}
