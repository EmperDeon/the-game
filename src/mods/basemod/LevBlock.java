package mods.basemod;

import java.io.Serializable;
import mods.basemod.containers.Mid;
import mods.basemod.containers.MultiTex;
import render.Tex;

public class LevBlock extends IItem implements Serializable {

 public LevBlock ( MultiTex tex , Mid id ) {
  super(tex , id);
 }

 public LevBlock ( Tex tex , String mid , String iid , String sid ) {
  super(tex , mid , iid , sid);
 }

 @Override
 public String getparam ( String k ) {
  String v;
  try {
   v = param.get(k);
  } catch ( Exception e ) {
   v = "";
   main.Main.LOG.addE("Block.getparam()" , e);
  }
  return ( v );
 }
}
