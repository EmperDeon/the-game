package basemod;

import utils.Id;

public class Block {
    
public Id Texid[];
public Integer modid;
public String parametres[];
//public Guiblock gui;

public Block Block(Id[] Texs,String[] param){
    this.Texid=Texs;
    this.parametres=param;
    
    return(this);
}

public String getparam(int i){
   String s;
   try{
    s=parametres[i];
   }catch(Exception e){
    s="Error";
   }
   return(s);
  }
}
