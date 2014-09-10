package main;

import java.util.TreeMap;
import mods.basemod.Mid;

public class IdMap {
 private final TreeMap<Integer, String> sid = new TreeMap<>();
 private final TreeMap<Integer, String> iid = new TreeMap<>();
 private final TreeMap<Integer, String> mid = new TreeMap<>();
 
 public IdMap(){}
 public void add(Mid id, String mid, String iid, String sid){
  this.mid.put(id.getMid(), mid);
  this.iid.put(id.getIid(), iid);
  this.sid.put(id.getSid(), sid);
 }
 
 public String getMid(Mid id){
  return this.mid.get(id.getMid());
 }
 public String getIid(Mid id){
  return this.iid.get(id.getIid());
 }
 public String getSid(Mid id){
  return this.sid.get(id.getSid());
 }
}
