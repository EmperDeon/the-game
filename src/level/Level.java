package level;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import level.chunk.OctChunk;

public class Level {
private final ArrayList<OctChunk> ch=new ArrayList();

public Level(){
load();
}

public void load(){
 
 for(int i = 0;i<64;i++)
  ch.add(new OctChunk("octch"+i)); 
}

public void load(int[][] m){
 
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
 System.out.println("Ошибка при сериализации объекта: \n"+ex);
}
}

public void tick(){
 for(int i=0;i<ch.size();i++)
  ch.get(i).tick();  
 
}
}
