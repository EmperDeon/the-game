package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public final class Options implements Serializable{
 private HashMap<String, String> opt = new HashMap<>();   
 private String file;
 private String dir;
  
 public Options(String file){    
  this.file=file;
  this.dir=getDir(file);
  load();
 }

 
 public String get(String key){   
  if(this.opt.containsKey(key))
   return this.opt.get(key);
  else{
   main.Main1.LOG.addE("Options.get()",new Exception("Error with getting value with key "+key));
   return null;
  }
 }
 
 public Double getD(String key){
  return Double.parseDouble(get(key));
 }
 
 public Integer getI(String key){
  return Integer.parseInt(get(key));
 }
 
 public void add(String key, String val){
  this.opt.put(key , val);
 }
 
 public void load(){  
  try (ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(file))) {
   serial.writeObject(this);
  } catch (IOException ex) {
   main.Main1.LOG.addE("Options.load()", ex);
  }

}
 public String getDir(String file){
  if(file.lastIndexOf("/")!=-1){
   return file.substring(0, file.lastIndexOf("/")+1);
  }else{
  if(file.lastIndexOf("\\")!=-1){   
   return file.substring(0, file.lastIndexOf("\\")+1);
  }else{
   main.Main1.LOG.addE("Options.getDir()",new Exception());
   return null;
   }
  }
 }
 public void save(){
  export(this.file);
 }
 
 public void export(String filel){
  File fos = new File(filel);
  if(!fos.canWrite())
   fos.mkdirs();
  fos.delete();

  try {
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(file));
   Options tmp = ( Options ) serial.readObject();
   this.dir = tmp.dir;
   this.file = tmp.file;
   this.opt = tmp.opt;
  } catch (IOException | ClassNotFoundException ex) {
   main.Main1.LOG.addE("Options.export()",ex);
  }
 }
}
