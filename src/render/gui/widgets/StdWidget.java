package render.gui.widgets;

import java.io.IOException;
import main.Main;
import org.fenggui.Button;
import org.fenggui.binding.render.Binding;
import org.fenggui.binding.render.Pixmap;
import org.fenggui.decorator.background.PixmapBackground;
import org.fenggui.util.Dimension;

/**
 * A simple way to create a game menu.
 *
 * @author charlierby, last edited by $Author$, $Date$
 * @version $Revision$
 */
public class StdWidget extends Button {

 Dimension size = new Dimension(5 , 5);

 public StdWidget ( String enabled , String focused , String disabled ) {
  try {
   Pixmap enabledp = new Pixmap(Binding.getInstance().getTexture(
           Main.mdir + enabled));
   Pixmap focusedp = new Pixmap(Binding.getInstance().getTexture(
           Main.mdir + focused));
   Pixmap disabledp = new Pixmap(Binding.getInstance().getTexture(
           Main.mdir + disabled));

   initButton(enabledp , focusedp , focusedp , focusedp , disabledp);
  } catch ( IOException e ) {
   Main.LOG.addE(e);
  }
 }

 private void initButton ( Pixmap defaultState , Pixmap hoverState ,
                           Pixmap focusState , Pixmap pressedState ,
                           Pixmap disabledState ) {
  getAppearance().removeAll();
  getAppearance().clearSpacings();

  size.setSize(defaultState.getWidth() , defaultState.getHeight());

  getAppearance().add(STATE_NONE , new PixmapBackground(defaultState , true));
  getAppearance().add(STATE_HOVERED , new PixmapBackground(hoverState , true));
  getAppearance().add(STATE_FOCUSED , new PixmapBackground(focusState , true));
  getAppearance().add(STATE_PRESSED , new PixmapBackground(pressedState , true));
  getAppearance().add(STATE_DISABLED ,
                      new PixmapBackground(disabledState , true));
  updateState();
  updateMinSize();
 }

 @Override
 public Dimension getMinContentSize () {
  return size;
 }

 public void setMinContentSize ( Dimension size ) {
  this.size = size;
 }

 /*
  * (non-Javadoc)
  *
  * @see org.fenggui.StatefullWidget#updateState(java.lang.String)
  */
 @Override
 protected void updateState ( String newActiveState ) {
  // change update state to only activate one state at a time
  // only default state is active as usual
  // re-enable default state so switches get called
  getAppearance().setEnabled(STATE_DEFAULT , true);

  if ( newActiveState != null ) {
   getAppearance().setEnabled(STATE_DISABLED , false);
   getAppearance().setEnabled(STATE_FOCUSED , false);
   getAppearance().setEnabled(STATE_NONE , false);
   getAppearance().setEnabled(STATE_ERROR , false);
   getAppearance().setEnabled(STATE_HOVERED , false);
   getAppearance().setEnabled(STATE_PRESSED , false);
   getAppearance().setEnabled(newActiveState , true);
  } else {
   if ( isEnabled() ) {
    if ( hasError() ) {
     getAppearance().setEnabled(STATE_ERROR , true);
     getAppearance().setEnabled(STATE_DISABLED , false);
     getAppearance().setEnabled(STATE_FOCUSED , false);
     getAppearance().setEnabled(STATE_NONE , false);
     getAppearance().setEnabled(STATE_HOVERED , false);
     getAppearance().setEnabled(STATE_PRESSED , false);
    } else {
     if ( isPressed() ) {
      getAppearance().setEnabled(STATE_DISABLED , false);
      getAppearance().setEnabled(STATE_FOCUSED , false);
      getAppearance().setEnabled(STATE_NONE , false);
      getAppearance().setEnabled(STATE_ERROR , false);
      getAppearance().setEnabled(STATE_HOVERED , false);
      getAppearance().setEnabled(STATE_PRESSED , true);
     } else {
      if ( isFocused() ) {
       getAppearance().setEnabled(STATE_DISABLED , false);
       getAppearance().setEnabled(STATE_FOCUSED , true);
       getAppearance().setEnabled(STATE_NONE , false);
       getAppearance().setEnabled(STATE_ERROR , false);
       getAppearance().setEnabled(STATE_HOVERED , false);
       getAppearance().setEnabled(STATE_PRESSED , false);
      } else {
       if ( isHovered() ) {
        getAppearance().setEnabled(STATE_DISABLED , false);
        getAppearance().setEnabled(STATE_FOCUSED , false);
        getAppearance().setEnabled(STATE_NONE , false);
        getAppearance().setEnabled(STATE_ERROR , false);
        getAppearance().setEnabled(STATE_PRESSED , false);
        getAppearance().setEnabled(STATE_HOVERED , true);
       } else {
        getAppearance().setEnabled(STATE_DISABLED , false);
        getAppearance().setEnabled(STATE_FOCUSED , false);
        getAppearance().setEnabled(STATE_NONE , true);
        getAppearance().setEnabled(STATE_ERROR , false);
        getAppearance().setEnabled(STATE_HOVERED , false);
        getAppearance().setEnabled(STATE_PRESSED , false);
       }
      }
     }
    }
   } else {
    getAppearance().setEnabled(STATE_DISABLED , true);
    getAppearance().setEnabled(STATE_FOCUSED , false);
    getAppearance().setEnabled(STATE_ERROR , false);
    getAppearance().setEnabled(STATE_NONE , false);
    getAppearance().setEnabled(STATE_PRESSED , false);
    getAppearance().setEnabled(STATE_HOVERED , false);
   }

  }
 }
}
