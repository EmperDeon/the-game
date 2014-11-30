package mods.basemod;

public class Model {

 private final String file;

 public Model ( String file ) {
  this.file = file;
 }

 @Override
 public String toString () {
  return "Model: " + file;
 }

 public String getFile () {
  return file;
 }
}
