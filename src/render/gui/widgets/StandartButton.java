package render.gui.widgets;

import org.fenggui.GameMenuButton;

public class StandartButton extends GameMenuButton{

 public StandartButton(String text, int x, int y){
  super("/usr/games/game/res/stdb.png","/usr/games/game/res/stdbf.png");
  setText(text);
  setXY(x,y);
  setHeight(40);
  setWidth(160);
 }
 
 public StandartButton ( String lowlightFile , String highlightFile ) {
  super(lowlightFile , highlightFile);
 }

}
