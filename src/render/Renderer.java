package render;

import level.Level;
import player.Player;
import render.gui.Gui;
import utils.MTimer;
import utils.TermEx;

public class Renderer implements Runnable{
public static boolean running = true;
public int width=800,height=600;
public Level level;

private MTimer timer;
private Player player;

private final Gui gui = new Gui(this);

public void init() throws TermEx{
 // this.level  = new Level("World1"); 
 // this.player = new Player(new Vec3f(0,0,0),level);
 // this.timer  = new MTimer();
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

public void render(){
// level.render();
}

public void initfinal(){
 this.gui.initfinal();
}
    
}
