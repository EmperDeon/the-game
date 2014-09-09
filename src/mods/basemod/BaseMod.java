package mods.basemod;

import java.util.ArrayList;
import render.Tex;
import utils.vec.Vec4;

public class BaseMod {

 protected ArrayList<Entity> blocks;
 protected ArrayList<Entity> items;
 protected Boolean needparam = false;
 protected int id = -1;

 public void init () {

 }

 public void action ( int id , int id2 , int act , Vec4<Integer> coord ) {

 }

 public Tex getBTex ( Mid id ) {
  return blocks.get(id.getIid()).getTex();
 }
 public Tex getITex ( Mid id ) {
  return items.get(id.getIid()).getTex();
 }
}
