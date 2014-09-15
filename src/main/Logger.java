package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger {
 private final ArrayList<LogE> errr = new ArrayList<>();
 private final ArrayList<LogE> warn = new ArrayList<>();
 private final ArrayList<LogE> info = new ArrayList<>();
 private final ArrayList<LogE> debg = new ArrayList<>();
 
 private final ArrayList<LogEx> ex = new ArrayList<>();
 public Logger (){
  
 }
 
 public void addE(String clas, String info){
  errr.add(new LogE(clas,info));
 }
 
 public void addE(String clas, Exception ex){
  this.ex.add(new LogEx(clas,ex));
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
   return  "[" + new SimpleDateFormat().format(date) + "] "+ clas + " - " + ex+" \n";
  }
 }
}
