package mods.basemod.containers;

import utils.ids.Mid;
import java.util.*;

public class Dictionary {

 private final TreeMap<String, ArrayList<Mid>> dict = new TreeMap<>();

 public Dictionary () {

 }

 public void add ( String k, Mid v ) {
  if ( dict.containsKey(k) ) {
   dict.get(k).add(v);
  } else {
   dict.put(k, new ArrayList<>());
  }
  dict.get(k).add(v);
 }
}
