package mods.basemod;

import java.util.ArrayList;
import render.Tex;
import utils.TId;
import utils.vec.Vec4;

public class BaseMod {

 public ArrayList<Entity> blocks;
 public ArrayList<Entity> items;
 public ArrayList<Tex> Texs;
 public Boolean needparam = false;
 public int id = -1;

 public void init () {

 }

 public void action ( int id , int id2 , int act , Vec4<Integer> coord ) {

 }

 public Tex getTex ( TId id ) {
  return Texs.get(id.getTid());
 }
}
