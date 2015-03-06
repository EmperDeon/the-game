package mods.basemod;

import java.util.*;

public class CraftE {

 private final Integer type;
 private final String grid;
 private final String elements;
 private final ArrayList<String> param;

 public CraftE ( Integer type, String grid, String elements, String... param ) {
  this.type = type;
  this.grid = grid;
  this.elements = elements;
  this.param = new ArrayList<>();
  this.param.addAll(Arrays.asList(param));
 }

 public CraftE ( Integer type, String grid, String elements, List param ) {
  this.type = type;
  this.grid = grid;
  this.elements = elements;
  this.param = new ArrayList<>();
  this.param.addAll(param);
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

 public String getParams () {
  StringBuilder s = new StringBuilder();
  param.stream().forEach(( t ) -> {
   s.append(t);
  });
  return s.toString();
 }

}
