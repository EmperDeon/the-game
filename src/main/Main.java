package main;

import basemod.CoreModContainer;
import basemod.ModContainer;
import utils.Error;
import utils.MTimer;
import utils.Options;
import utils.TermEx;

public class Main implements Runnable{
    
 public static final Error err=new Error();
 public static Thread Tm = new Thread(new Main());
 public static Thread Tr = new Thread(new render.Renderer());
 public boolean running = true;
 public final static String mdir = "/usr/games/game/"; 
 
 private Options opt;  
 private MTimer timer;
 private ModContainer mods;
 private CoreModContainer core;
  
 public void init() throws TermEx{ 
  this.opt    = new Options(mdir + "options.db");

  this.core   = new CoreModContainer();
  this.mods   = new ModContainer();
 }
 
 @Override
 public void run(){
  try{    
   init();

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
