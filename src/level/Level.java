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

private Options options;    
    
private final ArrayList<OctChunk> ch = new ArrayList();
private final ArrayList<int[][]> nom = new ArrayList();
private final String name;
private final Error err;

public Level(String name, Boolean created,Error err){
 this.err=err;   
 if(created){ // Создано ?   
  this.name=name;   
  load("game/saves/"+name+"/");
 }else{   
  this.name=name;
  
  for(int x = 0;x<3;x++)  
   for(int y = 0;y<3;y++)
    ch.add(new OctChunk(name, "region"+x+"-"+y,x,y)); 
 }    
}

public final void load(String dir){
 options = new Options(dir+"level.db",this.err);
 
 File f= new File(dir+"rg/");
 File[] dr = f.listFiles();
 //System.out.println(f.listFiles());
 
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
}
