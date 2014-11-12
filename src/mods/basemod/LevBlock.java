package mods.basemod;

import java.io.Serializable;
import mods.basemod.containers.Mid;
import mods.basemod.containers.MultiTex;

public class LevBlock extends IItem implements Serializable {
 protected final String dictionary;

 public LevBlock ( String dictionary , MultiTex tex , Mid id,  Integer durability , Model model , Integer type , Speeds speed ) {
  super(tex , id, durability, model,type,speed);
  this.dictionary = dictionary;
 }

 public LevBlock ( MultiTex tex , Mid id , Integer durability , Model model , Integer type , Speeds speed, String dict) {
  super(tex , id, durability, model,type,speed);
  this.dictionary = dict;
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
