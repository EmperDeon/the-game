package mods.basemod;

import java.io.Serializable;
import mods.basemod.containers.Mid;

public class LevBlock extends IItem implements Serializable {

 public LevBlock ( Mid id , Model model , String... params ) {
  super(id , model , params);
 }

 @Override
 public String getParam ( String k ) {
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
  return "LevBlock " + id.toString() + " " + getAllP();
 }
}
