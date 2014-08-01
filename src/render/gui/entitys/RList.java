package render.gui.entitys;

import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.types.Button;
import render.gui.entitys.types.Entity;
import utils.vec.Vec4;

public class RList {
 private final ArrayList<Entity> list;
 private GLAutoDrawable draw;
 
 public RList(){
  this.list = new ArrayList<>();
 }
 
 public void draw(GLAutoDrawable draw){
  this.draw = draw;
 }
 
 public void add(Entity ent){
  this.list.add(ent);
 }
 
 public void addB(Vec4<Double> pos , String s /*, Action act*/, String t){
  this.list.add(new Button(draw, pos, s, t));
 }
 
 public void addL(String t){
  
 }
 
 public void render(GLAutoDrawable drawable){
  for ( Entity ent : this.list ) {
   ent.render(drawable);
  }
  
 }
 
 public void clear(){
  this.list.clear();
 }
 
 public void free(GL gl){
 for(Entity ent : this.list)
   ent.free(gl);
  
  }
}
