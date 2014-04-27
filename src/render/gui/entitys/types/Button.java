package render.gui.entitys.types;

import utils.vec.Vec4i;

public class Button extends Entity{
 public String s;
 
 public Button ( int t , Vec4i pos , String s ) {
  super(t , pos );
  this.s = s;
 }
 
 @Override
 public void render(){
  
 }
}
