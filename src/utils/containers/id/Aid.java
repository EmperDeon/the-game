package utils.containers.id;

public class Aid extends Mid {

 private final String action;

 public Aid ( Mid t, String a ) {
  super(t);
  this.action = a;
 }

 public Aid ( String m, String b, String s, String a ) {
  super(m, b, s);
  this.action = a;
 }

 public String getAction () {
  return action;
 }
}
