package render.gui;

import java.util.ArrayList;
import javax.media.opengl.awt.GLCanvas;
import org.fenggui.IWidget;
import org.fenggui.binding.render.jogl.JOGLBinding;
import org.fenggui.event.WidgetListChangedEvent;
import org.fenggui.util.Log;

public class Display extends org.fenggui.Display{
 private final ArrayList<ArrayList<Integer>>   guiIds = new ArrayList<>(20);
 private final ArrayList<Integer>       alwaysVisible = new ArrayList<>(5);
 
 public Display (GLCanvas canvas){
  super(new JOGLBinding(canvas));
 }
 
 public synchronized void changeGui(Integer id){
  Integer i = 0;
  ArrayList<Integer> ids = guiIds.get(id);
  for(IWidget w : this.notifyList){
   if(ids.contains(i) || alwaysVisible.contains(i))
    w.setVisible(true);
   else
    w.setVisible(false);
   
   i++;
  }
 }

 public synchronized void addWidget(Integer id, IWidget... widgets){
  ArrayList<Integer> ids = new ArrayList<>();
  for (IWidget w : widgets){
   addWidgetInternal(w, this.notifyList.size());
   ids.add(this.notifyList.size());
  }
  this.guiIds.add(id, ids);
  updateMinSize();
  widgetAdded(new WidgetListChangedEvent(this, widgets));
 }
  
 private synchronized void addWidgetInternal(IWidget c, int position)
 {
  if (position > notifyList.size())
        position = notifyList.size();

      if (notifyList.contains(c))
      {
        Log.warn("Container.addWidget: Widget " + c + " is already in the container (" + this + ")");
      }
      else
      {
        //if(relyFocus() == null && c.relyFocus() != null) setRelyFocus(c);
        notifyList.add(position, c);

        c.setParent(this);

        if (getDisplay() != null)
          c.addedToWidgetTree();
      }
  }

  
}
