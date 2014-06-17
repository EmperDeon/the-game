package render.gui.entitys.types;

import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.Type;
import utils.vec.Vec4i;

public class Entity {
 public Type t;
 public Vec4i pos;

 public Entity(int t, Vec4i pos){
  this.t = new Type(t);
  this.pos = pos;
 }
 
 public void render(GLAutoDrawable drawable){}
 
 public void free( GLAutoDrawable drawable){}
}
