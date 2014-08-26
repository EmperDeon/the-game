package render.gui.widgets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.fenggui.Display;
import org.fenggui.IBasicContainer;
import org.fenggui.IWidget;
import org.fenggui.binding.render.Graphics;
import org.fenggui.event.FocusEvent;
import org.fenggui.event.IPositionChangedListener;
import org.fenggui.event.ISizeChangedListener;
import org.fenggui.event.PositionChangedEvent;
import org.fenggui.event.SizeChangedEvent;
import org.fenggui.event.key.KeyAdapter;
import org.fenggui.event.key.KeyPressedEvent;
import org.fenggui.event.key.KeyReleasedEvent;
import org.fenggui.event.key.KeyTypedEvent;
import org.fenggui.event.mouse.MouseAdapter;
import org.fenggui.event.mouse.MouseClickedEvent;
import org.fenggui.event.mouse.MouseDoubleClickedEvent;
import org.fenggui.event.mouse.MouseDraggedEvent;
import org.fenggui.event.mouse.MouseEnteredEvent;
import org.fenggui.event.mouse.MouseExitedEvent;
import org.fenggui.event.mouse.MousePressedEvent;
import org.fenggui.event.mouse.MouseReleasedEvent;
import org.fenggui.event.mouse.MouseWheelEvent;
import org.fenggui.layout.ILayoutData;
import org.fenggui.theme.xml.IXMLStreamableException;
import org.fenggui.theme.xml.InputOutputStream;
import org.fenggui.util.Dimension;
import org.fenggui.util.Point;
import utils.vec.Vec2;

public abstract class StdContainer implements IWidget{
 protected final ArrayList<StdWidget>   cont   = new ArrayList<>();
 protected final TreeMap<String,Object> data   = new TreeMap<>();
 protected final Vec2<Integer>          mcoord = new Vec2<>();
 protected final Vec2<Integer>          xy     = new Vec2<>();
 protected final Vec2<Integer>          wh     = new Vec2<>();
 
 protected String          name;
 protected Boolean         visible = true;
 protected Display         display;
 protected IBasicContainer parent;
 protected ILayoutData     layoutData = null;
 
 public StdContainer(){
  
 }
 @Override public abstract void resize ( int w , int h );
 @Override public abstract void updateMinSize ();
 
 @Override public void paint ( Graphics g ) {this.cont.stream().forEach(( w ) -> {if(visible)w.paint(g);});}

 @Override public void mouseEntered ( MouseEnteredEvent e ) {
  getMouseHook().mouseEntered(e);
 }
 @Override public void mouseExited ( MouseExitedEvent e) {
  getMouseHook().mouseExited(e);
 }
 @Override public void mousePressed ( MousePressedEvent e ) {
  getMouseHook().mousePressed(e);
 }
 @Override public void mouseMoved ( int x , int y ) {
 getMouseHook().mouseMoved(null);
 mcoord.sX(x);
 mcoord.sY(y);
 }
 @Override public void mouseDragged ( MouseDraggedEvent e ) {
  getMouseHook().mouseDragged(e);
 }
 @Override public void mouseReleased ( MouseReleasedEvent e ) {
  getMouseHook().mouseReleased(e);
 }
 @Override public void mouseClicked ( MouseClickedEvent e ) {
  getMouseHook().mouseClicked(e);
 }
 @Override public void mouseDoubleClicked ( MouseDoubleClickedEvent e ) {
  getMouseHook().mouseDoubleClicked(e);
 }
 @Override public void mouseWheel ( MouseWheelEvent e ) {
  getMouseHook().mouseWheel(e);
 }
 @Override public void keyPressed ( KeyPressedEvent e ) {
  getKeyHook().keyPressed(e);
 }
 @Override public void keyReleased ( KeyReleasedEvent e ) {
  getKeyHook().keyReleased(e);
 }
 @Override public void keyTyped ( KeyTypedEvent e ) {
  getKeyHook().keyTyped(e);
 }

