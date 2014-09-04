package render.gui.widgets;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import main.Main;
import org.fenggui.appearance.TextAppearance;
import org.fenggui.binding.render.text.ITextRenderer;
import org.fenggui.event.key.IKeyListener;
import org.fenggui.event.key.KeyPressedEvent;
import org.fenggui.event.key.KeyReleasedEvent;
import org.fenggui.event.key.KeyTypedEvent;
import org.fenggui.event.mouse.IMouseListener;
import org.fenggui.event.mouse.MouseClickedEvent;
import org.fenggui.event.mouse.MouseDoubleClickedEvent;
import org.fenggui.event.mouse.MouseDraggedEvent;
import org.fenggui.event.mouse.MouseEnteredEvent;
import org.fenggui.event.mouse.MouseExitedEvent;
import org.fenggui.event.mouse.MouseMovedEvent;
import org.fenggui.event.mouse.MousePressedEvent;
import org.fenggui.event.mouse.MouseReleasedEvent;
import org.fenggui.event.mouse.MouseWheelEvent;
import org.fenggui.text.content.factory.simple.TextStyle;
import org.fenggui.util.Alphabet;
import org.fenggui.util.Color;
import org.fenggui.util.fonttoolkit.FontFactory;

public class StdButton extends StdWidget{
 
 public StdButton(String text,  ActionListener a){
  super("res/stdbe.png","res/stdbf.png","res/stdbd");
  setText(text);
  this.setEnabled(false);
  setFont();
  setHooks(new IMouseListener() {
   @Override public void mouseDragged ( MouseDraggedEvent mouseDraggedEvent ) {}    
   @Override public void mouseMoved ( MouseMovedEvent mouseMovedEvent ) {}
   @Override public void mousePressed ( MousePressedEvent mousePressedEvent ) {}
   @Override public void mouseClicked ( MouseClickedEvent mouseClickedEvent ) {a.actionPerformed(null);}    
   @Override public void mouseReleased ( MouseReleasedEvent mouseReleasedEvent ) {}
   
   @Override public void mouseEntered ( MouseEnteredEvent mouseEnteredEvent ) {}
   @Override public void mouseExited ( MouseExitedEvent mouseExited ) {}
   @Override public void mouseDoubleClicked (MouseDoubleClickedEvent mouseDoubleClickedEvent ) {}    
   @Override public void mouseWheel ( MouseWheelEvent mouseWheelEvent ) {}
  }, 
   new IKeyListener(){
   @Override public void keyPressed ( KeyPressedEvent keyPressedEvent ) {}
   @Override public void keyReleased ( KeyReleasedEvent keyReleasedEvent ) {}
   @Override public void keyTyped ( KeyTypedEvent keyTypedEvent ) {}
  });
 }
 
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
   cust = Font.createFont(Font.TRUETYPE_FONT,new File(Main.mdir+"res/font.ttf")).deriveFont(16f);
  } catch ( FontFormatException | IOException ex ) {
   Main.ERR_LOG.addE("StandartButton.getFont()", ex);
  }
  
  renderer.setFont(FontFactory.renderStandardFont(cust , true, Alphabet.getDefaultAlphabet()));
  appearance.addRenderer(ITextRenderer.DEFAULTTEXTRENDERERKEY, renderer);
 }
}
