package mods.basemod;

import java.util.TreeMap;
import mods.basemod.containers.Containers;
import mods.basemod.containers.Mid;
import utils.vec.Vec4;

public class BaseMod {

 protected TreeMap<Mid , Block> blocks;
 protected TreeMap<Mid , Item> items;
 protected Boolean needparam = false;
 public Mid id;

 public void init (Containers c) {
  id = new Mid(-1 , 0 , 0);
  c.getIdmap().add(id , "BaseMod" , "" , "");
  c.initF(id);
 }

 public void postinit (Containers c) {
  c.postinitF(id);
 }

 public void action ( int id , int id2 , int act , Vec4<Integer> coord ) {

 }
//
// public Tex getBTex ( Mid id ) {
//  return blocks.get(id.getIid()).getTex();
// }
// public Tex getITex ( Mid id ) {
//  return items.get(id.getIid()).getTex();
// }

}
