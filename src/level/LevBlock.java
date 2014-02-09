package level;
public class LevBlock {
    
public int subid;
public int blockid;
public int modid;
public String parameters[];
//public Guiblock gui;

public LevBlock Block(int mid,int bid,int sid,String[] param){
 this.modid=mid;
 this.blockid=bid;
 this.subid=sid;
 this.parameters=param;   
 return(this);
}

public String getparam(int i){
  try{
   return(parameters[i]);
  }catch(Exception e){
   return("Err");
  }
 }
}
