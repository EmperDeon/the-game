package level;
import java.io.File;
import level.chunk.Chunk;
import level.chunk.ChunkContainer;
import level.chunk.ChunkId;
import utils.Options;
import utils.TermEx;
public class Level {

public Options options;    
private final int chpr = 8;
//private final ChunkContainer lch;
private final ChunkContainer rch;
private final String name;
public Boolean loaded = false;
public ChunkId pos = new ChunkId(0,0);

public Level(String name) throws TermEx{  
 File f = new File(main.Main.mdir + "saves/"+name+"/rg");
 if(f.canRead()&&f.listFiles()!=null){ // Created ?   
  this.name = name;
  this.rch = new ChunkContainer(main.Main.mdir + "saves/"+name+"/");
  load(main.Main.mdir + "saves/"+name+"/");
 }else{   
  this.name=name;
  
  options = new Options(new String[]{
                "name:"+name,
                "lchunks:", 
                "pos:0,0" // coord chunk with player
            }, main.Main.mdir + "saves/"+name+"/level.db");
  
  this.rch = new ChunkContainer(main.Main.mdir + "saves/"+name+"/rg/");
  //this.lch = new ChunkContainer(main.Main.mdir + "saves/"+name+"/");
  

  //save();
  this.loaded = true;
 }    
}

public void render(){
 
}

public final void load(String dir){
 File f = new File(dir+"level.db");   
 if(f.canRead()){ 
     options = new Options(dir+"level.db");
     
     pos.x = Integer.parseInt(options.get("pos")[0]);
     pos.y = Integer.parseInt(options.get("pos")[1]);
 }else{
     options = new Options(new String[]{                
                "name:"+name
            },dir+"level.db"); 
 }

 this.rch.load(new ChunkId(0,0),8);
 
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
