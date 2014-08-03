package render.gui.entitys;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.types.Button;
import render.gui.entitys.types.Entity;
import render.gui.entitys.types.LoadingI;
import render.tex.Tex;
import utils.Action;
import utils.GuiId;
import utils.vec.Vec4;

public final class RList implements Serializable{
 private final String file = main.Main.mdir+"res/gui.list";
 private final String STD_LOAD = "/usr/games/game/res/init.png";
 private final String STD_BUTT = "/usr/games/game/res/button.png";
 
 private final HashMap<GuiId,HashSet<Entity>> map;
 private final HashSet<Entity> list;
 private final GLAutoDrawable draw;
 private GuiId curr;                         // 0 - LoadingI
 
 public RList(GLAutoDrawable draw){
  this.map    = new HashMap  <>();
  this.list   = new HashSet  <>();
  this.curr = new GuiId(0);
  this.draw = draw;
  init(); 
 }
 
 private void init(){
  HashSet<Entity> v = new HashSet<>(); 
  // 0 - Loading
  v.add(new LoadingI(draw, new Tex(draw.getGL() ,STD_LOAD)));
  map.put(new GuiId(0), v);
  v.clear();
  
  // 1 - Main menu
  v.add(new Button(draw, new Vec4<>(10,10,200,200)," Label ", new Action() {@Override
   public void action () {
   
   }
  },new Tex(draw.getGL(),STD_BUTT)));
  map.put(new GuiId(1), v);
  v.clear();
  
  // 2 - ?
  
 }
 
 public void put(GuiId k, HashSet<Entity> v){map.put(k , v);}
 public void render(GLAutoDrawable draw, GuiId id){
  if(!id.equals(curr)){
   this.list.clear();
   this.list.addAll(map.get(id));
   this.curr = id;
  }
  
  for ( Entity ent : this.list ) {
   ent.render(draw);
  }
 }
 public void free(GL gl){ for(Entity ent : this.list) ent.free(gl); }
}
/* 
 
 public void add(Entity ent){
  this.list.add(ent);
 }
 
 public void addB(GLAutoDrawable draw, Vec4<Integer> pos , String s , Action act, String t){
  this.list.add(new Button(draw, pos, s, act, new Tex(draw.getGL(), t)));
 }
 
 public void addL(GLAutoDrawable draw, String t){
  this.list.add(new LoadingI(draw, new Tex(draw.getGL() ,t)));
 }
  
 public void change(GuiId id, GLAutoDrawable draw){
  if(this.curr != id){
   if(this.map.containsKey(id))
    this.map.remove(id);
   this.map.put(id , list);
   this.list.clear();
   if(this.map.containsKey(id))
    this.list.addAll(map.get(id));
   this.curr = id;
   
   for(Entity e : this.list)
    e.reinit(draw);
  }
  
 public void load(GLAutoDrawable draw){
  try {
   ObjectInputStream serial = new ObjectInputStream(new FileInputStream(file));
   RList tmp = ( RList ) serial.readObject();
   
   this.map.   clear(); this.map.   putAll(tmp.map);
   
   change(new GuiId(0), draw);
  } catch (IOException | ClassNotFoundException ex) {
   main.Main.ERR_LOG.addE("RList.load()",ex);
  }
  
  System.out.println("Loaded "+map.size()+" elements from gui.list");
 }
 
 public void save(){
  try (ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(file))) {
   serial.writeObject(this);
  } catch (IOException ex) {
   main.Main.ERR_LOG.addE("RList.save()", ex);
  }
  
  System.out.println("Saved "+map.size()+" elements to gui.list");
 }*/