package utils;

import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import utils.exceptions.LoggerExc;

public class Logger implements Serializable {

 public enum Type {

  error, warning, info, debug
 }

 private final ArrayList<LogEn> list = new ArrayList<>();

 public Logger () {
  main.Main.ACTIONS.addT("loggerExport", 200, ( ActionEvent e ) -> {
   main.Main.LOG.export("now.log");
  });
 }

 public void addE ( String ex ) {
  this.list.add(new LogEx(new LoggerExc(ex)));
 }

 public void addE ( Exception ex ) {
  this.list.add(new LogEx(ex));
 }

 public void addW ( String info ) {
  this.list.add(new LogE(info, Type.warning));
 }

 public void addI ( String info ) {
  this.list.add(new LogE(info, Type.info));
 }

 public void addD ( String info ) {
  this.list.add(new LogE(info, Type.debug));
 }

 public void save () {
  Date now = new Date();
  String file = "log/" + new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(now) + ".log";
  if ( !new File(file).exists() ) {
   export(file);
  } else {
   export(
      "log/" + new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(now) + "_1.log");
  }
 }

 public void export ( String file ) {
  try ( BufferedWriter out = new BufferedWriter(new FileWriter(
     main.Main.DIR + file)) ) {
   for ( LogEn e : list ) {
    out.write(e.toString() + "\n");
   }
   out.flush();
  } catch ( Exception ex ) {
   ex.printStackTrace();
   System.err.println("Error in class Logger.export(" + file + ")");
  }
 }

 private interface LogEn {
 }

 private class LogE implements LogEn {

  private final String clas;
  private final String info;
  private final Date date;
  private final Type type;

  public LogE ( String info, Type type ) {
   this.clas = Thread.currentThread().getStackTrace()[2].toString();
   this.info = info;
   this.date = new Date();
   this.type = type;
  }

  @Override
  public String toString () {
   switch ( type ) {
    case warning:
     return "warning&[" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(
        date) + "] " + clas + " - " + info;
    case info:
     return "info&[" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date) + "] " + clas + " - " + info;
    default:
     return "debug&[" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date) + "] " + clas + " - " + info;
   }
  }
 }

 private class LogEx implements LogEn {

  private final String clas;
  private final Exception ex;
  private final Date date;

  public LogEx ( Exception info ) {
   this.clas = Thread.currentThread().getStackTrace()[2].toString();
   this.ex = info;
   this.date = new Date();
  }

  @Override
  public String toString () {
   StringBuilder t = new StringBuilder();
   if ( ex instanceof LoggerExc ) {
    t.append(ex.getMessage());
   } else {
    for ( StackTraceElement e : ex.getStackTrace() ) {
     t.append(e.toString());
     t.append("\n");
    }
   }

   return "error&[" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date) + "] Exception in " + clas + " - " + t.
      toString();
  }
 }
}
