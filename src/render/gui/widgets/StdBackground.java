package render.gui.widgets;

public class StdBackground extends StdWidget{
 public StdBackground(String s){
  super(s,s,s);
 } 
 
 @Override public void resize(int w, int h){
   setXY(-100, -100);
   setWidth( w+200);
   setHeight(h+200);
 }
}

