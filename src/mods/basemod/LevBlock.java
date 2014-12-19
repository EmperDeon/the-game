package mods.basemod;

import java.io.Serializable;
import java.util.Map;
import mods.basemod.containers.Mid;
import utils.json.JSONObject;

public class LevBlock extends IItem implements Serializable {

 public LevBlock ( Mid id , Model model , Map<String, String> map ) {
  super(id , model , map);
 }
 
 public LevBlock (JSONObject o){
  super(o);
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
