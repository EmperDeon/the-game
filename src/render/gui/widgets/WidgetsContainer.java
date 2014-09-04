package render.gui.widgets;

import java.util.ArrayList;
import org.fenggui.Display;
import org.fenggui.binding.render.Graphics;
import utils.vec.Vec2;

public class WidgetsContainer extends StdContainer{
 protected Boolean always = false;
 protected Display display;
 protected Boolean visible = true;
 protected Vec2<Integer>          mcoord = new Vec2<>(0,0);
 protected Vec2<Integer>          xy = new Vec2<>(0,0);
 protected Vec2<Integer>          wh = new Vec2<>(0,0);
 
 public WidgetsContainer(ArrayList<StdWidget> con, Display display){
  this.cont.addAll(con);
  this.display = display;
  
 }
 
 public WidgetsContainer(Display display){
  wh     = new Vec2<>(display.getWidth(), display.getHeight());
  this.display = display;
  this.always = true;
 }
 
 @Override public void addW(StdWidget w){
  if(always)
   cont.add(w);
  else{  
   w.setWidth(200);
   w.setHeight(20);
   cont.add(w);
   resize(display.getWidth(),display.getHeight());
  }
 }
 
 @Override public synchronized void resize ( int w , int h ) {
//  if(!always){
//   Integer stdx = 200;
//   Integer stdy = 20;
//   wh.sX(stdx + 20);// Button.width + 2 * space
//   wh.sY((stdy + 10 ) * cont.size() + 10); // (Button.height + space) + space
//   xy.sX((w - wh.gX())/2);
//   xy.sY((h - wh.gY())/2);
//  
//   int i = 0;
//   for(StdWidget c : cont){
//    c.setX( xy.gX() + 10 ); // Container x + space
//    c.setY(xy.gY() + wh.gY() - (10 + stdy) * i + 10 ); 
//    i++;
//   }
//  }else{
//   wh.sX(w);
//   wh.sY(h);
//   xy.sX(0);
//   xy.sY(0);
//  }
 }
 @Override public void paint ( Graphics g ) {
  if(visible)
   this.cont.stream().forEach(( w ) -> {w.paint(g);});
 }

 @Override public void updateMinSize () {}
 
}
