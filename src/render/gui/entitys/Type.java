package render.gui.entitys;

import java.io.Serializable;

public class Type implements Serializable{
 
 public final static int Label = 0;
 public final static int Button = 1;
 public final static int Progress = 2;
 public final static int Image = 3;
 public int type;
 
 public Type(int t){
  this.type = t;
 }
 
 @Override
 public int hashCode(){
  return type;
 }

 @Override
 public boolean equals ( Object obj ) {
  if ( obj == null ) {
   return false;
  }
  if ( getClass() != obj.getClass() ) {
   return false;
  }
  final Type other = ( Type ) obj;
  if ( this.type != other.type ) {
   return false;
  }
  return true;
 }
}
