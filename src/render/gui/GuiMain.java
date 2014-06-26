package render.gui;

import javax.media.opengl.GLAutoDrawable;
import render.Tex;
import render.gui.entitys.RList;
import render.gui.entitys.types.Button;
import render.gui.entitys.types.Image;
import utils.vec.Vec4i;

public class GuiMain {
 
 private final RList rendlist = new RList();
 public GuiMain(){
  
 }
    
 public void initI(GLAutoDrawable draw){

   rendlist.add(new Image(new Tex(draw.getGL() ,"/usr/games/game/res/init.png")));

 }
 
 public void initM(GLAutoDrawable draw){
  rendlist.free(draw.getGL()); 
  rendlist.clear();

  rendlist.add(new Button(new Vec4i(0,0,20,20),"-",new Tex(draw.getGL(),"/usr/games/game/res/bstart.png")));

 }
 
 public void reshapeI(GLAutoDrawable drawable){
  //
 }
 
 public void renderI(GLAutoDrawable draw){
  rendlist.render(draw);
 }
  
 public void renderM(GLAutoDrawable draw){
  rendlist.render(draw);
 }

 public void reshapeM(GLAutoDrawable draw){
 }
 
}
