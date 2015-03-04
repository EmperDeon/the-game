package render.gui.widgets;

public class Loading extends StdWidget {

 public Loading () {
  super("res/init.png", "res/init.png", "res/init.png");
  setXY((getDisplay().getWidth() - 155) / 2,
        (getDisplay().getHeight() - 40) / 2);
  setWidth(155);
  setHeight(40);
 }
}
