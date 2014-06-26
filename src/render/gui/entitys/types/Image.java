package render.gui.entitys.types;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import render.Tex;
import render.gui.entitys.Type;
import utils.vec.Vec4i;

public class Image extends Entity{
 private final Tex tex;
 public Image (Tex tex) {
  super(Type.Image , new Vec4i(0,0,0,0));
  this.tex = tex;
 }
 
 @Override
 public void free( GL gl){
  tex.free(gl);
 }

 @Override
 public void render(GLAutoDrawable drawable){
  GL gl = drawable.getGL();
  GL2 gl2 = gl.getGL2();
  
  gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);
  gl2.glLoadIdentity();
  gl2.glTranslatef(-1f, 1f, 0);
  gl2.glScalef(2.0f, -2.0f, 0f);
                
   tex.bind(gl);
  
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
  
  tex.unbind(gl);
 }
}
