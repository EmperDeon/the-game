package level;

import java.util.ArrayList;
import main.Main;
import utils.Id;
import utils.Separ;
import utils.TId;

public class LevBlock {

public Boolean Bparams;    
public Id id;
public TId tid;
public ArrayList<String> param= new ArrayList();
//public Guiblock gui;

public LevBlock(Id id,TId tid,ArrayList<String> param){
 this.id=id;
 this.tid=tid;
 if(param!=null) {
  this.param.addAll(param); 
  this.Bparams=true;
 }  
}

public String getparam(String key){
  String s = "";
  try{
   if(param.contains(key))   
   for (String par : param) {
    if (Separ.getkey(par).equals(key)) {
     s = Separ.getkey(par);
    }
   }
  }catch(Exception e){
   Main.err.add("LevBlock", e);
  }
  return(s);
 }

 public Id getId(){
  return this.id;
 }
}
