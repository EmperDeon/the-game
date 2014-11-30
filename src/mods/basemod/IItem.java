package mods.basemod;

import java.io.Serializable;
import java.util.TreeMap;
import mods.basemod.containers.Mid;
import mods.basemod.interfaces.InvItem;
import render.Tex;

public class IItem implements Serializable , InvItem {

 protected final Mid id;
 protected final TreeMap<String , String> param;
 protected final Integer durability;
 protected final Model model;
 protected final Integer type;
 protected final Speeds speed;

 public IItem ( Mid id ,
                Integer durability , Model model , Integer type , Speeds speed ) {
  this.param = new TreeMap<>();
  this.id = id;
  this.durability = durability;
  this.model = model;
  this.type = type;
  this.speed = speed;
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
  return null;// tex.get();
 }

 @Override
 public Mid getId () {
  return id;
 }

 public Integer getDurab () {
  return durability;
 }

 public Integer getDurability () {
  return durability;
 }

 public Model getModel () {
  return model;
 }

 public Integer getType () {
  return type;
 }

 public Speeds getSpeed () {
  return speed;
 }

 @Override
 public String toString () {
  return "IItem, " + id.toString() + durability + model + type + speed;
 }

}
