package render.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import render.Tex;

public class GuiMain {
 
 private Tex texture ;
 
 public GuiMain(){
  
 }
    
 public void initI(GLAutoDrawable drawable){
  try {
   texture = new Tex(drawable.getGL(), new FileInputStream(new File("/usr/games/game/res/init.png")));
   //texture = Tex.loadClassPath(drawable.getGL(), "/jogl/util/data/av/test-ntsc01-160x90.png");
  } catch ( IOException ex ) {

  }
 }
 
 public void reshapeI(GLAutoDrawable drawable){
  //
 }
 
 public void renderI(GLAutoDrawable drawable){
  GL2 gl2 = drawable.getGL().getGL2();
  gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);
  gl2.glLoadIdentity();
  gl2.glTranslatef(-1f, 1f, 0);
  gl2.glScalef(2.0f, -2.0f, 0f);
                
  if(texture != null) 
   texture.bind(gl2);
  
  gl2.glBegin(GL2.GL_QUADS);
  gl2.glTexCoord2f(0, 0);
  gl2.glVertex3f(0.0f, 0.0f, 0.0f);
                
  gl2.glTexCoord2f(1, 0);
  gl2.glVertex3f(1.0f, 0.0f, 0.0f);
                
  gl2.glTexCoord2f(1, 1);
  gl2.glVertex3f(1.0f, 1.0f, 0.0f);
               
  gl2.glTexCoord2f(0, 1);
  gl2.glVertex3f(0.0f, 1.0f, 0.0f);
  gl2.glEnd();
 }
  
 public void renderM(GLAutoDrawable drawable){
  
 }

 public void reshapeM(GLAutoDrawable drawable){
 }
 
}
