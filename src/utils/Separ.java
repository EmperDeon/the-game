package utils;
public class Separ {
 
 public static String[] getval(String opt){
  return (opt.split(":"))[1].split(",");
 }
 
  public static String getkey(String opt){
  return (opt.split(":"))[0];
 }
}