 @Override public void focusChanged ( FocusEvent focusEvent ) {}

 @Override public void layout () {}
 @Override public void process ( InputOutputStream stream ) throws IOException ,IXMLStreamableException {}
 
 @Override public void sizeChanged ( SizeChangedEvent event ) {}
 @Override public void positionChanged ( PositionChangedEvent event ) {}

 @Override public void addSizeChangedListener ( ISizeChangedListener l ) {}
 @Override public void removeSizeChangedListener ( ISizeChangedListener l ) {}
 @Override public void addMinSizeChangedListener ( ISizeChangedListener l ) {}
 @Override public void removeMinSizeChangedListener ( ISizeChangedListener l ) {}
 @Override public void addPositionChangedListener ( IPositionChangedListener l ) {}
 @Override public void removePositionChangedListener ( IPositionChangedListener l ) {} 
 @Override public void removedFromWidgetTree () {}
 @Override public void addedToWidgetTree () {}
 
 @Override public boolean isInWidgetTree () {return true;} // ?
 @Override public boolean isExpandable () {return false;}
 @Override public boolean isShrinkable () {return false; }
 @Override public boolean isTraversable () {return false;}


 @Override public String toString(){
  String res =  name+"\n"
         +"contains "+ cont.size() + " elements:" ;
  for(IWidget w : cont)
   res = res + w.toString();
  return res;
 }
 @Override public IWidget clone () throws CloneNotSupportedException {
  return (IWidget)super.clone();
 }

 @Override public int getX () {return xy.gX();}
 @Override public int getY () {return xy.gY();}
 @Override public Point getPosition () {return new Point(xy.gX(),xy.gY());}
 @Override public IBasicContainer getParent () {return this.parent;}
 @Override public Dimension getSize () {return new Dimension(wh.gX(),wh.gY());}
 @Override public Dimension getMinSize () {return new Dimension(wh.gX(),wh.gY());} 
 @Override public Object getData ( String key ) {return this.data.get(key);}
 @Override public boolean isVisible () {return visible;}
 @Override public Display getDisplay () {return display;}
 @Override public int getDisplayX () { return display.getX();}
 @Override public int getDisplayY () { return display.getY();}
 @Override public ILayoutData getLayoutData () {return layoutData; }
 @Override public String getUniqueName () {
  return name; 
 }
 @Override public IWidget getWidget ( int x , int y ) {
  return getSWidget();
 }
 private synchronized StdWidget getSWidget(){ 
  int x,y,w,h;
  for(StdWidget c : cont){
   x = c.getX();
   y = c.getY();
   w = c.getWidth();
   h = c.getHeight();
   if(mcoord.gX() > x && mcoord.gX() < (x+w) && mcoord.gY()>y && mcoord.gY()<(y+h) )
    return c;
  }
  return null;
 }
 private MouseAdapter getMouseHook(){
  StdWidget w = getSWidget();
  if(w != null)
   return w.getMouseHook();
  else
   return new MouseAdapter() {};
 }
 private KeyAdapter getKeyHook(){
  StdWidget w = getSWidget();
  if(w != null)
   return w.getKeyHook();
  else
   return new KeyAdapter() {};
 } 
 
 @Override public void setX ( int x ) {xy.sX(x);}
 @Override public void setY ( int y ) {xy.sY(y);}
 @Override public void setPosition ( Point p ) {xy.sX(p.getX()); xy.sY(p.getY());}
 @Override public void setParent ( IBasicContainer object ) {this.parent=object;}
 @Override public void setSize ( Dimension d ) {wh.sX(d.getWidth()); wh.sY(d.getHeight()); }
 @Override public void setData ( String key , Object data ) {this.data.put(key , data);}
 @Override public void setVisible ( boolean visible ) {this.visible=visible;}
 public void setDisplay(Display d){this.display = d;} 
 public void setLayoutData(ILayoutData layoutData){this.layoutData = layoutData;}
}
