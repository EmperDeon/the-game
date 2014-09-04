package utils;
import java.util.Timer;
import java.util.TimerTask;
import level.Level;
import level.player.Player;

public class MTimer {
 private final Timer Tplayer = new Timer();   
 private final Timer Tlevel  = new Timer();
 private Player player;
 private Level level;
 
 public void start(Player player,Level level){
  this.player=player;
  this.level =level;
  
  Cplayer cplayer=new Cplayer();
  Tplayer.scheduleAtFixedRate(cplayer, 0, 1000);
  
  Clevel clevel=new Clevel();
  Tlevel.scheduleAtFixedRate(clevel, 0, 50);
 }

 public class Cplayer extends TimerTask {
  @Override
  public void run(){
   player.tick();
  }
 }
 
  class Clevel extends TimerTask {
  @Override
  public void run(){
 //  level.tick();
  }
 }
}
