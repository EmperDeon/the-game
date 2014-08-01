package level;

import java.util.ArrayList;
import main.Main;
import utils.Iid;
import utils.Separ;
import utils.TId;

public class LevBlock {

public Boolean Bparams;    
public Iid id;
public TId tid;
public ArrayList<String> param= new ArrayList<>();
//public Guiblock gui;

public LevBlock(Iid id,TId tid,ArrayList<String> param){
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
   Main.err.addE("LevBlock", e);
  }
  return(s);
 }

 public Iid getId(){
  return this.id;
 }
}
