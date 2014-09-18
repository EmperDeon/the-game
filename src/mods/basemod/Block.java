package mods.basemod;

import mods.basemod.containers.Mid;
import mods.basemod.containers.MultiTex;
import java.util.TreeMap;
import render.Tex;
import utils.vec.Vec3;

public class Block {

 private final MultiTex tex;
 private final Mid id;
 private final TreeMap<String , String> param;

 public Block ( Tex tex , Vec3<Integer> id ) {
  this.param = new TreeMap<>();
  this.tex = new MultiTex(tex);
  this.id = new Mid(id);
 }

 public Block ( Tex tex , Integer mid , Integer iid , Integer sid ) {
  this.param = new TreeMap<>();
  this.tex = new MultiTex(tex);
  this.id = new Mid(mid , iid , sid);
 }

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

 public Tex getTex () {
  return tex.get();
 }

 public Mid getId () {
  return id;
 }
}

