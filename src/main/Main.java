package main;
import java.util.concurrent.TimeUnit;
import opengl.Opengl;
import opengl.tile.Tile;
import level.Level;
import org.lwjgl.input.Keyboard;
import opengl.Player;
import basemod.Basemod;

public class Main implements Runnable{
private Timer timer;
public boolean exit;
static Level level=new Level();
static Player player;

public Tile tiles[][];
public Basemod[] mods;

public static Player getPL(){
 return Main.player;
} 

public static Level getLV(){
 return Main.level;
};

@Override
public void run() {
timer = new Timer(20.0F);
try{
Opengl opengl = new Opengl();
while (opengl.loaded) {
     try {    
     System.out.println(timer.fps);
     TimeUnit.SECONDS.sleep(1);
     } catch (InterruptedException ex) {
      System.out.println(ex);
     }

if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) break;
}
}catch(Exception e){}
}
public static void main(String[] args){
new Thread(new Main()).start();

}    
}
