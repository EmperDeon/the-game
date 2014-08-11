package render.gui;

import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.RList;
import utils.GuiId;

public class GuiMain {
 private RList rendlist;
 public GuiMain(){
  
 }
    
 public void initI(GLAutoDrawable draw){
  rendlist = new RList(draw);
  rendlist.change(new GuiId(0));
 }
 
 public void initM(GLAutoDrawable draw){
  rendlist.change(new GuiId(1));
 }
 
 public void reshape(GLAutoDrawable drawable){
  //
 }
 
 public void render(GLAutoDrawable draw){
  rendlist.render(draw);
 }

}
