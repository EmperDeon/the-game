package level;

import java.util.HashMap;
import main.Main;
import utils.Iid;
import utils.TId;

public class LevBlock {

public Boolean Bparams;    
public Iid id;
public TId tid;
public HashMap<String,String> param = new HashMap<>();
//public Guiblock gui;

public LevBlock(Iid id,TId tid,HashMap<String,String> param){
 this.id=id;
 this.tid=tid;
 if(param!=null) {
  this.param.putAll(param); 
  this.Bparams=true;
 }  
}

public String getparam(String key){
  String s = "";
  try{
   if(param.containsKey(key))   
    return param.get(key);
  }catch(Exception e){
   Main.LOG.addE("LevBlock", e);
  }
  Main.LOG.addE("LevBlock", new Exception("Error with getting value with key "+key));
  return "Err";
 }

 public Iid getId(){
  return this.id;
 }
}
