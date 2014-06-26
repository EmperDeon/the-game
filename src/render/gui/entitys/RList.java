package render.gui.entitys;

import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.types.Entity;

public class RList {
 public ArrayList<Entity> list;
 
 public RList(){
  this.list = new ArrayList();
 }
 
 public void add(Entity ent){
  this.list.add(ent);
 }
 
 public void render(GLAutoDrawable drawable){
  for ( Entity ent : list ) {
   ent.render(drawable);
  }
  
 }
 
 public void clear(){
  list.clear();
 }
 
 public void free(GL gl){
 for(Entity ent : list)
   ent.free(gl);
  
  }
}
