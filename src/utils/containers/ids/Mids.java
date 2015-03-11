package utils.containers.ids;
import java.util.*;
import utils.containers.id.Mid;

public class Mids {
 private final List<Mid> list = new ArrayList<>();
 
 public Mids(){
 
 }
 
 public void put(Mid mid){
  list.add(mid);
 }
 
 public Mid get(String m, String i, String s){
  for(Mid id : list){
   if(m.equals(id.getMid()) && i.equals(id.getIid()) && s.equals(id.getSid()))
    return id;
  }return null;
 }
 
 public boolean contains(String m, String i, String s){
  for(Mid id : list){
   if(m.equals(id.getMid()) && i.equals(id.getIid()) && s.equals(id.getSid()))
    return true;
  }
  
  return false;
 }
 
}
