package render.gui;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.awt.GLCanvas;
import main.Main1;
import org.fenggui.IWidget;
import org.fenggui.binding.render.Binding;
import org.fenggui.binding.render.Graphics;
import org.fenggui.binding.render.IOpenGL;
import org.fenggui.binding.render.Pixmap;
import org.fenggui.binding.render.jogl.JOGLBinding;
import org.fenggui.event.WidgetListChangedEvent;
import utils.GuiId;
import utils.vec.Vec2;
public class Display extends org.fenggui.Display {
 private final Layout layout = new Layout(this);
 private final Vec2<Integer> t = new Vec2<>(0,0);
 public Display ( GLCanvas canvas ) {
  super(new JOGLBinding(canvas));
 }
 public void changeGui (Integer id ) {layout.changeGui(new GuiId(id));}
 public void doLayout(){layout.doLayout();}
 public synchronized void addWidget ( Integer id , IWidget w ) {
  notifyList.add(notifyList.size() , w);
  w.setParent(this);

  layout.add(new GuiId(id) , w);

  if ( getDisplay() != null ) {
   w.addedToWidgetTree();
  }

  updateMinSize();
  widgetAdded(new WidgetListChangedEvent(this , w));
 }
 public void setBack ( Integer id , String b ) {
  try {
   layout.addBack(new GuiId(id) , new Pixmap(Binding.getInstance().
                  getTexture(Main1.mdir + b)));
  } catch ( IOException ex ) {
   Main1.LOG.addE("Display.setBackground()" , ex);
  }
 }
 @Override public synchronized void display () {
   if((t.gX() != getWidth())||(t.gY() != getHeight()))
     doLayout();
    
  
  if ( !this.isVisible() ) {
   return;
  }

  Binding binding = this.getBinding();
  IOpenGL gl = binding.getOpenGL();
  gl.pushAllAttribs();

  // this call is a problem, on newly installed systems it will raise an error as
  // no OpenGL extensions are installed. Should be replaced by something else if
  // possible.
  // opengl.activateTexture(0);
  gl.setViewPort(0 , 0 , binding.getCanvasWidth() , binding.getCanvasHeight());

  gl.setProjectionMatrixMode();
  gl.pushMatrix();
  gl.loadIdentity();
  gl.setOrtho2D(0 , binding.getCanvasWidth() , 0 , binding.getCanvasHeight());

  gl.setModelMatrixMode();
  gl.pushMatrix();
  gl.loadIdentity();
  gl.setupStateVariables(isDepthTestEnabled());

  // opengl.translateZ(-50);
  Graphics g = binding.getGraphics();

  g.resetTransformations();
  g.resetClipSpace();
  g.forceColor(true);

  paintB();
  this.notifyList.stream().forEach(w -> {
   paintW(w);
  });

  g.resetClipSpace();

  gl.setProjectionMatrixMode();
  gl.popMatrix();

  gl.setModelMatrixMode();
  gl.popMatrix();
  gl.popAllAttribs();
 }
 private boolean clipWidget ( Graphics g , IWidget c ) {
  int startX = c.getX() < 0 ? 0 : c.getX();
  int startY = c.getY() < 0 ? 0 : c.getY();

  if ( getDisplay() != null ) {
   Binding b = getBinding();

   if ( startX >= b.getCanvasWidth() || startY >= b.getCanvasHeight() ) {
    return false;
   }

   int cWidth = c.getSize().getWidth();
   int cHeight = c.getSize().getHeight();

   g.addClipSpace(startX , startY ,
                  c.getX() + cWidth > getWidth() ? getWidth() - startX : cWidth ,
                  c.getY() + cHeight > getHeight() ? getHeight() - startY : cHeight);

   if ( g.getClipSpace() != null ) {
    return true;
   }
  }
  return false;
 }
 private void paintW ( IWidget w ) {
  Binding binding = this.getBinding();
  IOpenGL gl = binding.getOpenGL();
  Graphics g = binding.getGraphics();

  gl.pushMatrix();

  clipWidget(g , w);

  g.translate(w.getX() , w.getY());

  w.paint(g);

  g.translate(-w.getX() , -w.getY());

  g.removeLastClipSpace();
  gl.popMatrix();
 }
 private void paintB () {
  Binding binding = this.getBinding();
  IOpenGL gl = binding.getOpenGL();
  Graphics g = binding.getGraphics();

  gl.pushMatrix();

  g.drawScaledImage(layout.getBack() , 0 , 0 , getWidth() , getHeight());

  g.removeLastClipSpace();
  gl.popMatrix();
 }
 public ArrayList<IWidget> getList(){
  return notifyList;
 }
}
