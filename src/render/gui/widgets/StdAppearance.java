package render.gui.widgets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.fenggui.appearance.IAppearance;
import org.fenggui.binding.render.Graphics;
import org.fenggui.binding.render.IOpenGL;
import org.fenggui.binding.render.Pixmap;
import org.fenggui.decorator.IDecorator;
import org.fenggui.decorator.background.Background;
import org.fenggui.decorator.border.Border;
import org.fenggui.decorator.switches.Switch;
import org.fenggui.theme.XMLTheme;
import org.fenggui.theme.xml.IXMLStreamable;
import static org.fenggui.theme.xml.IXMLStreamable.GENERATE_NAME;
import org.fenggui.theme.xml.IXMLStreamableException;
import org.fenggui.theme.xml.InputOnlyStream;
import org.fenggui.theme.xml.InputOutputStream;
import org.fenggui.util.Alignment;
import org.fenggui.util.Color;
import org.fenggui.util.Dimension;
import org.fenggui.util.Log;
import org.fenggui.util.Spacing;
import org.fenggui.util.Span;

public class StdAppearance  implements IAppearance, IXMLStreamable
{
  protected Map<String, Pixmap>       pixmaps    = null;
  protected Map<String, Color>        colors     = null;
  protected Map<String, IntegerStore> integers   = null;
  protected Map<String, Spacing>      spacings   = null;
  protected Map<String, IDecorator>   decorators = null;
  protected Map<String, Alignment>    alignments = null;
  protected final ArrayList<IDecorator> backgroundDecorators = new ArrayList<>(0);
  protected final ArrayList<Switch>     switches             = new ArrayList<>(0);
  protected Spacing        margin  = Spacing.ZERO_SPACING;
  protected Spacing        border  = Spacing.ZERO_SPACING;
  protected Spacing        padding = Spacing.ZERO_SPACING;
  protected StdContainer widget  = null;

  
  public StdAppearance(StdContainer w)
  {
    this.widget = w;
  }

  public StdAppearance(StdContainer w, StdAppearance appearance)
  {
    this(w);
    widget = w;
    this.margin = new Spacing(appearance.margin);
    this.border = new Spacing(appearance.border);
    this.padding = new Spacing(appearance.padding);
     for (IDecorator decorator : appearance.backgroundDecorators)
    {
      IDecorator copy = decorator.copy();
      if (copy != null)
        this.backgroundDecorators.add(copy);
    }

    for (Switch s : appearance.switches)
    {
      Switch copy = s.copy();
      if (copy != null)
        switches.add(copy);
    }
  }

  public StdAppearance(StdContainer w, InputOnlyStream stream) throws IOException, IXMLStreamableException
  {
    widget = w;
    this.process(stream);
  }

  public final void paintBackground(Graphics g, IOpenGL gl)
  {
    for (int i = 0; i < backgroundDecorators.size(); i++)
    {
      int width = getWidget().getSize().getWidth();
      int height = getWidget().getSize().getHeight();

      paintDecorator(backgroundDecorators.get(i), g, gl, this, width, height);
    }
  }
  
 @Override
  public void process(InputOutputStream stream) throws IOException, IXMLStreamableException
  {
    if (stream.startSubcontext("BackgroundDecorators"))
    {
      if (stream.processAttribute("clear", false, false))
        backgroundDecorators.clear();

      stream.processChildren(backgroundDecorators, XMLTheme.TYPE_REGISTRY);

      stream.endSubcontext();
    }

    if (stream.startSubcontext("Switches"))
    {
      if (stream.processAttribute("clear", false, false))
        switches.clear();

      stream.processChildren(switches, XMLTheme.TYPE_REGISTRY);

      stream.endSubcontext();
    }

  }


  /**
   * Internal Class to store integer values as XML elements
   * 
   * @author Marc Menghin, last edited by $Author$, $Date$
   * @version $Revision$
   */
  class IntegerStore implements IXMLStreamable
  {
    Integer value;

    public IntegerStore(Integer value)
    {
      this.value = value;
    }

    /* (non-Javadoc)
     * @see org.fenggui.theme.xml.IXMLStreamable#getUniqueName()
     */
    @Override
    public String getUniqueName()
    {
      return IXMLStreamable.GENERATE_NAME;
    }

    /* (non-Javadoc)
     * @see org.fenggui.theme.xml.IXMLStreamable#process(org.fenggui.theme.xml.InputOutputStream)
     */
    @Override
    public void process(InputOutputStream stream) throws IOException, IXMLStreamableException
    {
      stream.processAttribute("Value", value);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public IntegerStore clone()
    {
      IntegerStore result;
      try
      {
        result = (IntegerStore) super.clone();
      }
      catch (CloneNotSupportedException e)
      {
        //can not happen but write out anyway
        Log.error("Couldn't clone IntegerStore", e);
        return null;
      }
      result.value = new Integer(this.value.intValue());
      return result;
    }
  }


  public Pixmap getPixmap(String key)
  {
    if (pixmaps == null)
      return null;
    else
      return pixmaps.get(key);
  }

