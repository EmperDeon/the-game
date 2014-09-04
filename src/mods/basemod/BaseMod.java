package mods.basemod;
import java.util.ArrayList;
import render.Tex;
import utils.TId;
import utils.vec.Vec4;
public class BaseMod {
public ArrayList<Block[]>   blocks;
public ArrayList<Tex>  Texs;
public ArrayList<Item>    items;
public Boolean needparam = false;
public int id=-1;


public void init(){
    
}
public void action(int id,int id2,int act,Vec4<Integer> coord){
 
    
    
 }

public Tex getTex(TId id){
 return Texs.get(id.getTid());
}
}