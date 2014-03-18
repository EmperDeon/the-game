package main;

import level.Level;
import player.Player;
import utils.Options;
import utils.Error;
import utils.MTimer;

public class Main implements Runnable{
 private final Error err=new Error();
 private final Options opt = new Options("game/options.db",err);  
 private final MTimer timer = new MTimer();
 private Level level = new Level("test",err);
 private Player player;
//public Tile tile;  
 
 public static void main(String[] args){
  new Thread(new Main()).start();
  new Thread(new render.Renderer()).start();
 } 
  
 @Override
 public void run(){
  init();

//  timer.start(player, level);
  
  destroy();
 }
 
 public void init(){

  opt.save();
 } 

 public void destroy(){
  err.check();
  level.save();
 }
    
}
