package utils.containers.ids;
import java.util.*;

public class Mids {
 private final List<Mid> list = new ArrayList<>();
 
 public Mids(){
 
 }
 
 public void put(Mid mid){
  list.add(mid);
 }
 
 public Mid get(String m, String i, String s){
  for(Mid id : list){
   if(m.equals(id.mid) && i.equals(id.iid) && s.equals(id.sid))
    return id;
  }return null;
 }
 
 public boolean contains(String m, String i, String s){
  for(Mid id : list){
   if(m.equals(id.mid) && i.equals(id.iid) && s.equals(id.sid))
    return true;
  }
  
  return false;
 }
 
}
