package level;

import java.util.ArrayList;
import main.Main;
import utils.Id;
import utils.Separ;

public class LevBlock {
    
public Id id;
public ArrayList<String> param= new ArrayList();
//public Guiblock gui;

public LevBlock(Id id,ArrayList<String> param){
 this.id=id;
 this.param.addAll(param);   
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
   Main.getErr().add("LevBlock", e);
  }
  return(s);
 }

 public Id getId(){
  return this.id;
 }
}
