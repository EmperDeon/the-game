package utils;
import java.util.ArrayList;
import java.util.logging.Level;
import main.Main;

public class Error {
 private final ArrayList<String> errcl = new ArrayList<>();   
 private final ArrayList<Exception> errex = new ArrayList<>();
 
 public Error(){

 }
 
 public void addE(String cl,Exception ex){
  this.errcl.add(cl);
  this.errex.add(ex);
 }
 
 public void check(){
  if(!errex.isEmpty()){
   for(int i=0;i<errex.size();i++)
     Main.log.log(Level.SEVERE, errcl.get(i) +" Exception: ", errex.get(i)); 
   errcl.clear();
   errex.clear();
  }
 }
 
 public String get(int x){
  String s;   
  s="\n Error in class :"+errcl.get(x)+"\n ;";
 // errex.get(x).printStackTrace();
  return s;
 }
}
