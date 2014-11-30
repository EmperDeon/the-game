package mods.basemod;

public class Speeds {

 private final String str;

 public Speeds ( String str ) {
  this.str = str;
 }

 @Override
 public String toString () {
  return "Speeds: " + str;
 }

 public String getStr () {
  return str;
 }
}