  public void addPixmap(String key, Pixmap pixmap)
  {
    if (pixmaps == null)
      pixmaps = new HashMap<String, Pixmap>();
    pixmaps.remove(key);
    pixmaps.put(key, pixmap);
  }

  public Color getColor(String key)
  {
      if (colors == null)
      return null;
    else
      return colors.get(key);
  }

  public void addColor(String key, Color color)
  {
    if (colors == null)
      colors = new HashMap<String, Color>();
    colors.remove(key);
    colors.put(key, color);
  }

  public Integer getInteger(String key)
  {
    if (integers == null)
      return null;
    else
    {
      IntegerStore store = integers.get(key);
      if (store != null)
        return integers.get(key).value;
      else
        return null;
    }
  }

  public void addInteger(String key, Integer integer)
  {
    if (integers == null)
      integers = new HashMap<String, IntegerStore>();

    integers.remove(key);
    integers.put(key, new IntegerStore(integer));
  }

  public Spacing getSpacing(String key)
  {
    if (spacings == null)
      return null;
    else
      return spacings.get(key);
  }

  public void addSpacing(String key, Spacing spacing)
  {
    if (spacings == null)
      spacings = new HashMap<String, Spacing>();

    spacings.remove(key);
    spacings.put(key, spacing);
  }

  public IDecorator getDecorator(String key)
  {
    if (decorators != null)
      return decorators.get(key);
    else
      return null;
  }

  public void addDecorator(String key, IDecorator decorator)
  {
    if (decorators == null)
      decorators = new HashMap<String, IDecorator>();

    decorators.remove(key);
    decorators.put(key, decorator);
  }

  public Alignment getAlignment(String key)
  {
    if (alignments != null)
      return alignments.get(key);
    else
      return null;
  }

  public void addAlignment(String key, Alignment decorator)
  {
    if (alignments == null)
      alignments = new HashMap<String, Alignment>();

    alignments.remove(key);
    alignments.put(key, decorator);
  }

 
 

  public void add(String label, Background background, Span spanType)
  {
    background.setLabel(label);
    background.setSpan(spanType);
    backgroundDecorators.add(background);
  }

  public void add(IDecorator decorator)
  {
    backgroundDecorators.add(decorator);
  }

  public void add(Background background)
  {
    add("default", background, Span.PADDING);
  }

  public void add(String label, Background background)
  {
    add(label, background, Span.PADDING);
  }

  public void add(String label, Border border, boolean setAsBorderSpacing)
  {
    border.setLabel(label);
    backgroundDecorators.add(border);

    // we need to set a copy of the border as Spacing type in order to avoid that the
    // XMLOutputStream outputs all the border information.
    if (setAsBorderSpacing)
      setBorder(new Spacing(border.getTop(), border.getLeft(), border.getRight(), border.getBottom()));
  }

  public void add(Border border)
  {
    add("default", border, true);
  }

  public void add(String label, Border border)
  {
    add(label, border, true);
  }

  public void add(Switch sw)
  {
    switches.add(sw);
  }

  /**
   * Paints the given decorator. It adjusts the size of the decorator according
   * to the span. E.g. Span.BORDER means that the decorator span over the
   * padding AND the border.
   * @param d the decorators
   * @param g the graphics object
   * @param gl the opengl object
   * @param app the appearance used to calculate the margins
   * @param widgetWidth the widget if the whole widget
   * @param widgetHeight the heigth of the whole widget
   */
  private void paintDecorator(IDecorator d, Graphics g, IOpenGL gl, StdAppearance app, int widgetWidth,
      int widgetHeight)
  {
    if (!d.isEnabled())
      return;

    int x = 0;
    int y = 0;

    if (d.getSpan() == Span.PADDING)
    {
      Spacing m = app.getMargin();
      Spacing b = app.getBorder();

      x += m.getLeft() + b.getLeft();
      y += m.getBottom() + b.getBottom();

      widgetWidth -= x + m.getRight() + b.getRight();
      widgetHeight -= y + m.getTop() + b.getTop();
    }
    else if (d.getSpan() == Span.BORDER)
    {
      Spacing m = app.getMargin();

      x += m.getLeft();
      y += m.getBottom();

      widgetWidth -= x + m.getRight();
      widgetHeight -= y + m.getTop();
    }

    d.paint(g, x, y, widgetWidth, widgetHeight);
  }

  public void setEnabled(String label, boolean enable)
  {
    for (IDecorator wrapper : backgroundDecorators)
    {
      if (wrapper.getLabel().equals(label))
        wrapper.setEnabled(enable);
    }

    for (Switch sw : switches)
    {
      if (sw.getLabel().equals(label) && enable == sw.isReactingOnEnabled())
        sw.setup(getWidget());
    }
  }

  @Override
  public String toString()
  {
    String s = "\nBackground decorators:";

    for (IDecorator wrapper : backgroundDecorators)
    {
      s += "\n- " + wrapper.toString();
    }


    return super.toString() + s;
  }

