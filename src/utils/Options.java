package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Options implements Serializable{
 private ArrayList<String> opt;   
 private String file;
 private String dir;
 public Options(){
  
 }
 
 public Options(String[] opt){
     this.opt.addAll(Arrays.asList(opt));   
 }
 
 public String[] get(String opt){
  String[] tmp = new String[]{"Err"};   
  for(int i=0;i<this.opt.size();i++)
   if(this.opt.get(i).startsWith(opt))
    tmp = Separ.getval(this.opt.get(i));
  return tmp;
 }
 
 public void add(String key,String[] val){
  this.opt.add(key);
 }
 
 public void load(String file){
  Options tm;   
  try {     
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(file));
   tm = (Options) serial.readObject();
   this.opt=tm.opt;
   this.dir=tm.dir;
   this.file=tm.file;
   } catch (IOException | ClassNotFoundException ex) {
   System.out.println("Error: \n"+ex);
 }
}
 
 public void save(String file){
  this.file=file;
  if(file.lastIndexOf("/")!=-1){
   this.dir=file.substring(0, file.lastIndexOf("/")-1);
  }else{
  if(file.lastIndexOf("\\")!=-1){   
   this.dir=file.substring(0, file.lastIndexOf("\\")-1);
  }else{
   System.out.println("Error with find dir \n ");    
   }
  }
 
  try {     
  File fos = new File(file);
  if(!fos.canWrite())
   fos.mkdirs();
  fos.delete();
  
  ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(file));
  serial.writeObject(this);
  serial.flush();
  } catch (IOException ex) {
   System.out.println("Error: \n"+ex);
  }
 }
}
