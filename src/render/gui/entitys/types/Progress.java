package render.gui.entitys.types;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import utils.vec.Vec4;

public class Progress extends Entity{
 public int prog;
 
 public Progress ( int t , Vec4<Integer> pos ,int prog) {
  super(t , pos);
  this.prog = prog;
 }
 
 @Override
 public void render(GLAutoDrawable drawable){
  
 }
 
 @Override public void action(){}
 @Override public void free ( GL gl ) {}
}
