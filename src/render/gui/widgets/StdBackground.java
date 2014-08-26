/*
 * FengGUI - Java GUIs in OpenGL (http://www.fenggui.org)
 * 
 * Copyright (c) 2005-2009 FengGUI Project
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details:
 * http://www.gnu.org/copyleft/lesser.html#TOC3
 * 
 * $Id: PixmapBackground.java 606 2009-03-13 14:56:05Z marcmenghin $
 */
package render.gui.widgets;

import java.io.IOException;
import main.Main;
import org.fenggui.binding.render.Binding;
import org.fenggui.binding.render.Graphics;
import org.fenggui.binding.render.Pixmap;
import org.fenggui.decorator.IDecorator;
import org.fenggui.decorator.background.Background;
import org.fenggui.theme.xml.IXMLStreamableException;
import org.fenggui.theme.xml.InputOnlyStream;
import org.fenggui.theme.xml.InputOutputStream;
import org.fenggui.util.Color;

/**
 * Draws pixmaps as the background of a widget. The <coder>PixmapBackground</code>
 * has two modes of operation:
 * <ul>
 * <li>A single pixmap that is drawn in the center or scaled to fill the widget. It can be used
 * to display a plain image as the background.</li>
 * <li>A single pixmap that is drawn in the center and eight border-pixmaps that surround the
 * pixmap in the center. Again, the pixmap in the center can be scaled to fill the residual space. This mode
 * can be used to draw sharp edges where the inner pixmap is scaled. This comes in handy for inlay-borders for
 * example.</li>
 * </ul>
 * The pixmaps are aligned as follows where the centered pixmap can fill the entire space when
 * no surrounding pixmaps are specified:
 * <pre>
 * +-------------+--------+--------------+
 * |  top-left   |  top   |   top-right  |
 * +-------------+--------+--------------+
 * |    left     | center |    right     |
 * +-------------+--------+--------------+
 * | bottom-left | bottom | bottom-right |
 * +-------------+--------+--------------+
 * </pre>
 * 
 * @author Johannes Schaback, last edited by $Author: marcmenghin $, $Date: 2009-03-13 15:56:05 +0100 (Fr, 13 Mär 2009) $
 * @version $Revision: 606 $
 *
 */
public class StdBackground extends Background
{
  private static final Color DEFAULT_BLENDING_COLOR = Color.WHITE;
  private Color              blendingColor          = DEFAULT_BLENDING_COLOR;
  private boolean            scaled                 = false;
  private Pixmap             center                 = null;

  private boolean            useAlternateBlending   = false;

  public StdBackground(InputOnlyStream stream) throws IOException, IXMLStreamableException
  {
    process(stream);
  }
  public StdBackground(Pixmap p){
   this.center = p;
  }
  /**
   * Creates a new <code>PixmapBackground</code> with a centered pixmap and
   * the surrounding "border" pixmaps.
   * @param f Path to background file
   */
  public StdBackground(String f){
   try{
    this.center = new Pixmap(Binding.getInstance().getTexture(f));
   }catch(IOException e){
    Main.ERR_LOG.addE("StdBackground.new()", e);
   }
  }

  @Override
  public void paint(Graphics g, int localX, int localY, int width, int height)
  {
   g.setColor(blendingColor);
    // draw only the center
   g.drawImage(center, localX + width / 2 - center.getWidth() / 2, localY + height / 2 - center.getHeight() / 2);
  }

  public Color getBlendingColor()
  {
    return blendingColor;
  }

  public void setBlendingColor(Color blendingColor)
  {
    if (blendingColor == null)
      throw new IllegalArgumentException("blendingColor == null");
    this.blendingColor = blendingColor;
  }

  public boolean isScaled()
  {
    return false;
  }

  /* (non-Javadoc)
   * @see org.fenggui.io.IOStreamSaveable#process(org.fenggui.io.InputOutputStream)
   */
  @Override
  public void process(InputOutputStream stream) throws IOException, IXMLStreamableException{
    super.process(stream);

    blendingColor = stream.processChild("BlendingColor", blendingColor, DEFAULT_BLENDING_COLOR, Color.class);
    useAlternateBlending = stream.processAttribute("alternateBlending", useAlternateBlending, false);
    scaled = stream.processAttribute("scaled", scaled, false);

    center = stream.processChild("CenterPixmap", center, Pixmap.class);

  }

  /* (non-Javadoc)
   * @see org.fenggui.decorator.background.Background#copy()
   */
  @Override
 public IDecorator copy(){
  StdBackground result = new StdBackground(center);
  result.setBlendingColor(new Color(this.getBlendingColor()));
  result.useAlternateBlending = useAlternateBlending;
  super.copy(result);
  return result;
 }

 public boolean isUseAlternateBlending(){
  return false;
 }
}
