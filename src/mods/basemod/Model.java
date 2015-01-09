package mods.basemod;

import java.io.Serializable;

public class Model implements Serializable {

 private final String file;

 public Model ( String file ) {
  this.file = file;
 }

 public void load () {

 }

 @Override
 public String toString () {
  return "Model: " + file;
 }

 public String getFile () {
  return file;
 }
}
