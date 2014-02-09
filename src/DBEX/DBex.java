package DBEX;
import org.leveldb.DB;
import org.leveldb.Options;
import static org.leveldb.impl.Iq80DBFactory.*;
import java.io.*;
public class DBex {
    
public static void main(String[] args) {
 String[] opt=Load();
 for(int i=0;i<opt.length-1;i++)
  if(opt[i]!=null) System.out.println(opt[i]);
 }

static String[] Load(){
 String[] ret=new String[21]; 
 String[] opt={"opt1","opt2","opt3","opt4","opt5"};
 Options options = new Options();
 options.createIfMissing(true); 
 try (DB db = factory.open(new File("options.db"), options)) {
  for(int i=0;i<4;i++){ 
   String tmp=asString(db.get(bytes(opt[i])));
   Boolean finded=false;
   if(tmp.indexOf("",1)==1){ finded=true;}
  }
 } catch (IOException ex) {
  System.out.println(ex); 
 }   
 return ret;
 }
}
//  db.put(bytes(opt[i]),bytes("test"+i));
