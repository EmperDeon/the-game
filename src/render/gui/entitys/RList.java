package render.gui.entitys;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.types.Button;
import render.gui.entitys.types.Entity;
import render.gui.entitys.types.LoadingI;
import utils.Action;
import utils.vec.Vec4;
public final class RList{
 private final ArrayList<Entity> map;
 private final ArrayList<ArrayList<Integer>> ids;  
 
 public RList(){
  this.map  = new ArrayList <>(10);
  this.ids  = new ArrayList <>(10);
 }

 public void render(GLAutoDrawable draw, Integer id){
  for(Integer i : this.ids.get(id))
   this.map.get(i).render(draw);
 }
 public void free(GL gl){ for(Entity ent : this.map) ent.free(gl); this.ids.clear();}
 public void add(Integer id, Entity v){
  map.add(v);
  this.ids.get(id).add(map.size());
 }
 public void addId(Integer id){
  this.ids.add(id, new ArrayList<Integer>());
 }
 public void addInit(Integer id, GLAutoDrawable draw){
  map.add(new LoadingI(draw)); 
  this.ids.get(id).add(map.size()-1);
 }
 public void addButton(Integer id, GLAutoDrawable draw, Vec4<Integer> pos, String label, Action act){
  map.add(new Button(draw, pos, label, act));
  this.ids.get(id).add(map.size()-1);
 }
} 