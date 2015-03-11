package mods.basemod;

import java.io.Serializable;
import java.util.*;
import mods.basemod.containers.Server;
import mods.basemod.resources.Model;
import static mods.basemod.resources.Model.Model;
import utils.containers.ids.Mid;
import utils.containers.json.JSONObject;

public class LevBlock extends IItem implements Serializable {

 public LevBlock () {
  super(Server.instanceMid("main", "base"), Model(Server.instanceRid(Server.instanceMid("main","base"), Resource.Type.Model , ""), "base"), null);
 }

 public LevBlock ( Mid id, Model model, Map<String, String> map ) {
  super(id, model, map);
 }

 public LevBlock ( String m, JSONObject o ) {
  super(m, o);
 }

 @Override
 public Object getParam ( String k ) {
  Object v;
  try {
   v = param.get(k);
  } catch ( Exception e ) {
   v = "";
   main.Main.LOG.addE(e);
  }
  return (v);
 }

 @Override
 public String toString () {
  return "LevBlock " + id.toString() + " " + getAllP();
 }

 @Override
 public void toJSON ( JSONObject o ) {
  o.put("Iid", id.getIid());
  o.put("Sid", id.getSid());
  o.put("Model", model.getFile());
  o.put("Params", param);
 }
}
