package mods.basemod.containers;

import java.io.Serializable;
import java.util.TreeMap;
import mods.basemod.interfaces.*;

public class ActMap implements Serializable {

 private final TreeMap<Aid, Action> map = new TreeMap<>();

 public ActMap () {

 }

 public void add ( Mid id, String a, Action act ) {
  this.map.put(new Aid(id, a), act);
 }

 public void add ( Mid id, String a, ActionU act ) {
  this.map.put(new Aid(id, a), act);
 }

 public void addAll ( ActMap t ) {
  this.map.putAll(t.map);
 }

}
