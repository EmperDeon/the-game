package mods.basemod;

import java.io.Serializable;
import java.util.TreeMap;
import mods.basemod.containers.Mid;
import mods.basemod.interfaces.InvItem;

public class IItem implements Serializable , InvItem {

 protected final Mid id;
 protected final TreeMap<String , String> param;
 protected final Model model;

 public IItem ( Mid id , Model model , String... param ) {
  this.param = new TreeMap<>();
  this.id = id;

  this.model = model;
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
 public void addAllP ( String[] p ) {
  String[] t;
  for ( String s : p ) {
   t = s.split(":");
   this.param.put(t[0] , t[1]);
  }
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

}
