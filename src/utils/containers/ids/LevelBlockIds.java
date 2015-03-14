package utils.containers.ids;
import java.util.*;
import mods.basemod.LevBlock;
import utils.containers.id.*;

public class LevelBlockIds {
 private final List<LevBlock> list = new ArrayList<>();

 public LevelBlockIds () {

 }

 public void put ( LevBlock mid ) {
  list.add(mid);
 }

 public LevBlock get ( Rid id, String url ) {
  for ( LevBlock b : list ) {
   if ( b.getModel().getId().equals(id) && b.getModel().getUrl().equals(url) ) {
    return b;
   }
  }
  return null;
 }

 public boolean contains ( Rid id, String url ) {
  for ( LevBlock b : list ) {
   if ( b.getModel().getId().equals(id) && b.getModel().getUrl().equals(url) ) {
    return true;
   }
  }

  return false;
 }

}
