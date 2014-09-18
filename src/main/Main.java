package main;

import mods.basemod.containers.CoreModContainer;
import mods.basemod.containers.ModContainer;
import utils.MTimer;
import utils.Options;
import utils.exceptions.TermEx;

public final class Main implements Runnable {

 public final static String mdir = "/usr/games/game/";

 public final static Logger LOG = new Logger();
 public final static Options OPTIONS = new Options(mdir + "options.db");
 public final static IdMap IdMap = new IdMap();
 public final static MTimer timer = new MTimer();
 public final static ModContainer mods = new ModContainer();
 public final static CoreModContainer core = new CoreModContainer();
 public final static render.Render rend = /*
          * new render.Render()
          */ null;
 public final static Main main = new Main();
 public final static Thread Tm = new Thread(main);
 //public final static Thread Tr = new Thread( rend );

 public boolean running = true;

 public void init () throws TermEx , InterruptedException {
  mods.loadDir();
  LOG.addE("Main", new Exception());
  LOG.save();
  //rend.initfinal();
 }

 @Override
 public void run () {
  try {
   init();

//   while (running){  
//    Tm.wait();
//   }
   destroy();
  } catch ( TermEx ex ) {
   System.err.println(ex.s);
   Tm.interrupt();
  } catch ( InterruptedException ex ) {

  }
 }

 public void destroy () {

  //level.save();
 }

 public static void main ( String[] args ) {
  Tm.start();
  // Tr.start();
 }
}
