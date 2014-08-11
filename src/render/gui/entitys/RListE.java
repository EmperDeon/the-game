package render.gui.entitys;

import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.types.Button;
import render.gui.entitys.types.Entity;
import render.gui.entitys.types.LoadingI;
import utils.Action;
import utils.GuiId;
import utils.vec.Vec4;

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
 public void render(GLAutoDrawable draw, GuiId id){
   System.out.println("Render "+this.list.size()+" elements");
  for ( Entity ent : this.list ) {
   ent.render(draw);
  }
 }
 public void free(GL gl){ for(Entity ent : this.list) ent.free(gl); }
 public void clear (){ this.list.clear(); }
}

