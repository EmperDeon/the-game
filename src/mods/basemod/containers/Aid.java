package mods.basemod.containers;

public class Aid extends Mid {

 private final String action;

 public Aid ( Mid t , String a ) {
  super(t);
  this.action = a;
 }

 public Aid ( Integer m , Integer b , Integer s , String a ) {
  super(m , b , s);
  this.action = a;
 }

 public String getAction () {
  return action;
 }
}
