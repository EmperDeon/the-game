package render.gui;

import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.RList;
import utils.vec.Vec4;

public class GuiMain {
 private final String STD_LOAD = "/usr/games/game/res/init.png";
 private final String STD_BUTT = "/usr/games/game/res/bstart.png";
 
 private final RList rendlist = new RList();
 public GuiMain(){
  
 }
    
 public void initI(GLAutoDrawable draw){
  rendlist.draw(draw);
  rendlist.addL( STD_LOAD );

 }
 
 public void initM(GLAutoDrawable draw){
  rendlist.free(draw.getGL()); 
  rendlist.clear();
  
  rendlist.addB(new Vec4<>(0d,0d,20d,20d)," Label ",STD_BUTT);

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
