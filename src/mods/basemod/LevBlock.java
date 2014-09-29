package mods.basemod;

import java.io.Serializable;
import mods.basemod.containers.Mid;
import mods.basemod.containers.MultiTex;
import render.Tex;
import utils.vec.Vec3;

public class LevBlock extends IItem implements Serializable {

 public LevBlock ( MultiTex tex , Mid id ) {
  super(tex , id);
 }

 public LevBlock ( Tex tex , Vec3<Integer> id ) {
  super(new MultiTex(tex) , id);
 }

 public LevBlock ( Tex tex , Integer mid , Integer iid , Integer sid ) {
  super(new MultiTex(tex) , mid , iid , sid);
 }

}
