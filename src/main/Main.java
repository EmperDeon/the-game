package main;

import basemod.CoreModContainer;
import basemod.ModContainer;
import level.Level;
import player.Player;
import utils.Options;
import utils.Error;
import utils.MTimer;
import utils.vec.Vec3f;

public class Main implements Runnable{
 private static final Error err=new Error();
 private final Options opt = new Options("game/options.db",err);  
 private final MTimer timer = new MTimer();
 private final Level level = new Level("test",err);
 private final Player player = new Player(new Vec3f(0,0,0),level);
 private final ModContainer mods= new ModContainer("game/mods");
 private final CoreModContainer core = new CoreModContainer("game/coremods");
//public Tex Tex;  
  
 public static Error getErr(){
  return err;
 }
  
 public void init(){
  level.save();
 // opt.save();
 }
 
 @Override
 public void run(){
  init();

//  timer.start(player, level);
  
  destroy();
 }
 
 

 public void destroy(){
  err.check();
  level.save();
 }
 
   public static void main(String[] args){
 // MyRobot.main(null);
  new Thread(new Main()).start();
  //new Thread(new render.Renderer()).start();
 }   
}
