package mods.basemod;

import java.io.Serializable;
import mods.basemod.containers.Mid;

public class LevBlock extends IItem implements Serializable {

 private final String dictionary;

 public LevBlock ( Mid id ,
                   Integer durability , Model model ,
                   Speeds speed , String dictionary ) {
  super(id , durability , model , -1 , speed);
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

 @Override
 public String toString () {
  return "LevBlock " + id.toString() + "Durability: " + durability + model.
          toString() + speed.toString() + "Dictionary: " + dictionary;
 }

 public String getDictionary () {
  return dictionary;
 }
}
