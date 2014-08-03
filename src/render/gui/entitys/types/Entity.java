package render.gui.entitys.types;

import java.io.Serializable;
import java.util.Objects;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.Type;
import utils.vec.Vec4;

public abstract class Entity implements Serializable{
 public  final Type t;
 private final Vec4<Integer> pos;

 public Entity(int t, Vec4<Integer> pos){
  this.t = new Type(t);
  this.pos = pos;
 }
 
 public abstract void render(GLAutoDrawable drawable);
 public abstract void action ();
 public abstract void free(GL gl);

 //@Override
 //public int hashCode(){
 // return t.hashCode() + pos.hashCode();
// }

 @Override
 public boolean equals ( Object obj ) {
  if ( obj == null ) {
   return false;
  }
  if ( getClass() != obj.getClass() ) {
   return false;
  }
  final Entity other = ( Entity ) obj;
  if ( !Objects.equals(this.t , other.t) ) {
   return false;
  }
  if ( !Objects.equals(this.pos , other.pos) ) {
   return false;
  }
  return true;
 }
}
