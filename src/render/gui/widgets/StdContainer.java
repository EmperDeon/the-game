package render.gui.widgets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.fenggui.Display;
import org.fenggui.IBasicContainer;
import org.fenggui.IWidget;
import org.fenggui.binding.render.Binding;
import org.fenggui.binding.render.Graphics;
import org.fenggui.binding.render.IOpenGL;
import org.fenggui.event.*;
import org.fenggui.event.key.KeyPressedEvent;
import org.fenggui.event.key.KeyReleasedEvent;
import org.fenggui.event.key.KeyTypedEvent;
import org.fenggui.event.mouse.MouseClickedEvent;
import org.fenggui.event.mouse.MouseDoubleClickedEvent;
import org.fenggui.event.mouse.MouseDraggedEvent;
import org.fenggui.event.mouse.MouseEnteredEvent;
import org.fenggui.event.mouse.MouseExitedEvent;
import org.fenggui.event.mouse.MousePressedEvent;
import org.fenggui.event.mouse.MouseReleasedEvent;
import org.fenggui.event.mouse.MouseWheelEvent;
import org.fenggui.layout.ILayoutData;
import org.fenggui.theme.XMLTheme;
import org.fenggui.theme.xml.IXMLStreamable;
import static org.fenggui.theme.xml.IXMLStreamable.GENERATE_NAME;
import org.fenggui.theme.xml.IXMLStreamableException;
import org.fenggui.theme.xml.InputOutputStream;
import org.fenggui.theme.xml.MissingElementException;
import org.fenggui.util.Dimension;
import org.fenggui.util.Point;

public class StdContainer implements IWidget, IXMLStreamable, IBasicContainer,  Cloneable{
  protected StdLayout            layoutManager    = null;
  protected ArrayList<StdWidget> cont             = new ArrayList<>();
  protected boolean              keyTraversalRoot = false;
  protected StdAppearance    appearance       = null;
  protected boolean              minSizeUpdated   = false;
  protected Dimension                           size;
  protected boolean                             visible;
  protected Map<String, Object>                 data       = null;
  protected IBasicContainer                     parent     = null;
  protected Point                               position;
  protected ILayoutData                         layoutData = null;
  protected Display display;
  public StdContainer(Display display){
    this.position = new Point(0, 0);
    this.size = new Dimension(10, 10);
    this.visible = true;
    this.data = null;
    this.parent = null;
    this.layoutData = null;
    this.display = display;
    this.layoutManager = new StdLayout();
    this.appearance = new StdAppearance(this);
  }
 
  @Override public void resize ( int w , int h ){}
 
  public void addW(StdWidget c){
    cont.add(c);
    c.setParent(this);
    updateMinSize();
  }
  
  @Override public StdWidget getWidget(int x, int y){
   return null;
  }
  
  @Override public void updateMinSize() {
    minSizeUpdated = false;

    if (!minSizeUpdated)
      this.layout();
  }
  public synchronized void paintContent(Graphics g, IOpenGL gl) {
    IOpenGL opengl = g.getOpenGL();
      for (StdWidget c : cont){
        // if widget lays completely outside
        if (c.getX() > this.getWidth() || c.getY() > this.getHeight())
          continue;

        //XXX the upper statement does not recognize the margins of the container!

        boolean valid = this.clipWidget(g, c);

        if (!valid)
        {
          g.removeLastClipSpace();
          continue;
        }

        if (g.getClipSpace() != null)
        {
          opengl.pushMatrix();
          g.translate(c.getX(), c.getY());

          c.paint(g);

          g.translate(-c.getX(), -c.getY());
          opengl.popMatrix();
        }

        g.removeLastClipSpace();
      }
  }

  @Override public void focusChanged(FocusEvent focusEvent) {

    if (focusEvent.isFocusGained())
    {
      int i = 0;

      synchronized (cont)
      {
        while (i < size() && !cont.get(i).isTraversable())
          i++;

        if (i >= size())
          return;

        getDisplay().setFocusedWidget(cont.get(i));
      }
    }
  }
  final boolean clipWidget(Graphics g, StdWidget c) {
    int startX = c.getX() < 0 ? 0 : c.getX();
    int startY = c.getY() < 0 ? 0 : c.getY();

    if (getDisplay() != null)
    {
      Binding b = getDisplay().getBinding();

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
      else
        return false;
    }
    return false;
  }
  @Override public boolean isTraversable() {
    return true;
  }

