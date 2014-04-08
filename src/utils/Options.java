package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public final class Options implements Serializable{
 private ArrayList<String> opt=new ArrayList();   
 private String file;
 private String dir;
  
 public Options(String file){    
  this.file=file;
  this.dir=getDir(file);
  load();
//  System.out.println(err.get());
 }
 
 public Options(String[] opt,String file){
  this.file=file;
  this.dir=getDir(file);
  this.opt.addAll(Arrays.asList(opt));   
 }
 
 public String[] get(String opt){  
  String[] tmp = new String[]{"Err"};   
  for(int i=0;i<this.opt.size();i++)
   if(this.opt.get(i).startsWith(opt))
    tmp = Separ.getval(this.opt.get(i));
  return tmp;
 }
 
 public void add(String val){
  this.opt.add(val);
 }
 
 public void load(){
  try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
   String line;
   while ((line = reader.readLine()) != null) {
    if(line.startsWith("!dir")){
     this.dir=line.split(":")[1];
    }else{
    if(line.startsWith("!file")){
     this.file=line.split(":")[1];
    }else{
     add(line);
    }
   }
  }   
 }catch(Exception ex){
  main.Main.err.add("Options.load()",ex);
 }
}
 public String getDir(String file){
  if(file.lastIndexOf("/")!=-1){
   return file.substring(0, file.lastIndexOf("/")+1);
  }else{
  if(file.lastIndexOf("\\")!=-1){   
   return file.substring(0, file.lastIndexOf("\\")+1);
  }else{
   main.Main.err.add("Options.getDir()",new Exception());
   return null;
   }
  }
 }
 public void save(){
  export(this.file);
 }
 
 public void export(String filel){
  String dirl = getDir(filel);
  File fos = new File(filel);
  if(!fos.canWrite())
   fos.mkdirs();
  fos.delete();

  try(BufferedWriter writer = new BufferedWriter(new FileWriter(filel))){
   writer.write("!dir:"+dirl+"\n");
   writer.write("!file:"+filel+"\n");
      
   for(int i=0; i<opt.size();i++)
    writer.write(this.opt.get(i)+"\n");
  } catch (IOException ex) {
      main.Main.err.add("Options.save()",ex);
  }
 }
}
