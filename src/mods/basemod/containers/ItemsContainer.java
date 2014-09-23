package mods.basemod.containers;

import java.util.TreeMap;
import mods.basemod.Item;
import render.Tex;

public class ItemsContainer {

 private final TreeMap<Mid , Item> cont = new TreeMap<>();

 public ItemsContainer () {
 }

 public void addItem ( Item v ) {
  cont.put(v.getId() , v);
 }

 public Item getItem ( Mid k ) {
  return cont.get(k);
 }

 public Tex getTex ( Mid k ) {
  return cont.get(k).getTex();
 }
 
  public void addAll(ItemsContainer t){
  cont.putAll(t.cont);
 }
}
