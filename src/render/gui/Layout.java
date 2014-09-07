package render.gui;

import java.util.ArrayList;
import java.util.TreeMap;
import org.fenggui.IWidget;
import org.fenggui.binding.render.Pixmap;
import org.fenggui.util.Dimension;
import utils.GuiId;
import utils.vec.Vec2;

public class Layout {

 private final TreeMap<GuiId , GuiE> map = new TreeMap<>();
 private final TreeMap<GuiId , Pixmap> back = new TreeMap<>();
 private final int columns = 1;
 private final Vec2<Integer> space = new Vec2<>(0 , 0);
 private final Vec2<Integer> stdwh = new Vec2<>(200, 40);
 private final Display disp;
 private GuiId curr = new GuiId(0);

 public Layout ( Display disp , int sp1 , int sp2 , int w , int h ) {
  space.sX(sp1);
  space.sY(sp2);
  stdwh.sX(w);
  stdwh.sY(h);
  this.disp = disp;
 }

 public Layout ( Display display ) {
  space.sX(15);
  space.sY(15);
  stdwh.sX(400);
  stdwh.sY(40);
  this.disp = display;
 }

 public synchronized void doLayout () {
  map.values().stream().forEach(( e ) -> {e.doLayout(columns);});
 }
 public synchronized void changeGui ( GuiId id ) {
  this.curr = id;
  disp.getList().stream().forEach(w -> {
   w.setVisible(false);
  });

  map.get(id).list.stream().forEach(i -> {
   disp.getList().get(i).setVisible(true);
  });
 }
 public synchronized void add ( GuiId k , IWidget w ) {
  if ( !map.containsKey(k) ) {
   map.put(k , new GuiE());
  }

  if ( !k.equals(curr) ) {
   w.setVisible(false);
  }
  map.get(k).add(w);

 }
 public Pixmap getBack () {
  return back.get(curr);
 }
 public void addBack ( GuiId k , Pixmap v ) {
  back.put(k , v);
 }
 private class GuiE {
  public final ArrayList<Integer> list = new ArrayList<>();
  public final Vec2<Integer> minwh = new Vec2<>();
  public final Vec2<Integer> xy = new Vec2<>();

  public GuiE () {
  }

  public void add ( IWidget w ) {
   list.add(disp.getList().size());
   minSize();
  }

  private void minSize () {
   int col = list.size();
   if ( col % columns != 0 ) {
    col = col / columns + 1;
   } else {
    col /= columns;
   }
   this.minwh.sY(col * stdwh.gY() + space.gY() * ( col + 1 ));
   this.minwh.sX(columns * stdwh.gX() + space.gX() * ( columns + 1 ));
   xy.sX((disp.getWidth()-minwh.gX())/2);
   xy.sY((disp.getHeight()-minwh.gY())/2);
  }

  public synchronized void doLayout (Integer colum) {
   switch ( colum ) {
   case 1:{
    ArrayList<IWidget> t = new ArrayList<>();
    t.clear();
    list.stream().forEach(( w ) -> {
     t.add(disp.getList().get(w-1));
    });
    int y = 0;
    for(IWidget w : t){
     w.setSize(new Dimension(stdwh.gX(), stdwh.gY()));
     w.setX(xy.gX()+space.gX());
     w.setY(disp.getHeight()-xy.gY() + space.gY()*(y+1) + stdwh.gY()*y);
     y++;
    }
    
   }break;
   case 2:{
    //
   }break;
   default: {
    // do
   }
   }
   minSize();
  }
 }
}