  public synchronized void bringToFront(StdWidget child) {
      if (!cont.contains(child))
        throw new IllegalArgumentException("The given child must be in this container");

      cont.remove(child);
      cont.add(cont.size(), child);
  }
  @Override public void removedFromWidgetTree(){}
  @Override public void addedToWidgetTree(){}
  @Override public void layout() {
    // layout this container according to the min. sizes of the children
    // and my own size. Since i know the min. size of the children and
    // my final size, I can set the final size of my children as well.

    layoutManager.doLayout(appearance, cont);

    synchronized (cont)
    {
     cont.stream().forEach( c -> {c.layout();} );
    }
  }
  public void updateMinSizeAndLayout() {
    updateMinSize();
    layout();
  }

  @Override public String toString() {
    if (cont == null)
    {
      return super.toString() + " {}";
    }

    String s = super.toString() + " {";

    synchronized (cont)
    {
      for (int i = 0; i < cont.size(); i++)
      {
        s += cont.get(i).getClass().getSimpleName();
        if (i < cont.size() - 1)
          s += ", ";
      }
    }
    s += "}";
    return s;
  }
  @Override public void positionChanged(PositionChangedEvent event) {
    if (!this.isVisible() || !this.isInWidgetTree())
    {
      return;
    }

    synchronized (cont)
    {
      for (StdWidget widget : cont)
      {
        widget.positionChanged(event);
      }
    }
  }
  public int size() {
    return cont.size();
  }
  public void pack() {
    setSizeToMinSize();
    layout();
  }
  @Override public void process(InputOutputStream stream) throws IOException, IXMLStreamableException {
    try
    {
      layoutManager = stream.processChild(layoutManager, XMLTheme.TYPE_REGISTRY);
    }
    catch (MissingElementException e)
    {
      // we ignore the exception intentionally, because not providing a
      // layout manger means that the default layout manager should remain in
      // place (which is the RowLayoutManager)
    }

    if (stream.startSubcontext("children"))
    {
      stream.processChildren(cont, XMLTheme.TYPE_REGISTRY);
      stream.endSubcontext();
    }
  }
    @Override
  public int getDisplayX()
  {
    // FIXME Is it the right thing to do, if parent == null ?
    IBasicContainer parent = this.getParent();
    if (parent != null)
    {
      return parent.getDisplayX() + this.getX();
    }
    return 0;
  }

  @Override
  public int getDisplayY(){return 0;}

  @Override public void setSize(Dimension s) {
    this.size = s;
    this.layout();
  }
  public Dimension getMinContentSize() {
    return this.getLayoutManager().computeMinSize(cont);
  }
  public StdAppearance getAppearance() {
    return appearance;
  }
  public StdWidget getWidget(int index) {
    return cont.get(index);
  }
  public Iterable<IWidget> getWidgets() {   
   ArrayList<IWidget> r = new ArrayList<>();
   cont.stream().forEach(w->{r.add((IWidget)w );});
   return r;
  }
  public void setLayoutManager(StdLayout lm) {
    if (lm == null)
      return;
    layoutManager = lm;

    updateMinSize();
  }
  public StdLayout getLayoutManager() {
    return layoutManager;
  }
  public void setKeyTraversalRoot(boolean traversalRoot) {
    this.keyTraversalRoot = traversalRoot;
  }
  public void setAppearance(StdAppearance appearance) {
    this.appearance = appearance;
  }
  
  
  // Clonable
  @Override public StdContainer clone() throws CloneNotSupportedException {
    StdContainer result = (StdContainer) super.clone();

    result.appearance =(StdAppearance) this.appearance.clone(); 
    
    return result;
  } 
  
 // IBasicContainer
 @Override public IWidget getNextWidget ( IWidget start ) {return null;}
 @Override public IWidget getPreviousWidget ( IWidget start ) {return null;}
 @Override public IWidget getNextTraversableWidget ( IWidget start ) {return null;}
 @Override public IWidget getPreviousTraversableWidget ( IWidget start ) {return null;}
 @Override public boolean isKeyTraversalRoot () {return false; }

 // StdWidget
  
  @Override public void paint(Graphics g){
    if (visible)
    {
      if (getAppearance() != null)
        getAppearance().paint(g, g.getOpenGL());
      else
        paintContent(g, g.getOpenGL());
    }
  }

  @Override public String getUniqueName(){
    return GENERATE_NAME;
  }

  public void setLayoutData(ILayoutData layoutData)
  {
    this.layoutData = layoutData;
  }

  @Override
  public ILayoutData getLayoutData()
  {
    return layoutData;
  }

  @Override
  public IBasicContainer getParent()
  {
    return parent;
  }

  @Override
  public void mouseEntered(MouseEnteredEvent mouseEnteredEvent)
  {
    if (!mouseEnteredEvent.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().mouseEntered(mouseEnteredEvent);
    }
  }

