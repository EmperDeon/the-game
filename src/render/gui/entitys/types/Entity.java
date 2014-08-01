package render.gui.entitys.types;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.Type;
import utils.vec.Vec4;

public class Entity {
 public Type t;
 public Vec4<Double> pos;

 public Entity(int t, Vec4<Double> pos){
  this.t = new Type(t);
  this.pos = pos;
 }
 
 public void render(GLAutoDrawable drawable){}
 
 public void free(GL gl){}
}
