package utils;
import java.util.ArrayList;

public class Error {
 private final ArrayList<String> errcl = new ArrayList();   
 private final ArrayList<Exception> errex = new ArrayList();
   
 public Error(){

 }
 
 public void add(String cl,Exception ex){
  this.errcl.add(cl);
  this.errex.add(ex);
 }
 
 public void check(){
  if(!errex.isEmpty()){
   for(int i=0;i<errex.size();i++)
    System.out.println(get(i)); 
   errcl.clear();
   errex.clear();
  }
 }
 
 public String get(int x){
  String s;   
  s="\n Error in class :"+errcl.get(x)+"\n ;";
  errex.get(x).printStackTrace();
  return s;
 }
}
