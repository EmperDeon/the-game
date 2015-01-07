package mods.basemod.containers;

import java.util.TreeMap;
import mods.basemod.Resource;
import mods.basemod.Rid;

public class Resources {
 private final TreeMap<Rid, Resource> map = new TreeMap<>();
 
 
 public Resources(){
  
 }
 
 public Resource getResource(Rid id){
  return map.get(id);
 }
 
 public void putResource(Rid k, Resource v){
  this.map.put(k , v);
 }
 
 public void putAll(String mid){
  
 }
 
 public void load(){
 
 }
 
 public void save(){
 
 }
 
}
