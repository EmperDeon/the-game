package level;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import level.chunk.ChunkContainer;
import level.chunk.OctChunk;
import utils.Error;
import utils.Options;
public class Level {

public Options options;    
private int chpr = 32;
private final ChunkContainer lch = new ChunkContainer();
private final ChunkContainer rch = new ChunkContainer();
private final String name;
private final Error err;

private void loadCh(){}

public Level(String name,Error err){
 this.err=err;   
 File f = new File("game/saves/"+name+"/rg");
 if(f.canRead()&&f.listFiles()[0]!=null){ // Created ?   
  this.name=name;   
  load("game/saves/"+name+"/");
 }else{   
  this.name=name;
  
  options = new Options(new String[]{
                "name:"+name,
                "lchunks:", 
                "pos:0,0" // coord chunk with player
            },"game/saves/"+name+"/level.db",this.err);
  OctChunk ch;
  
  int i;
  
  for(int x = -(chpr/8);x<=(chpr/8);x++)  
   for(int y = -(chpr/8);y<=(chpr/8);y++){
    ch = new OctChunk(name,x,y,options); 
    addChid(ch);
   }
 }    
}

public final void addChid(OctChunk ch){
 int i = 0;   
 for(int cx=0;cx<8;cx++)
  for(int cy=0;cy<8;cy++){
   rch.add(i, ch.getCh(cx, cy));
   i++;    
  }
}

public void render(){
 
}

public final void load(String dir){
 File f = new File(dir+"level.db");   
 if(f.canRead()){ options = new Options(dir+"level.db",this.err);}
            else{ options = new Options(new String[]{                
                "name:"+name
            },dir+"level.db",this.err); }
 
 rch.loadAll(dir);
 
}


public void save(){
 options.save();
 
 rch.save();
 ArrayList<OctChunk> ch = new ArrayList();
 
 for(int i=0;i<ch.size();i++)
 try {     
  File f = new File(ch.get(i).getD());
  if(!f.canWrite())
  f.mkdirs();
  f.delete();
  
  ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(ch.get(i).getD()));
  serial.writeObject(ch.get(i));
  serial.flush();
 } catch (IOException ex) {
  err.add("Level.save()",ex);
 }
}

/*public void tick(){
 for(int i=0;i<ch.size();i++)
  ch.get(i).tick();  
 
}

public OctChunk get(int x){
 return ch.get(x);
}*/
}
