package utils;
public class Separ {
 
 public static String[] getval(String opt){
  return (opt.split(":"))[1].split(",");
 }
}
