package mods.basemod;

import java.io.Serializable;
import java.util.TreeMap;
import mods.basemod.containers.Mid;
import mods.basemod.containers.MultiTex;
import mods.basemod.interfaces.InvItem;
import render.Tex;
import utils.vec.Vec3;

public class IItem implements Serializable, InvItem{

 private final MultiTex tex;
 private final Mid id;
 private final TreeMap<String , String> param;

 public IItem ( MultiTex tex , Mid id ) {
  this.param = new TreeMap<>();
  this.tex = tex;
  this.id = id;
 }

 public IItem ( MultiTex tex , Vec3<Integer> id ) {
  this.param = new TreeMap<>();
  this.tex = tex;
  this.id = new Mid(id);
 }

 public IItem ( Tex tex , Integer mid , Integer iid , Integer sid ) {
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
