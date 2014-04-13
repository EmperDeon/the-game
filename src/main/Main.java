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
 public static Thread Tm = new Thread(new Main());
 public static Thread Tr = new Thread(new render.Renderer());
 public boolean running = true;
 public final static String mdir = "/usr/games/game/"; 
 
 private Options opt;  
 private MTimer timer;
 private Level level;
 private Player player;
 private ModContainer mods;
 private CoreModContainer core;
  
 public void init() throws TermEx{ 
  this.level  = new Level("World1"); 
  this.timer  = new MTimer();
  this.opt    = new Options(mdir + "options.db");
  this.player = new Player(new Vec3f(0,0,0),level);
  this.core   = new CoreModContainer();
  this.mods   = new ModContainer();
 }
 
 @Override
 public void run(){
  try{    
   init();

  timer.start(player, level);
//   while (running){  
//    Tm.wait();
//   }
  
   destroy();
  }catch(TermEx ex){//System.getProperties().list(System.out);
   System.err.println(ex.s);
   Tm.interrupt();
  } // | InterruptedException
 }
  


 public void destroy(){
  err.check();
  
  //level.save();
 }
 
 public static void main(String[] args){
  Tm.start();
  //Tr.start();
 }   
}
