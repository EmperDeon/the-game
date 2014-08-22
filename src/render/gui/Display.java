package render.gui;
import java.util.ArrayList;
import javax.media.opengl.awt.GLCanvas;
import org.fenggui.IWidget;
import org.fenggui.binding.render.jogl.JOGLBinding;
import org.fenggui.event.WidgetListChangedEvent;
public class Display extends org.fenggui.Display{
 private final ArrayList<ArrayList<Integer>>   guiIds = new ArrayList<>(20);
 private final ArrayList<Integer>       alwaysVisible = new ArrayList<>(5);
 private Integer curr = 0;
 
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
  this.curr = id;
 }
 public synchronized void addWidget(Integer id, IWidget... widgets){
  ArrayList<Integer> ids = new ArrayList<>();
  
  
  for (IWidget w : widgets){

   notifyList.add(notifyList.size(), w);
   w.setParent(this);
   if (getDisplay() != null)
    w.addedToWidgetTree();
   
   ids.add(this.notifyList.size());
   if(!id.equals(curr))
    w.setVisible(false);
  }
  
  this.guiIds.add(id, ids);
  updateMinSize();
  widgetAdded(new WidgetListChangedEvent(this, widgets));
 }
 public synchronized void addWidget(Integer id, ArrayList<IWidget> widgets){
  addWidget(id, (IWidget[])widgets.toArray());
 }
}
