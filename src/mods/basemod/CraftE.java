package mods.basemod;

public class CraftE{

 private final Integer type;
 private final String grid;
 private final String elements;

 public CraftE ( Integer type , String grid , String elements ) {
  this.type = type;
  this.grid = grid;
  this.elements = elements;
 }

 public Integer getType () {
  return type;
 }

 public String getGrid () {
  return grid;
 }

 public String getElements () {
  return elements;
 }

}
