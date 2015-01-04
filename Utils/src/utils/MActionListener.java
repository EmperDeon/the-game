package utils;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class MActionListener {

 private final TreeMap<String , ActE> cont = new TreeMap<>();

 public MActionListener () {

 }

 public void add ( String name , ActionListener e ) {
  if ( !cont.containsKey(name) ) {
   cont.put(name , new ActE());
  }
  cont.get(name).add(e);
 }

 public void addT ( String name , Integer delay , ActionListener e ) {
  if ( !cont.containsKey(name) ) {
   cont.put(name , new ActE());
  }
  cont.get(name).add(delay , e);
 }

 private static class ActE {

  protected final Timer timer = new Timer();
  protected final ArrayList<ActionListener> arr = new ArrayList<>();

  public synchronized void add ( Integer delay , ActionListener e ) {
   timer.scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run () {
     arr.stream().forEach(( ActionListener e ) -> {
      e.actionPerformed(null);
     });
    }
   } , 10 , delay);
   arr.add(e);
  }

  public synchronized void add ( ActionListener e ) {
   arr.add(e);
  }
 }
}
