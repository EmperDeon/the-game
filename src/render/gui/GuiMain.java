package render.gui;

import javax.media.opengl.GL2;
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
    
 public void initI(GLAutoDrawable drawable){

   rendlist.add(new Image(new Tex(drawable.getGL() ,"/usr/games/game/res/test.png")));
   //texture = Tex.loadClassPath(drawable.getGL(), "/jogl/util/data/av/test-ntsc01-160x90.png");

 }
 
 public void initM(GLAutoDrawable drawable){
  rendlist.free(drawable); 
  rendlist.clear();

   rendlist.add(new Button(new Vec4i(0,0,20,20),
                "-", 
                new Tex(
                 drawable.getGL(),
                 "/usr/games/game/res/test.png"))
               );

 }
 
 public void reshapeI(GLAutoDrawable drawable){
  //
 }
 
 public void renderI(GLAutoDrawable draw){
  rendlist.render(draw);
  draw.getGL().getGL2().glBindTexture(GL2.GL_TEXTURE_2D , 0);
 }
  
 public void renderM(GLAutoDrawable drawable){
  rendlist.render(drawable);
 }

 public void reshapeM(GLAutoDrawable drawable){
 }
 
}
