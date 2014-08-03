package utils;
import java.io.Serializable;
import java.util.Objects;

public class GuiId implements Id, Serializable{
  private final Integer id;
  
  public GuiId(Integer id){
   this.id = id;
  }
  public int getId(){
   return id;
  }
  @Override public int hashCode(){
   return id.hashCode();
  }
  @Override public boolean equals ( Object obj ) {
   if ( obj == null ) {
    return false;
   }
   if ( getClass() != obj.getClass() ) {
    return false;
   }
   final GuiId other = ( GuiId ) obj;
   if ( !Objects.equals(this.id , other.id) ) {
    return false;
   }
   return true;
  }
 }
