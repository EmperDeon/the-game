package main;

import basemod.CoreModContainer;
import basemod.ModContainer;
import level.Level;
import player.Player;
import utils.Error;
import utils.MTimer;
import utils.Options;
import utils.TermEx;
import utils.vec.Vec3f;

public class Main implements Runnable{
 public static final Error err=new Error();
 
 private final Options opt = new Options("game/options.db");  
 private final MTimer timer = new MTimer();
 private Level level;
 private final Player player = new Player(new Vec3f(0,0,0),level);
 private final ModContainer mods= new ModContainer("game/mods");
 private final CoreModContainer core = new CoreModContainer("game/coremods");
//public Tex Tex;  
  
 public static Error getErr(){
  return err;
 }

 public void init() throws TermEx{
  this.level = new Level("test");   
  level.save();
 }
 
 @Override
 public void run(){
  try{    
   init();

//  timer.start(player, level);
  
   destroy();
  }catch(TermEx ex){
   System.exit(666);
  } 
 }
 
 

 public void destroy(){
  err.check();
  //level.save();
 }
 
   public static void main(String[] args){
 // MyRobot.main(null);
  new Thread(new Main()).start();
  //new Thread(new render.Renderer()).start();
 }   
}
