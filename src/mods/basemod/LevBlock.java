package mods.basemod;

import java.io.Serializable;
import mods.basemod.containers.Mid;

public class LevBlock extends IItem implements Serializable {

 protected final String dictionary;

 public LevBlock ( String dictionary , Mid id ,
                   Integer durability , Model model , Integer type ,
                   Speeds speed ) {
  super(id , durability , model , type , speed);
  this.dictionary = dictionary;
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
