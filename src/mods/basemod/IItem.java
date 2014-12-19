package mods.basemod;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import mods.basemod.containers.Mid;
import mods.basemod.interfaces.InvItem;
import utils.json.JSONObject;

public class IItem implements Serializable , InvItem {

 protected final Mid id;
 protected final TreeMap<String , String> param;

 public TreeMap<String , String> getParam () {
  return param;
 }
 protected final Model model;

 public IItem ( Mid id , Model model , Map<String , String> map ) {
  this.param = new TreeMap<>(map);
  this.id = id;
  this.model = model;
 }

 public IItem ( String m , JSONObject o ) {
  this.id = new Mid(m , o.getString("Iid") , o.getString("Sid"));
  this.model = new Model(o.getString("Model"));
  this.param =;
 }

 @Override
 public String getParam ( String k ) {
  String t = "";
  try {
   t = param.get(k);
  } catch ( Exception e ) {
   main.Main.LOG.addE("Item.getparam()" , e);
  }
  return t;
 }

 @Override
 public String getAllP () {
  String t = "";
  t = param.keySet().stream().
          map(( s ) -> s + "=" + param.get(s)).
          reduce(t , String::concat);
  return t;
 }

 @Override
 public void addParam ( String k , String v ) {
  this.param.put(k , v);
 }

 @Override
 public void addAllP ( Map<String , String> p ) {
  this.param.putAll(p);
 }

 @Override
 public Mid getId () {
  return id;
 }

 public Model getModel () {
  return model;
 }

 @Override
 public String toString () {
  return "IItem, " + id.toString() + " " + getAllP();
 }

 @Override
 public void toJSON ( JSONObject o ) {

 }
}