  @Override
  public void mouseExited(MouseExitedEvent mouseExitedEvent)
  {
    if (!mouseExitedEvent.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().mouseExited(mouseExitedEvent);
    }
  }

  @Override
  public void mousePressed(MousePressedEvent mp)
  {
    if (!mp.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().mousePressed(mp);
    }
  }

  @Override
  public void mouseMoved(int displayX, int displayY)
  {
    if (this.getParent() != null)
    {
      this.getParent().mouseMoved(displayX, displayY);
    }
  }

  @Override
  public void mouseDragged(MouseDraggedEvent mp)
  {
    if (!mp.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().mouseDragged(mp);
    }
  }

  @Override
  public void mouseReleased(MouseReleasedEvent mr)
  {
    if (!mr.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().mouseReleased(mr);
    }
  }

  @Override
  public void mouseDoubleClicked(MouseDoubleClickedEvent event)
  {
    if (!event.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().mouseDoubleClicked(event);
    }
  }

  @Override
  public void mouseClicked(MouseClickedEvent event)
  {
    if (!event.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().mouseClicked(event);
    }
  }

  @Override
  public void mouseWheel(MouseWheelEvent mouseWheelEvent)
  {
    if (!mouseWheelEvent.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().mouseWheel(mouseWheelEvent);
    }
  }

  @Override
  public void keyPressed(KeyPressedEvent keyPressedEvent)
  {
    if (!keyPressedEvent.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().keyPressed(keyPressedEvent);
    }
  }

  @Override
  public void keyReleased(KeyReleasedEvent keyReleasedEvent)
  {
    if (!keyReleasedEvent.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().keyReleased(keyReleasedEvent);
    }
  }

  @Override
  public void keyTyped(KeyTypedEvent keyTypedEvent)
  {
    if (!keyTypedEvent.isAlreadyUsed() && (this.getParent() != null))
    {
      this.getParent().keyTyped(keyTypedEvent);
    }
  }

  @Override public void sizeChanged(SizeChangedEvent event){}

  @Override public void addSizeChangedListener(ISizeChangedListener l){}

  @Override public void removeSizeChangedListener(ISizeChangedListener l){}

  @Override public void addPositionChangedListener(IPositionChangedListener l){}

  @Override public void removePositionChangedListener(IPositionChangedListener l){}

  @Override public boolean isInWidgetTree(){return true;}

  @Override
  public Display getDisplay()
  {
    if (parent == null)
    {
      return null;
    }
    return this.getParent().getDisplay();
  }

  public void setSizeToMinSize()
  {
    this.setSize(new Dimension(this.getMinSize()));
  }

  public void move(int x, int y)
  {
    this.setPosition(new Point(this.getX() + x, this.getY() + y));
  }
  
  public void setXY(int x, int y){this.position.setXY(x , y);}
  @Override public final void setParent(IBasicContainer parent){this.parent = parent;}
  @Override public void setY(int y){this.position.setY(y);}
  @Override public int getY() {return position.getY();}
  public boolean hasFocus(){
    if (display == null)
    {
      return false;
    }

    IWidget w = display.getFocusedWidget();

    if (w == null)
    {
      return false;
    }

    return w.equals(this);
  }
  public int getWidth(){return size.getWidth();}
  public int getHeight(){return size.getHeight();}
  @Override public boolean isExpandable(){return false;}
  @Override public boolean isShrinkable(){return false;}
  @Override public Dimension getSize(){return size;}
  @Override public Dimension getMinSize(){return size;}
  public void setShrinkable(boolean shrinkable){}
  public void setMinSize(Dimension dim){}
  @Override public Point getPosition(){return position;}
  public int getMinWidth(){return this.size.getWidth();}
  public int getMinHeight(){return this.size.getHeight();}
  public void setMinSize(int minWidth, int minHeight){}
  public void setSize(int width, int height){this.size.setSize(width , height);}
  public void setHeight(int height){this.size.setHeight(height);}
  public void setWidth(int width){this.size.setWidth(width);}
  @Override public void setX(int x){this.position.setX(x);}
  @Override public int getX(){return position.getX();}
  @Override public void setPosition(Point p){this.position=p;}
  @Override public boolean isVisible(){return visible;}
  @Override public void setVisible(boolean visible){this.visible = visible;}
  @Override public Object getData(String key){
    if (data == null)
    {
      return null;
    }
    else
    {
      return data.get(key);
    }
  }
  @Override public void setData(String key, Object data){
    if (this.data == null)
    {
      this.data = new HashMap<String, Object>();
    }
    this.data.put(key, data);
  }
  @Override public void addMinSizeChangedListener(ISizeChangedListener l){}
  @Override public void removeMinSizeChangedListener(ISizeChangedListener l){}
}

