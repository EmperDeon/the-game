package level;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import level.chunk.OctChunk;
import utils.Options;
import utils.Error;
public class Level {

public Options options;    
    
private final ArrayList<OctChunk> ch = new ArrayList();
private final ArrayList<int[][]> nom = new ArrayList();
private final String name;
private final Error err;

public Level(String name,Error err){
 this.err=err;   
 File f = new File("game/saves/"+name+"/rg");
 if(f.canRead()&&f.listFiles()[0]!=null){ // Создано ?   
  this.name=name;   
  load("game/saves/"+name+"/");
 }else{   
  this.name=name;
  
  options = new Options(new String[]{
                "name:"+name
            },"game/saves/"+name+"/level.db",this.err);
  
  for(int x = -1;x<=1;x++)  
   for(int y = -1;y<=1;y++)
    ch.add(new OctChunk(name,x,y,options)); 
 }    
}

//public void genOctChunk(int x,int y){
// 
//}

public final void load(String dir){
 File f = new File(dir+"level.db");   
 if(f.canRead()){ options = new Options(dir+"level.db",this.err);}
            else{ options = new Options(new String[]{
                "name:"+name,
                "",
                ""
            },dir+"level.db",this.err); }
 
 f= new File(dir+"rg/");
 File[] dr = f.listFiles();

 for (File dr1 : dr) {
  try {
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(dr1));
   ch.add((OctChunk)serial.readObject());
  }catch (IOException | ClassNotFoundException ex) {
   err.add("Level.load()",ex);
  }  
 }
}


public void save(){
 options.save();
    
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

public void tick(){
 for(int i=0;i<ch.size();i++)
  ch.get(i).tick();  
 
}

public OctChunk get(int x){
 return ch.get(x);
}
}
