package mods.basemod.containers;

import java.util.ArrayList;
import render.Tex;

public class MultiTex {
 private final ArrayList<Tex> arr = new ArrayList<>();
 private boolean eqtex = true;// if all textures of block is eqal
 private int curr = 0;
 public MultiTex(Tex tex){
  this.arr.add(tex);
  
 }
 
 public Tex get(){
  if(eqtex)
   return arr.get(0);
  return arr.get(curr);
 }
}
