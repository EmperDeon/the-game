package utils;
public class Iid{
 private String val; 
 
 public Iid(String val){
  this.val=val;
 }
 
 public void setMid(String val){
  this.val=this.val.replace(getMid(), val);
 }
 
 public void setBid(String val){
  this.val=this.val.replace(getBid(), val);
 }
 
 public void setSid(String val){
  this.val=this.val.replace(getSid(), val);
 }
 
 public String getMid(){
  return (val.split(":"))[0];
 }
 
 public String getBid(){
  return (val.split(":"))[1];
 }
 
 public String getSid(){
  return (val.split(":"))[2];
 }
}
