package render.gui.entitys.types;

import utils.vec.Vec4i;

public class Label extends Entity{
 public String s;
 
 public Label ( int t , Vec4i pos , String s) {
  super(t , pos);
  this.s = s;
 }

 @Override
 public void render(){
  
 }
}
