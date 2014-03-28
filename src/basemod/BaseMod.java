package basemod;
import java.util.ArrayList;
import utils.vec.Vec5i;
import utils.TId;
import render.Tex;
public class BaseMod {
public ArrayList<Block[]>   blocks;
public ArrayList<Tex>  Texs;
public ArrayList<Item>    items;
public Boolean needparam = false;
public int id=-1;


public void init(){
    
}
public void action(int id,int id2,int act,Vec5i coord){
 
    
    
 }

public Tex getTex(TId id){
 return Texs.get(id.getTid());
}
}