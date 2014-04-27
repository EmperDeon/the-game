package render.gui.entitys;

import render.gui.entitys.types.Entity;
import java.util.ArrayList;

public class RList {
 public ArrayList<Entity> list;
 
 public RList(){
  this.list = new ArrayList();
 }
 
 public void add(Entity ent){
  this.list.add(ent);
 }
 
 public void render(){
  for(Entity ent : list)
   ent.render();
  
 }
}
