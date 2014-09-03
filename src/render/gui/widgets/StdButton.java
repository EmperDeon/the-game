package render.gui.widgets;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import main.Main;
import org.fenggui.appearance.TextAppearance;
import org.fenggui.binding.render.text.ITextRenderer;
import org.fenggui.event.key.IKeyListener;
import org.fenggui.event.mouse.IMouseListener;
import org.fenggui.text.content.factory.simple.TextStyle;
import org.fenggui.util.Alphabet;
import org.fenggui.util.Color;
import org.fenggui.util.fonttoolkit.FontFactory;

public class StdButton extends StdWidget{

 public StdButton(String text, IMouseListener l, IKeyListener k){
  super("res/stdbe.png","res/stdbf.png","res/stdbd");
  setText(text);
  this.setEnabled(false);
  this.setHooks(l , k);
  setFont();
 }
 
 @Override
 public void resize(int w, int h){
//   setXY((w - 200)/2,(h - 40)/2);
//   setWidth(200);
//   setHeight(40);
 }
 private void setFont(){
  TextAppearance appearance = this.getAppearance();
  TextStyle def = new TextStyle();
  def.getTextStyleEntry(TextStyle.DEFAULTSTYLEKEY).setColor(Color.WHITE);
  appearance.addStyle(TextStyle.DEFAULTSTYLEKEY, def);

  ITextRenderer renderer = appearance.getRenderer(ITextRenderer.DEFAULTTEXTRENDERERKEY).copy();
  Font cust = null;
  try {
   cust = Font.createFont(Font.TRUETYPE_FONT,new File(Main.mdir+"res/font.ttf")).deriveFont(12f);
  } catch ( FontFormatException | IOException ex ) {
   Main.ERR_LOG.addE("StandartButton.getFont()", ex);
  }
  
  renderer.setFont(FontFactory.renderStandardFont(cust , true, Alphabet.getDefaultAlphabet()));
  appearance.addRenderer(ITextRenderer.DEFAULTTEXTRENDERERKEY, renderer);
 }
}
