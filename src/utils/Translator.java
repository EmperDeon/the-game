package utils;

import java.io.*;
import java.util.TreeMap;

public class Translator {

 private final TreeMap<String, String> map = new TreeMap<>();

 public Translator () {

 }

 public void add ( String k, String v ) {
  this.map.put(k, v);
 }

 public String get ( String k ) {
  return map.get(k);
 }

 public void loadFromFile ( String file ) {
  String line;
  String[] t;
  try ( BufferedReader in = new BufferedReader(new FileReader(
     main.Main.DIR + file)) ) {
   line = in.readLine();
   while ( line != null ) {
    t = line.split(":");
    add(t[0], t[1]);
    line = in.readLine();
   }
  } catch ( Exception ex ) {

  }
 }
}
