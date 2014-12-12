package mods.basemod;

import java.io.Serializable;

public class Speeds implements Serializable {

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
