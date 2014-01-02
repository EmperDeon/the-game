package main;
import main.db.DB;
import main.db.OptionsDB;
import static main.db.impl.Iq80DBFactory.*;
import java.io.*;

public class Options {
 String[][] Load(){
  String[][] ret=new String[1][1]; 
  OptionsDB options = new OptionsDB();
  options.createIfMissing(true); 
  // Опции \
  try (DB db = factory.open(new File("options.db"), options)) {
  
   ret[1][1]=asString(db.get(bytes("Kluch")));

  } catch (IOException ex) {
   System.out.println(ex); 
  }   
  // Опции /
  // Моды \
  try (DB db = factory.open(new File("mod.db"), options)) {
  
   ret[1][1]=asString(db.get(bytes("Kluch")));
  
  } catch (IOException ex) {
   System.out.println(ex); 
  }  
  // Моды /
  return ret;
 }
 
  void Save(String[][] in){
  OptionsDB options = new OptionsDB();
  options.createIfMissing(true); 
  // Опции \
  try (DB db = factory.open(new File("options.db"), options)) {
  
   db.put(bytes("Kluch"), bytes("Znach"));
  
  } catch (IOException ex) {
   System.out.println(ex); 
  }   
  // Опции /
 }
}
