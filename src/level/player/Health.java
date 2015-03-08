package level.player;

public class Health {

 public int arml, armr, legl, legr, body, head;

 public Health ( int arml, int armr, int legl, int legr, int body, int head ) {
  this.arml = arml;
 }
 
 public void init (Health t){
  this.arml = t.arml;
  this.armr = t.armr;
  this.legl = t.legl;
  this.legr = t.legr;
  this.body = t.body;
  this.head = t.head;
 }
}
