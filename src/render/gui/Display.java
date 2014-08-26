package render.gui;
import java.io.IOException;
import javax.media.opengl.awt.GLCanvas;
import main.Main;
import org.fenggui.IWidget;
import org.fenggui.binding.render.Binding;
import org.fenggui.binding.render.Graphics;
import org.fenggui.binding.render.IOpenGL;
import org.fenggui.binding.render.Pixmap;
import org.fenggui.binding.render.jogl.JOGLBinding;
import org.fenggui.event.WidgetListChangedEvent;
import render.gui.widgets.WidgetsContainer;
import utils.vec.Vec2;
public class Display extends org.fenggui.Display{
 private final Vec2<Integer>    t             = new Vec2<>();
 private final WidgetsContainer always ;
 private Pixmap          background;
 private Integer                curr          = 0;

 
 public Display (GLCanvas canvas){
  super(new JOGLBinding(canvas));
  this.always = new WidgetsContainer();
 }
 
 public void changeGui(Integer id){
  this.curr = id;
 }
 public synchronized void addWidget(Integer id, IWidget w){
  notifyList.add(notifyList.size(), w);
  w.setParent(this);
  if (getDisplay() != null)
   w.addedToWidgetTree();
  w.resize(getWidth(), getHeight());

  updateMinSize();
  widgetAdded(new WidgetListChangedEvent(this, w));
 }
 public void setBack(String b){try {
  this.background = new Pixmap(Binding.getInstance().getTexture(Main.mdir+b));
  } catch ( IOException ex ) {
   Main.ERR_LOG.addE("Display.setBackground()", ex);
  }
} 
 public synchronized void resize(){
  notifyList.get(curr).resize(getWidth(), getHeight());
  t.sX(getWidth());
  t.sY(getHeight());
 }
 
 @Override public synchronized void display(){
    if (!this.isVisible())
      return;
    if((t.gX() != getWidth())||(t.gY() != getHeight()))
     resize();
    
    Binding binding = this.getBinding();
    IOpenGL gl = binding.getOpenGL();
    gl.pushAllAttribs();

    // this call is a problem, on newly installed systems it will raise an error as
    // no OpenGL extensions are installed. Should be replaced by something else if
    // possible.
    // opengl.activateTexture(0);

    gl.setViewPort(0, 0, binding.getCanvasWidth(), binding.getCanvasHeight());

    gl.setProjectionMatrixMode();
    gl.pushMatrix();
    gl.loadIdentity();
    gl.setOrtho2D(0, binding.getCanvasWidth(), 0, binding.getCanvasHeight());

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
    paintW(notifyList.get(curr));
    paintW(always); 

    g.resetClipSpace();

    gl.setProjectionMatrixMode();
    gl.popMatrix();

    gl.setModelMatrixMode();
    gl.popMatrix();
    gl.popAllAttribs();
  }
 private boolean clipWidget(Graphics g, IWidget c){
    int startX = c.getX() < 0 ? 0 : c.getX();
    int startY = c.getY() < 0 ? 0 : c.getY();

    if (getDisplay() != null)
    {
      Binding b = getBinding();

      if (startX >= b.getCanvasWidth() || startY >= b.getCanvasHeight())
      {
        return false;
      }

      int cWidth = c.getSize().getWidth();
      int cHeight = c.getSize().getHeight();

      g.addClipSpace(startX, startY, c.getX() + cWidth > getWidth() ? getWidth() - startX : cWidth,
        c.getY() + cHeight > getHeight() ? getHeight() - startY : cHeight);

      if (g.getClipSpace() != null)
        return true;
    }
    return false;
  }
 private void paintW(IWidget w){
  Binding binding = this.getBinding();
  IOpenGL gl = binding.getOpenGL();
  Graphics g = binding.getGraphics();
  
  gl.pushMatrix();

  clipWidget(g, w);

  g.translate(w.getX(), w.getY());

  w.paint(g);

  g.translate(-w.getX(), -w.getY());

  g.removeLastClipSpace();
  gl.popMatrix();
 }
 private void paintB(){
  Binding binding = this.getBinding();
  IOpenGL gl = binding.getOpenGL();
  Graphics g = binding.getGraphics();
  
  gl.pushMatrix();

  g.translate(-50, -50);

  g.drawImage(background, getWidth() / 2, getHeight() / 2);

  g.translate(50, 50);

  g.removeLastClipSpace();
  gl.popMatrix();
 }
}
