package render.gui.entitys.types;

import javax.media.opengl.GLAutoDrawable;
import utils.vec.Vec4;

public class Label extends Entity{
 public String s;
 
 public Label ( int t , Vec4<Double> pos , String s) {
  super(t , pos);
  this.s = s;
 }

 @Override
 public void render(GLAutoDrawable drawable){
  
 }
}
