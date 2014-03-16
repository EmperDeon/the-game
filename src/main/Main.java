package main;

import level.Level;
import utils.Options;
import utils.Error;

public class Main implements Runnable{
//public Tile tile;
 private Level level; 
 private Error err=new Error();
@Override
public void run(){
 level = new Level("test",err);

 level.save();

 Options op = new Options("game/options.db",err); 
 op.save();
 
 err.check();
}


    
public static void main(String[] args){
new Thread(new Main()).start();
//new Thread(new render.Renderer()).start();
}    
}
