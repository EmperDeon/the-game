package utils;

import java.util.ArrayList;

public class Error {
 private final ArrayList<String> errcl = new ArrayList();   
 private final ArrayList<Exception> errex = new ArrayList();
 
 public Error(){}
 
 public void add(String cl,Exception ex){
  this.errcl.add(cl);
  this.errex.add(ex);
 }
 
 public String get(){
  String s="";   
  for(int i = 0;i<errex.size();i++)
   s+="\n Error in class :"+errcl.get(i)+"\n ;"+errex.get(i).getMessage();
  return s;
 }
}
