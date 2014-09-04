package mods.basemod;

import utils.Iid;

public class Block {
    
public Iid Texid[];
public Integer modid;
public String parametres[];
//public Guiblock gui;

public Block Block(Iid[] Texs,String[] param){
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
