package render.gui;
import java.util.ArrayList;
import java.util.TreeMap;
import org.fenggui.IWidget;
import org.fenggui.binding.render.Pixmap;
import utils.GuiId;
import utils.vec.Vec2;
public class Layout {
 private final TreeMap<GuiId , ArrayList<Integer>> map = new TreeMap<>();
 private final TreeMap<GuiId , Pixmap> back = new TreeMap<>();
 private final int columns = 2;
 private final Vec2<Integer> space = new Vec2<>(0 , 0);
 private final Vec2<Integer> stdwh = new Vec2<>(0 , 0);
 private GuiId curr = new GuiId(0);

 public Layout ( int sp1 , int sp2 , int w , int h ) {
  space.sX(sp1);
  space.sY(sp2);
  stdwh.sX(w);
  stdwh.sY(h);
 }
 public Layout () {
  space.sX(15);
  space.sY(15);
  stdwh.sX(150);
  stdwh.sY(20);
 }

 public synchronized void doLayout ( ArrayList<IWidget> cont , int w , int y ) {
  
 }

 public void changeGui ( GuiId id , ArrayList<IWidget> cont ) {
  this.curr = id;
  cont.stream().forEach(w -> {
   w.setVisible(false);
  });

  map.get(id).stream().forEach(i -> {
   cont.get(i).setVisible(true);
  });
 }
 public void add ( GuiId k , Integer v , IWidget w ) {
  if ( !map.containsKey(k) ) {
   map.put(k , new ArrayList<>());
  }

  if ( !k.equals(curr) ) {
   w.setVisible(false);
  }
  map.get(k).add(v);
 }
 public Pixmap getBack () {
  return back.get(curr);
 }
 public void addBack ( GuiId k , Pixmap v ) {
  back.put(k , v);
 }
}
