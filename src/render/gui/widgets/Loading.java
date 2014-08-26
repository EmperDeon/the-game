package render.gui.widgets;

public class Loading extends StdWidget{
 public Loading(){
  super("res/init.png","res/init.png","res/init.png");
 }

 @Override
 public void resize(int w, int h){
   setXY((w - 155)/2,(h - 40)/2);
   setWidth(155);
   setHeight(40);
 }
}
