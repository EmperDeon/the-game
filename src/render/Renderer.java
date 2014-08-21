package render;

import level.Level;
import player.Player;
import render.gui.Gui;
import utils.MTimer;
import utils.exceptions.TermEx;

public class Renderer implements Runnable{
 public static boolean running = true;
 public Level level;

 private MTimer timer;
 private Player player;

 private Gui gui;

 public void init() throws TermEx{
  this.gui = new Gui();
  gui.setTitle("The Game");
  gui.setVisible(true);
 }
 
 public void render(){
  //
 }
 
 public static void destroy(){
 
 }

 @Override
 public void run(){
  try {
   init();
   
   destroy();
  } catch ( TermEx ex ) {
   Thread.currentThread().interrupt();
  }
 }

 public void initfinal(){
  //this.gui.initfinal();
 }
}
