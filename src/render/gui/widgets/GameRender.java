package render.gui.widgets;

import java.io.IOException;
import java.util.TreeMap;
import org.fenggui.*;
import org.fenggui.binding.render.Graphics;
import org.fenggui.event.*;
import org.fenggui.event.key.*;
import org.fenggui.event.mouse.*;
import org.fenggui.layout.ILayoutData;
import org.fenggui.theme.xml.*;
import org.fenggui.util.*;
import render.Render;
import render.Render.Hooks;

public class GameRender implements IWidget {

 private Display parent;
 private final Render rend = main.Main.rend;
 private final Hooks hook;
 private boolean visible = false;
 private final TreeMap<String, Object> map = new TreeMap<>();

 public GameRender () {
  hook = rend.gethook();
 }

 @Override
 public ILayoutData getLayoutData () {
  return null;
 }

 @Override
 public IBasicContainer getParent () {
  return parent;
 }

 @Override
 public IWidget clone () throws CloneNotSupportedException {
  return (IWidget) super.clone();
 }

 @Override
 public void mouseEntered ( MouseEnteredEvent e ) {
  hook.mouseEntered(e);
 }

 @Override
 public void mouseExited ( MouseExitedEvent e ) {
  hook.mouseExited(e);
 }

 @Override
 public void mousePressed ( MousePressedEvent e ) {
  hook.mousePressed(e);
 }

 @Override
 public void mouseMoved ( int displayX, int displayY ) {
  hook.mouseMoved(displayX, displayY);
 }

 @Override
 public void mouseDragged ( MouseDraggedEvent e ) {
  hook.mouseDragged(e);
 }

 @Override
 public void mouseReleased ( MouseReleasedEvent e ) {
  hook.mouseReleased(e);
 }

 @Override
 public void mouseClicked ( MouseClickedEvent e ) {
  hook.mouseClicked(e);
 }

 @Override
 public void mouseDoubleClicked ( MouseDoubleClickedEvent e ) {
  hook.mouseDoubleClicked(e);
 }

 @Override
 public void mouseWheel ( MouseWheelEvent e ) {
  hook.mouseWheel(e);
 }

 @Override
 public void keyPressed ( KeyPressedEvent e ) {
  hook.keyPressed(e);
 }

 @Override
 public void keyReleased ( KeyReleasedEvent e ) {
  hook.keyReleased(e);
 }

 @Override
 public void keyTyped ( KeyTypedEvent e ) {
  hook.keyTyped(e);
 }

 @Override
 public void focusChanged ( FocusEvent e ) {
  hook.focusChanged(e);
 }

 @Override
 public void sizeChanged ( SizeChangedEvent event ) {
  rend.resizeW();
 }

 @Override
 public void positionChanged ( PositionChangedEvent event ) {
 }

 @Override
 public void addSizeChangedListener ( ISizeChangedListener l ) {
 }

 @Override
 public void removeSizeChangedListener ( ISizeChangedListener l ) {
 }

 @Override
 public void addMinSizeChangedListener ( ISizeChangedListener l ) {
 }

 @Override
 public void removeMinSizeChangedListener ( ISizeChangedListener l ) {
 }

 @Override
 public void addPositionChangedListener ( IPositionChangedListener l ) {
 }

 @Override
 public void removePositionChangedListener ( IPositionChangedListener l ) {
 }

 @Override
 public int getDisplayX () {
  return 0;
 }

 @Override
 public int getDisplayY () {
  return 0;
 }

 @Override
 public Display getDisplay () {
  return parent;
 }

 @Override
 public IWidget getWidget ( int x, int y ) {
  return this;
 }

 @Override
 public void updateMinSize () {
 }

 @Override
 public Dimension getSize () {
  return new Dimension(parent.getWidth(), parent.getHeight());
 }

 @Override
 public Dimension getMinSize () {
  return new Dimension(parent.getWidth(), parent.getHeight());
 }

 @Override
 public int getX () {
  return 0;
 }

 @Override
 public int getY () {
  return 0;
 }

 @Override
 public Point getPosition () {
  return new Point(0, 0);
 }

 @Override
 public void setPosition ( Point p ) {
 }

 @Override
 public void setX ( int x ) {
 }

 @Override
 public void setY ( int y ) {
 }

 @Override
 public boolean isTraversable () {
  return false;
 }

 @Override
 public void removedFromWidgetTree () {
 }

 @Override
 public void addedToWidgetTree () {
 }

 @Override
 public void setParent ( IBasicContainer object ) {
 }

 public void setParent ( Display object ) {
  this.parent = object;
 }

 @Override
 public boolean isInWidgetTree () {
  return true;
 }

 @Override
 public void layout () {
 }

 @Override
 public void setSize ( Dimension d ) {
 }

 @Override
 public boolean isVisible () {
  return visible;
 }

 @Override
 public void setVisible ( boolean visible ) {
  this.visible = visible;
 }

 @Override
 public boolean isExpandable () {
  return false;
 }

 @Override
 public boolean isShrinkable () {
  return false;
 }

 @Override
 public void paint ( Graphics g ) {
  rend.renderW(g);
 }

 @Override
 public void setData ( String key, Object data ) {
  this.map.put(key, data);
 }

 @Override
 public Object getData ( String key ) {
  return map.get(key);
 }

 @Override
 public void process ( InputOutputStream stream ) throws IOException,
                                                         IXMLStreamableException {
 }

 @Override
 public String getUniqueName () {
  return "GameRenderer";
 }

}
