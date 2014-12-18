package mods.basemod.containers;

import java.io.Serializable;
import java.util.TreeMap;
import mods.basemod.IItem;
import render.Tex;

public class IItemsContainer implements Serializable {

 private final TreeMap<Mid , IItem> cont = new TreeMap<>();

 public IItemsContainer () {
 }

 public void addItem ( IItem v ) {
  cont.put(v.getId() , v);
 }

 public IItem getItem ( Mid k ) {
  return cont.get(k);
 }

 public Tex getTex ( Mid k ) {
  return cont.get(k).getTex();
 }

 public void addAll ( IItemsContainer t ) {
  cont.putAll(t.cont);
 }
}
