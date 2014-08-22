package render.gui.widgets;

import org.fenggui.GameMenuButton;
import render.gui.Display;

public class Loading extends GameMenuButton{

  public Loading(Display display){
  super("/usr/games/game/res/init.png","/usr/games/game/res/init.png");
  setWidth( display.getWidth ()+10);
  setHeight(display.getHeight()+10);
  setXY(0,0);
 }
 
 public Loading( String lowlightFile , String highlightFile ) {
  super(lowlightFile , highlightFile);
 }
 
 @Override
 public void resize(int w, int h){
  setWidth( w+10);
  setHeight(h+10);
 }
}
