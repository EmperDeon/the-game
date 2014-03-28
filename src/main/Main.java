package main;

import basemod.CoreModContainer;
import basemod.ModContainer;
import level.Level;
import player.Player;
import utils.Options;
import utils.Error;
import utils.MTimer;

public class Main implements Runnable{
 private static final Error err=new Error();
 private final Options opt = new Options("game/options.db",err);  
 private final MTimer timer = new MTimer();
 private Level level = new Level("test",err);
 private Player player;
 private ModContainer mods= new ModContainer("game/mods");
 private CoreModContainer core = new CoreModContainer("game/coremods");
//public Tex Tex;  
 
 public static void main(String[] args){
 // MyRobot.main(null);
  new Thread(new Main()).start();
  new Thread(new render.Renderer()).start();
 } 
 
 public static Error getErr(){
  return err;
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
