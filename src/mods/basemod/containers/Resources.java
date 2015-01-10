package mods.basemod.containers;

import java.util.TreeMap;
import static main.Main.LOG;
import mods.basemod.Resource;
import mods.basemod.Rid;

public abstract class Resources {

 private final TreeMap<Rid , Resource> map = new TreeMap<>();

 public Resources () {
  LOG.addI("Resource inited");
 }

 public Resource getResource ( Rid id ) {
  return map.get(id);
 }

 public void putResource ( Rid k , Resource v ) {
  this.map.put(k , v);

 }

 public void putAll ( String mid ) {

 }

 public void putAll ( Resources res ) {

 }

 public void load () {
  LOG.addI("Load started");

  LOG.addI("Load ended");
 }

 public void save () {
  LOG.addI("Save started");

  LOG.addI("Save ended");
 }

}
