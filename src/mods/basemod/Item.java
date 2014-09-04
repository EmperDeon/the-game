package mods.basemod;
public class Item {
    
public Boolean Texeq;
public String parametres[];
public String type;
public int modid;

public Item Item(Boolean Texeq,String[] param){
    this.Texeq=Texeq;
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
