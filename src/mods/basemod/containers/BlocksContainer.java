package mods.basemod.containers;

import java.io.Serializable;
import java.util.TreeMap;
import mods.basemod.LevBlock;
import render.Tex;

public class BlocksContainer implements Serializable {

 private final TreeMap<Mid , LevBlock> cont = new TreeMap<>();

 public BlocksContainer () {
 }

 public void addBlock ( LevBlock v ) {
  cont.put(v.getId() , v);
 }

 public LevBlock getBlock ( Mid k ) {
  return cont.get(k);
 }

 public Tex getTex ( Mid k ) {
  return cont.get(k).getTex();
 }

 public void addAll ( BlocksContainer t ) {
  cont.putAll(t.cont);
 }
}
