package render.gui.entitys.types;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import utils.vec.Vec4;

public class Label extends Entity{
 private final String s;
 private final Vec4<Integer> pos;
 
 public Label ( int t , Vec4<Integer> pos , String s) {
  super(t , pos);
  this.s   = s;
  this.pos = pos;
 }

 @Override
 public void render(GLAutoDrawable drawable){
  
 }
 

 @Override public void action(){}
 @Override public void free ( GL gl ) {}
}
