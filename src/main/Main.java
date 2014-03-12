package main;

import level.Level;

public class Main implements Runnable{
//public Tile tile;
 private Level level;    
@Override
public void run(){
 level = new Level("world1",false);   
 String[] s= utils.Separ.getval("val:ex1,ex2,ex3");
 for(int i=0;i<s.length;i++)
  System.out.println(s[i]);
}


    
public static void main(String[] args){
new Thread(new Main()).start();
//new Thread(new render.Renderer()).start();
}    
}
