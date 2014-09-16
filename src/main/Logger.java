package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger {
 private final ArrayList<LogEx> exep = new ArrayList<>();
 private final ArrayList<LogE> warn = new ArrayList<>();
 private final ArrayList<LogE> info = new ArrayList<>();
 private final ArrayList<LogE> debg = new ArrayList<>();
 
 
 public Logger (){
  
 }
 
 public void addE(String clas, Exception ex){
  this.exep.add(new LogEx(clas,ex));
 }
 public void addW(String clas, String info){this.warn.add(new LogE(clas, info));}
 public void addI(String clas, String info){this.info.add(new LogE(clas, info));}
 public void addD(String clas, String info){this.debg.add(new LogE(clas, info));}
  
 public String getE(){StringBuilder s = new StringBuilder(); exep.stream().forEach(x -> {s.append(x.toString());}); return s.toString();}
 public String getW(){StringBuilder s = new StringBuilder(); warn.stream().forEach(x -> {s.append(x.toString());}); return s.toString();}
 public String getI(){StringBuilder s = new StringBuilder(); info.stream().forEach(x -> {s.append(x.toString());}); return s.toString();}
 public String getD(){StringBuilder s = new StringBuilder(); debg.stream().forEach(x -> {s.append(x.toString());}); return s.toString();}
 
  public void save(){
  try(ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream(new File("/usr/games/game/log.log")))){
   s.writeObject(this);
   s.flush();
  }catch(IOException e){}
 }
 
 
 private class LogE {
  private final String clas;
  private final String info;
  private final Date date;
  
  public LogE(String clas, String info){
   this.clas = clas;
   this.info = info;
   this.date = new Date();
  }        
  
  @Override public String toString(){
   return  "[" + new SimpleDateFormat().format(date) + "] "+ clas + " - " + info+" \n";
  }
 }
 private class LogEx {
  private final String clas;
  private final Exception ex;
  private final Date date;
  
  public LogEx(String clas, Exception info){
   this.clas = clas;
   this.ex = info;
   this.date = new Date();
  }        
  
  @Override public String toString(){
   StringBuilder t = new StringBuilder();
   
   for(StackTraceElement e : ex.getStackTrace())
    t.append(e.toString());
   
   return  "[" + new SimpleDateFormat().format(date) + "] "+ clas + " - " + t.toString()+" \n";
  }
 }
}
