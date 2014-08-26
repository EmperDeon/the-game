package render.gui.widgets;

import java.util.ArrayList;
import org.fenggui.binding.render.Graphics;
import utils.vec.Vec2;

public class WidgetsContainer extends StdContainer{
 private ArrayList<ArrayList<Integer>> rowsid;
 private Vec2<Integer> space;
 private final Vec2<Integer> std = new Vec2<>(200,20);
 public WidgetsContainer(){}
 public WidgetsContainer(Vec2<Integer> space, Vec2<Integer> std, ArrayList<ArrayList<Integer>> rowsid){
  this.rowsid = rowsid;
  this.space  = space;
 // this.std    = std;
 }
 public void addW(StdWidget w){
  w.setWidth(200);
  w.setHeight(20);
  cont.add(w);
  resize(display.getWidth(),display.getHeight());
 }
 
 @Override public void resize ( int w , int h ) {
  Integer rows = rowsid.size();
  Integer stdx = std.gX();
  Integer stdy = std.gY();
  Integer spacex = 20,spacey = 20;
  rowsid.stream().forEach((arr ) -> {
   for(int cols = 0 ; cols < arr.size() ; cols++){
    StdWidget c = cont.get(cols);
    c.setX((w - rows*stdx - rows*(spacex -1) + 0)/2);
    c.setY((h - cols*stdy - cols*spacey  )/2); 
   }
  });
 }
 @Override public void paint ( Graphics g ) {
  this.cont.stream().forEach(( w ) -> {if(visible)w.paint(g);});
 }

 @Override public void updateMinSize () {}
 
}
