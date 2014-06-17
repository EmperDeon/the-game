package render.gui.entitys;

import render.gui.entitys.types.Entity;
import java.util.ArrayList;
import javax.media.opengl.GLAutoDrawable;

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
 
 public void free(GLAutoDrawable drawable){
 for(Entity ent : list)
   ent.free(drawable);
  
  }
}
