package render.gui.widgets;

import org.fenggui.GameMenuButton;

public class StandartImage extends GameMenuButton{

  public StandartImage(String text, int x, int y){
  super("/usr/games/game/res/init.png","/usr/games/game/res/init.png");
  System.out.println(getDisplay().getHeight());
  //setText(text);
  //setXY(x,y);
  setHeight(400);
  setWidth(600);
 }
 
 public StandartImage( String lowlightFile , String highlightFile ) {
  super(lowlightFile , highlightFile);
 }
 
}
