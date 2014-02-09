package basemod;

import render.tile.Tile;

public class Block {
    
public Tile[] tiles;
public Integer modid;
public String parametres[];
//public Guiblock gui;

public Block Block(Tile[] tiles,String[] param){
    this.tiles=tiles;
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
