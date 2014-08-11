package render.gui.entitys;

import java.io.Serializable;
import java.util.TreeMap;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import utils.Action;
import utils.GuiId;
import utils.vec.Vec4;

public final class RList implements Serializable{
 private final TreeMap<GuiId, RListE> map;
 private final GLAutoDrawable draw;
 private GuiId curr;        
 
 public RList(GLAutoDrawable draw){
  this.map    = new TreeMap  <>();
  this.curr = new GuiId(0);
  this.draw = draw;
  init(); 
 }
 
 private void init(){
  RListE v = new RListE(draw); 
  // 0 - Loading
  v.addInit(draw);
  this.map.put(new GuiId(0), v);
  v.clear();
  
  // 1 - Main menu
  v.addButton(draw, new Vec4<>(10,10,200,200)," Label ", new Action() {@Override
   public void action () {
   
   }
  });
  this.map.put(new GuiId(1), v);
  v.clear();
  
  // 2 - ?
  
 }
 
 public void change(GuiId k){ this.curr = k; }
 public void put(GuiId k, RListE v){map.put(k, v);}
 public void render(GLAutoDrawable draw){this.map.get(curr);}
 public void free(GL gl){ for(RListE ent : this.map.values()) ent.free(gl); }
}