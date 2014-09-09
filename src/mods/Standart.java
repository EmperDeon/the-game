package mods;

import java.util.ArrayList;
import mods.basemod.BaseMod;
import utils.vec.Vec4;

public class Standart extends BaseMod {

 @Override
 public void init () {
  needparam = true;
  blocks = new ArrayList<>();
  //
 }

 @Override
 public void action ( int id , int id2 , int act , Vec4<Integer> coord ) {
  //
 }

 public Standart () {

 }
}
