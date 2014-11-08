package mods.basemod;

import java.io.Serializable;
import java.util.TreeMap;
import mods.basemod.containers.Mid;
import mods.basemod.containers.MultiTex;
import mods.basemod.interfaces.InvItem;
import render.Tex;

public class IItem implements Serializable , InvItem {

 protected final MultiTex tex;
 protected final Mid id;
 protected final TreeMap<String , String> param;

 public IItem ( MultiTex tex , Mid id ) {
  this.param = new TreeMap<>();
  this.tex = tex;
  this.id = id;
 }

 public IItem ( Tex tex , String mid , String iid , String sid ) {
  this.param = new TreeMap<>();
  this.tex = new MultiTex(tex);
  this.id = new Mid(mid , iid , sid);
 }

 @Override
 public String getparam ( String k ) {
  String v;
  try {
   v = param.get(k);
  } catch ( Exception e ) {
   v = "";
   main.Main.LOG.addE("Item.getparam()" , e);
  }
  return ( v );
 }

 @Override
 public Tex getTex () {
  return tex.get();
 }

 @Override
 public Mid getId () {
  return id;
 }
}
