package main;

import basemod.CoreModContainer;
import basemod.ModContainer;
import utils.Error;
import utils.MTimer;
import utils.Options;
import utils.TermEx;

public class Main implements Runnable{
    
 public static final Error err=new Error();
 
 public final static render.Renderer rend = new render.Renderer();
 public final static Main main = new Main();
 public final static Thread Tm = new Thread( main );
 public final static Thread Tr = new Thread( rend );
 
 public boolean running = true;
 public final static String mdir = "/usr/games/game/"; 
 
 private Options opt;  
 private MTimer timer;
 private ModContainer mods;
 private CoreModContainer core;
  
 public void init() throws TermEx, InterruptedException{ 
  this.opt    = new Options(mdir + "options.db");

  this.core   = new CoreModContainer();
  this.mods   = new ModContainer();
  
  Thread.sleep(1500);
  rend.initfinal();
 }
 
 @Override
 public void run(){
  try{    
   init();

//   while (running){  
//    Tm.wait();
//   }
  
   destroy();
  }catch(TermEx ex){
   System.err.println(ex.s);
   Tm.interrupt();
  } catch ( InterruptedException ex ) {
   
  } 
 }
  


 public void destroy(){
  err.check();
  
  //level.save();
 }
 
 public static void main(String[] args){
  Tm.start();
  Tr.start();
 }   
}
