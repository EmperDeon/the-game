package render;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import level.Level;
import player.Player;
import render.gui.Gui;
import utils.vec.Vec2f;
import utils.vec.Vec3f;

public class Renderer implements Runnable{
public static boolean running = true;
public boolean b=true;
public static Player player;
public static GL2 gl;
public static GLU glu;
public int width=800,height=600;
private Level level;
private final Gui gui = new Gui();
private final Vec3f plcoord = new Vec3f(0,0,0);
private final Vec2f cmcoord = new Vec2f();

private String sec="";
public int fps=0;
public void init(){
 gui.frame();
}

public static void destroy(){
 
}
@Override
public void run(){
 init();
 
 destroy();
}

    
}