  /**
   * Removes all switches and decorators from this appearance.
   *
   */
  public void removeAll()
  {
    backgroundDecorators.clear();
    switches.clear();
  }

  /**
   * Returns the space reserved for borders.
   * @return the border spacing
   */
  public Spacing getBorder()
  {
    return border;
  }

  public StdContainer getWidget()
  {
    return widget;
  }

  /**
   * Reserves space for the border. Do not confuse with addBorder().
   * @param border the spacing which says how much space to reserve
   */
  public void setBorder(Spacing border)
  {
    if (border == null)
      border = Spacing.ZERO_SPACING;

    this.border = border;
    getWidget().updateMinSize();
  }

  public Spacing getMargin()
  {
    return margin;
  }

  public void setMargin(Spacing margin)
  {
    if (margin == null)
      margin = Spacing.ZERO_SPACING;

    this.margin = margin;
    getWidget().updateMinSize();
  }

  public Spacing getPadding()
  {
    return padding;
  }

  public void setPadding(Spacing padding)
  {
    if (padding == null)
      padding = Spacing.ZERO_SPACING;

    this.padding = padding;
    getWidget().updateMinSize();
  }

  public void clearSpacings()
  {
    clearSpacings(Spacing.ZERO_SPACING);
  }

  public void clearSpacings(Spacing clearSpacing)
  {
    this.margin = clearSpacing;
    this.border = clearSpacing;
    this.padding = clearSpacing;
  }

  @Override
  public final Dimension getMinSizeHint()
  {
    Dimension contentSize = this.getWidget().getMinContentSize();

    if (contentSize == null)
      return new Dimension(10, 10);

    contentSize.setSize(contentSize.getWidth() + border.getLeftPlusRight() + margin.getLeftPlusRight()
        + padding.getLeftPlusRight(), contentSize.getHeight() + border.getBottomPlusTop() + margin.getBottomPlusTop()
        + padding.getBottomPlusTop());

    return contentSize;
  }

  @Override
  public void paint(Graphics g, IOpenGL gl)
  {
    int offsetX = margin.getLeft() + border.getLeft() + padding.getLeft();
    int offsetY = margin.getBottom() + border.getBottom() + padding.getBottom();

    paintBackground(g, gl);

    g.translate(offsetX, offsetY);

    widget.paintContent(g, gl);

    g.translate(-offsetX, -offsetY);

  }

  /**
   * Return how much height in pixels is available to draw the content.
   * @return available space for content
   */
  public int getContentWidth()
  {
    return widget.getSize().getWidth() - border.getLeftPlusRight() - margin.getLeftPlusRight()
        - padding.getLeftPlusRight();
  }

  /**
   * Return how much width in pixels is available to draw the content.
   * @return available space for content
   */
  public int getContentHeight()
  {
    return widget.getSize().getHeight() - border.getBottomPlusTop() - margin.getBottomPlusTop()
        - padding.getBottomPlusTop();
  }

  /**
   * Returns if the specified point lays inside the box
   * without margin.
  
   * @param pX The x-coordinate of the position to test.
   * @param pY The y-coordinate of the position to test.
   * @return Whether or not the point was inside the box.
   */
  public final boolean insideMargin(int pX, int pY)
  {
    // translating pX, pY to the inner Box
    pX -= margin.getLeft();
    pY -= margin.getBottom();

    int innerWidth = getContentWidth();
    int innerHeight = getContentHeight();

    return pX >= 0
        && pX < innerWidth + padding.getLeft() + padding.getRight() + getBorder().getLeft() + getBorder().getRight()
        && pY >= 0
        && pY < innerHeight + padding.getBottom() + padding.getTop() + getBorder().getTop() + getBorder().getBottom();
  }

  /**
   * Returns the sum of bottom margin, bottom border and the
   * bottom padding.
   * @return sum
   */
  public int getBottomMargins()
  {
    return margin.getBottom() + getBorder().getBottom() + padding.getBottom();
  }

  /**
   * Returns the sum of the top margin, top border and top padding.
   * @return sum
   */
  public int getTopMargins()
  {
    return margin.getTop() + getBorder().getTop() + padding.getTop();
  }

  /**
   * Returns the sum of the left margin, left border and left 
   * padding.
   * @return sum
   */
  public int getLeftMargins()
  {
    return margin.getLeft() + getBorder().getLeft() + padding.getLeft();
  }

  /**
   * Returns the sum of the right margin, right border and
   * right padding.
   * @return sum
   */
  public int getRightMargins()
  {
    return margin.getRight() + getBorder().getRight() + padding.getRight();
  }

  public int getContentMinWidth()
  {
    return getWidget().getMinSize().getWidth() - getLeftMargins() - getRightMargins();
  }

  public int getContentMinHeight()
  {
    return getWidget().getMinSize().getHeight() - getTopMargins() - getBottomMargins();
  }


  @Override
  public String getUniqueName()
  {
    return GENERATE_NAME;
  }
 @Override public StdAppearance clone() throws CloneNotSupportedException {
  throw new CloneNotSupportedException();
 }
  
}