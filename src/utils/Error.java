package utils;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import main.Main;

public class Error {
 private final ArrayList<String> errcl = new ArrayList<>();   
 private final ArrayList<Exception> errex = new ArrayList<>();
 private final TreeMap <String, String> inf = new TreeMap<>();
 private final TreeMap <String, Exception> err = new TreeMap<>();
 //private final Queue<String> errq;
 public Error(){

 }
 
 public void addE(String cl,Exception ex){
  err.put(cl , ex);
 }
 
 public void addI(String cl, String info){
  inf.put(cl , info);
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
