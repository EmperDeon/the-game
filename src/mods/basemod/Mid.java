package mods.basemod;

import utils.vec.Vec3;

public class Mid {
 private final Vec3<Integer> id;
 
 public Mid(Vec3<Integer> id){
  this.id = id;
 }
 
 public Mid(Integer mid, Integer iid, Integer sid){
  this.id = new Vec3<>();
  this.id.sX(mid);
  this.id.sY(iid);
  this.id.sZ(sid);
 }
 
 public Vec3<Integer> getIds(){
  return id;
 }
 
 public Integer getMid(){
  return id.gX();
 }
 
 public Integer getIid(){
  return id.gY();
 }
 
 public Integer getSid(){
  return id.gZ();
 }
}
