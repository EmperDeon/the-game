package render.gui;

import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.RList;
import utils.GuiId;

public class GuiMain {

 private GuiId gid = new GuiId(0);
 private RList rendlist;
 public GuiMain(){
  
 }
    
 public void initI(GLAutoDrawable draw){
  rendlist = new RList(draw);
  

  //rendlist.addL( STD_LOAD );
 }
 
 public void initM(GLAutoDrawable draw){

  gid = new GuiId(1);
  
 // rendlist.save();
 }
 
 public void reshape(GLAutoDrawable drawable){
  //
 }
 
 public void render(GLAutoDrawable draw){
  rendlist.render(draw, gid);
 }

}
