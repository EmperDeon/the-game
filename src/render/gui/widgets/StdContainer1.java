package render.gui.widgets;

public class StdContainer1{} /*extends StandardWidget implements  Cloneable{
  protected StdLayout            layoutManager    = null;
  protected ArrayList<StdWidget> cont             = new ArrayList<>();
  protected boolean              keyTraversalRoot = false;
  protected DefaultAppearance    appearance       = null;
  protected boolean              minSizeUpdated   = false;

  public StdContainer1(){
    this(new StdLayout());
  }
  public StdContainer1(StdLayout layoutManager){
    super();
    this.layoutManager = layoutManager;
    appearance = new DefaultAppearance(this);
  }
  public StdContainer1(StdContainer container){
    super(container);

    this.layoutManager = container.layoutManager;
    this.appearance = new DefaultAppearance(this, container.appearance);
  }
  
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
    super.updateMinSize();

    if (!minSizeUpdated)
      this.layout();
  }
  @Override public void paintContent(Graphics g, IOpenGL gl) {
    IOpenGL opengl = g.getOpenGL();

    synchronized (this.cont)
    {

      for (StdWidget c : cont)
      {
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

  }

  @Override public boolean isKeyTraversalRoot(){return keyTraversalRoot;}
  @Override public void focusChanged(FocusEvent focusEvent) {
    super.focusChanged(focusEvent);

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
  @Override public void minSizeChanged(SizeChangedEvent event){
    minSizeUpdated = true;
    super.minSizeChanged(event);
  }
  public void bringToFront(StdWidget child) {
    synchronized (cont)
    {
      if (!cont.contains(child))
        throw new IllegalArgumentException("The given child must be in this container");

      cont.remove(child);
      cont.add(cont.size(), child);
    }
  }
  @Override public void addWidget(IWidget... widgets){}
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
  @Override public void removeWidget(IWidget... widgets) {updateMinSize();}
  public void removeWidgetInternal(StdWidget w) {
    if (w == null)
      return;
    if (w.equals(this))
      throw new IllegalArgumentException("Cannot remove myself! " + this);

    cont.remove(w);
    w.removedFromWidgetTree();
    w.setParent(null);
  }
  public void removeWidgets(java.util.List<IWidget> list){updateMinSize();}
  public void removeAllWidgets() {
    removeWidget(cont.toArray(new StdWidget[cont.size()]));
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
    super.positionChanged(event);

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
    super.process(stream);
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
  @Override public boolean hasChildWidgets() {
    return cont.size() > 0;
  }
  @Override public StdContainer clone() throws CloneNotSupportedException {
    StdContainer result = (StdContainer) super.clone();

    result.appearance = this.appearance.clone(result); 
    
    return result;
  }
  
  @Override public IWidget getPreviousWidget(IWidget currentWidget) {
    int i;
    synchronized (cont)
    {
      if (currentWidget == null)
        i = size() - 1;
      else
        i = cont.indexOf(currentWidget) - 1;

      if (i < 0)
      {
        if (isKeyTraversalRoot() && 0 != size() - 1)
          i = size() - 1;
        else
          return null;
        //				return getParent().getPreviousWidget(this);
      }

      return cont.get(i);
    }
  }
  @Override public IWidget getNextWidget(IWidget currentWidget) {
    int i;
    synchronized (cont)
    {
      if (currentWidget == null)
        i = 0;
      else
        i = cont.indexOf(currentWidget) + 1;

      if (i > size() - 1)
      {
        if (isKeyTraversalRoot() && (size() - 1) != 0)
          i = 0;
        else
          return null;
      }

      return cont.get(i);
    }
  }
  @Override public IWidget getNextTraversableWidget(IWidget currentWidget) {
    if (currentWidget != null && !cont.contains(currentWidget))
      throw new IllegalArgumentException("currentWidget is not child of this container!");

    IWidget w = getNextWidget(currentWidget);

    //search in this and deeper levels
    while (w != null)
    {
      if (w.isTraversable())
      {
        if (w instanceof IBasicContainer)
        {
          if (!((IBasicContainer) w).isKeyTraversalRoot())
          {
            IWidget tmp = ((IBasicContainer) w).getNextTraversableWidget(null);
            if (tmp != null)
            {
              w = tmp;
              break;
            }
          }
        }
        else
        {
          //found next here
          break;
        }
      }
      w = getNextWidget(w);
    }

    //no widgets here go one level up and search there
    if (w == null && this.getParent() != null && !isKeyTraversalRoot())
    {
      w = this.getParent().getNextTraversableWidget(this);
    }
    else if (w == null && isKeyTraversalRoot())
    {
      w = getNextTraversableWidget(null);
    }

    return w;
  }
  @Override public IWidget getPreviousTraversableWidget(IWidget currentWidget) {
    if (currentWidget != null && !cont.contains(currentWidget))
      throw new IllegalArgumentException("currentWidget is not child of this container!");

    IWidget w = getPreviousWidget(currentWidget);

    //search in this and deeper levels
    while (w != null)
    {
      if (w.isTraversable())
      {
        if (w instanceof IBasicContainer)
        {
          if (!((IBasicContainer) w).isKeyTraversalRoot())
          {
            IWidget tmp = ((IBasicContainer) w).getPreviousTraversableWidget(null);
            if (tmp != null)
            {
              w = tmp;
              break;
            }
          }
        }
        else
        {
          //found next here
          break;
        }
      }
      w = getPreviousWidget(w);
    }

    //no widgets here go one level up and search there
    if (w == null && this.getParent() != null && !isKeyTraversalRoot())
    {
      w = this.getParent().getPreviousTraversableWidget(this);
    }
    else if (w == null && isKeyTraversalRoot())
    {
      w = getPreviousTraversableWidget(null);
    }

    return w;
  }
  @Override public int getDisplayX() {
    return super.getDisplayX() + getAppearance().getLeftMargins();
  }
  @Override public int getDisplayY() {
    return super.getDisplayY() + getAppearance().getBottomMargins();
  }
  @Override public java.util.List<IWidget> getContent() {
   ArrayList<IWidget> r = new ArrayList<>();
   cont.stream().forEach(w->{r.add((IWidget)w );});
   return r;
  }
  @Override public void setSize(Dimension s) {
    super.setSize(s);
    this.layout();
  }
  @Override public int getChildWidgetCount() {
    return cont.size();
  }
  @Override public Dimension getMinContentSize() {
    return this.getLayoutManager().computeMinSize(cont);
  }
  @Override public DefaultAppearance getAppearance() {
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
  public void setAppearance(DefaultAppearance appearance) {
    this.appearance = appearance;
  }

 @Override public void addWidget ( IWidget w , int position ) {addW(w);}
  
}
*/
