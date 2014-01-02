package basemod;
public class Block {
    
public Boolean tileeq;
public Integer modid;
public String parametres[];
//public Guiblock gui;

public Block Block(Boolean tileeq,String[] param){
    this.tileeq=tileeq;
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
