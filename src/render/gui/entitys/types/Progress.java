package render.gui.entitys.types;

import utils.vec.Vec4i;

public class Progress extends Entity{
 public int prog;
 
 public Progress ( int t , Vec4i pos ,int prog) {
  super(t , pos);
  this.prog = prog;
 }
 
 @Override
 public void render(){
  
 };
}
