package level;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import level.chunk.OctChunk;

public class Level {
private final ArrayList<OctChunk> ch=new ArrayList();
private String name;

public Level(String name, Boolean created){
 if(created){ // Создано ?   
  this.name=name;   
  load("/game/save/"+name+"/");
 }else{
  this.name=name;   
  for(int i = 0;i<9;i++)
   ch.add(new OctChunk(name, "octch"+i+".rg")); 
 }    
}

public final void load(String dir){
 File f= new File(dir+"rg/");
 File[] dr = f.listFiles();
 for (File dr1 : dr) {
  try {
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(dr1));
   ch.add((OctChunk)serial.readObject());
  }catch (IOException | ClassNotFoundException ex) {
   System.out.println("Ошибка при загрузке объекта: \n"+ex);
  }  
 }
}


public void save(){
 for(int i=0;i<ch.size();i++)
 try {     
  File fos = new File(ch.get(i).getD());
  if(!fos.canWrite())
   fos.mkdirs();
  fos.delete();
  ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(ch.get(i).getD()+ch.get(i).getF()));
  serial.writeObject(ch.get(i));
  serial.flush();
 } catch (IOException ex) {
 System.out.println("Ошибка при сохранении объекта: \n"+ex);
}
}

public void tick(){
 for(int i=0;i<ch.size();i++)
  ch.get(i).tick();  
 
}
}
