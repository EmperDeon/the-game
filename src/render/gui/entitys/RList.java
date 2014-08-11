package render.gui.entitys;

import java.util.ArrayList;
import java.util.TreeMap;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.types.Button;
import render.gui.entitys.types.Entity;
import render.gui.entitys.types.LoadingI;
import utils.Action;
import utils.GuiId;
import utils.vec.Vec4;

public final class RList{
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
 public void render(GLAutoDrawable draw){this.map.get(curr).render(draw); }
 public void free(GL gl){ for(RListE ent : this.map.values()) ent.free(gl); }

 public final class RListE {
  private final ArrayList<Entity> list;
  public RListE(GLAutoDrawable draw){
   this.list = new ArrayList <>();
  }
  public void add(Entity v){list.add(v);}
  public void addInit(GLAutoDrawable draw){ list.add(new LoadingI(draw)); }
  public void addButton(GLAutoDrawable draw, Vec4<Integer> pos, String label, Action act){
   list.add(new Button(draw, pos, label, act));
  }
  public void addAll(ArrayList<Entity> c) { list.addAll(c); }
  public void render(GLAutoDrawable draw){
    System.out.println("Render "+this.list.size()+" elements");
   for ( Entity ent : this.list ) {
    ent.render(draw);
   }
  }
  public void free(GL gl){ for(Entity ent : this.list) ent.free(gl); }
  public void clear (){ this.list.clear(); }
 }
}