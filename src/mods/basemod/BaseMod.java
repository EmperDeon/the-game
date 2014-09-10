package mods.basemod;

import java.util.ArrayList;
import render.Tex;
import utils.vec.Vec4;

public class BaseMod {

 protected ArrayList<Entity> blocks;
 protected ArrayList<Entity> items;
 protected Boolean needparam = false;
 public Mid id;
 
 public void init () {
  id = new Mid(-1,0,0);
  main.Main.IdMap.add(id , "BaseMod" ,"" ,"");
  main.Main.mods.initF(id);
 }
 
 public void postinit(){
  main.Main.mods.postinitF(id);
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
