package main;

public class Main implements Runnable{
//public Tile tile;
@Override
public void run(){
    
   
    
}


    
public static void main(String[] args){
new Thread(new Main()).start();
new Thread(new render.Renderer()).start();
}    
}
