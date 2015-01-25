package utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import utils.exceptions.LoggerExc;

public class Logger implements Serializable {
 public enum Type {
  Error, Warning, Info, Debug, All
 }
 private final ArrayList<LogEx> exep = new ArrayList<>();
 private final ArrayList<LogE> warn = new ArrayList<>();
 private final ArrayList<LogE> info = new ArrayList<>();
 private final ArrayList<LogE> debg = new ArrayList<>();
 private final ArrayList<LogEn> all = new ArrayList<>();

 public void addE(String ex) {
  this.exep.add(new LogEx(new LoggerExc(ex)));
 }

 public void addE(Exception ex) {
  this.exep.add(new LogEx(ex));
 }

 public void addW(String info) {
  this.warn.add(new LogE(info, Type.Warning));
 }

 public void addI(String info) {
  this.info.add(new LogE(info, Type.Info));
 }

 public void addD(String info) {
  this.debg.add(new LogE(info, Type.Debug));
 }

 public String getAll() {
  StringBuilder s = new StringBuilder();

  all.addAll(exep);
  all.addAll(debg);
  all.addAll(info);
  all.addAll(warn);

  all.sort((LogEn o1, LogEn o2) -> o1.getDate().compareTo(o2.getDate()));

  all.stream().forEach((e) -> {
   s.append(e.toString());
  });

  all.clear();
  return s.toString();
 }

 public String getE() {
  StringBuilder s = new StringBuilder();
  exep.stream().forEach(x -> {
   s.append(x.toString());
  });
  return s.toString();
 }

 public String getW() {
  StringBuilder s = new StringBuilder();
  warn.stream().forEach(x -> {
   s.append(x.toString());
  });
  return s.toString();
 }

 public String getI() {
  StringBuilder s = new StringBuilder();
  info.stream().forEach(x -> {
   s.append(x.toString());
  });
  return s.toString();
 }

 public String getD() {
  StringBuilder s = new StringBuilder();
  debg.stream().forEach(x -> {
   s.append(x.toString());
  });
  return s.toString();
 }

 public void save() {
  try (ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream(
     new File(main.Main.mdir+"log.log")))) {
   s.writeObject(this);
   s.flush();
  } catch (IOException e) {
   System.out.println("Error" + e.toString());
  }
 }

 private interface LogEn {

  public Date getDate();

  @Override
  public String toString();
 }

 private class LogE implements LogEn, Serializable {

  private final String clas;
  private final String info;
  private final Date date;
  private final Type type;
  public LogE(String info, Type type) {
   this.clas = Thread.currentThread().getStackTrace()[2].toString();
   this.info = info;
   this.date = new Date();
   this.type = type;
   main.Main.logmanager.update1();
  }
  
  @Override
  public String toString() {
   switch (type) {
    case Warning:
     return "<p style=\" color: #ffc100 \" >[" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date) + "] " + clas + " - " + info + "</p>";
    case Info:
     return "<p style=\" color: #009dff \" >[" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date) + "] " + clas + " - " + info + "</p>";
    default:
     return "<p style=\" color: #9f9f9f \" >[" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date) + "] " + clas + " - " + info + "</p>";
   }
  }

  @Override
  public Date getDate() {
   return date;
  }
 }

 private class LogEx implements LogEn, Serializable {

  private final String clas;
  private final Exception ex;
  private final Date date;

  public LogEx(Exception info) {
   this.clas = Thread.currentThread().getStackTrace()[2].toString();
   this.ex = info;
   this.date = new Date();
   main.Main.logmanager.update1();
  }

  @Override
  public String toString() {
   StringBuilder t = new StringBuilder();
   if (ex instanceof LoggerExc) {
    t.append(ex.getMessage());
   } else {
    for (StackTraceElement e : ex.getStackTrace()) {
     t.append(e.toString());
     t.append("\n");
    }
   }

   return "<p style=\" color: red \" >[" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date) + "] Exception in " + clas + " - " + t.
      toString() + "</p>";
  }

  @Override
  public Date getDate() {
   return date;
  }
 }
}
