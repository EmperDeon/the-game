package level;
import java.io.File;
import level.chunk.Chunk;
import level.chunk.ChunkContainer;
import level.chunk.OctChunk;
import utils.Options;
import utils.TermEx;
import utils.vec.Vec2f;
public class Level {

public Options options;    
private final int chpr = 8;
//private final ChunkContainer lch;
private final ChunkContainer rch;
private final String name;
public Boolean loaded = false;
public Vec2f pos = new Vec2f();

public Level(String name) throws TermEx{  
// File f = new File("game/saves/"+name+"/rg");
// if(f.canRead()&&f.listFiles()[0]!=null){ // Created ?   
//  this.name = name;
//  this.rch = new ChunkContainer("game/saves/"+name+"/",chpr);
//  load("game/saves/"+name+"/");
// }else{   
  this.name=name;
  
  options = new Options(new String[]{
                "name:"+name,
                "lchunks:", 
                "pos:0,0" // coord chunk with player
            },"game/saves/"+name+"/level.db");
  
  this.rch = new ChunkContainer("game/saves/"+name+"/rg/");
  //this.lch = new ChunkContainer("game/saves/"+name+"/");
  
  for(int x = -(chpr/8);x<=(chpr/8);x++)  
   for(int y = -(chpr/8);y<=(chpr/8);y++){
    rch.addAll(new OctChunk(name,x,y,options)); 
   }
  //save();
  this.loaded = true;
 //}    
}

public void render(){
 
}

public final void load(String dir){
 File f = new File(dir+"level.db");   
 if(f.canRead()){ 
     options = new Options(dir+"level.db");
 }else{
     options = new Options(new String[]{                
                "name:"+name
            },dir+"level.db"); 
 }

 
 
 this.rch.loadAll(pos,chpr);
 this.loaded = true;
}


public void save(){
 options.save();
 
 rch.save();

}

/*public void tick(){
 for(int i=0;i<ch.size();i++)
  ch.get(i).tick();  
 
}
*/
 public Chunk get(int x){
 return rch.get(x);
}

}
