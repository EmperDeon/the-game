package basemod;
public class Item {
    
public Boolean tileeq;
public String parametres[];
public String type;
public int modid;

public Item Item(Boolean tileeq,String[] param){
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
