package mods.basemod;

public class Model {
 protected final String file;
 public Model (String file){
  this.file = file;
 }
 
 
 @Override
 public String toString(){
  return file;
 }
}
