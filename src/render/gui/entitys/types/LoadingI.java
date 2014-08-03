package render.gui.entitys.types;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.Type;
import render.tex.Tex;
import render.tex.TexCoords;

public class LoadingI extends Entity{
 private final Tex tex;
 public LoadingI (GLAutoDrawable draw, Tex tex) {
  super(Type.Image , null);
  this.tex = tex;
 }
 
 @Override
 public void free( GL gl){
  tex.free(gl);
 }
 
 @Override
 public void action(){}
 
 @Override
 public void render(GLAutoDrawable drawable){
  GL gl = drawable.getGL();
  GL2 gl2 = gl.getGL2();
  
  gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);
  gl2.glLoadIdentity();
  gl2.glTranslatef(-1f, 1f, 0);
  gl2.glScalef(2.0f, -2.0f, 0f);
                
   tex.bind(gl);
   TexCoords crd = tex.getCoords();
  gl2.glBegin(GL2.GL_QUADS);
  
   gl2.glTexCoord2f(crd.left(), crd.top());
   gl2.glVertex3f(  0.0f, 0.0f, 0.0f);
                 
   gl2.glTexCoord2f(crd.right(), crd.top());
   gl2.glVertex3f(  1.0f, 0.0f, 0.0f);
                 
   gl2.glTexCoord2f(crd.right(), crd.bottom());
   gl2.glVertex3f(  1.0f, 1.0f, 0.0f);
                
   gl2.glTexCoord2f(crd.left(), crd.bottom());
   gl2.glVertex3f(  0.0f, 1.0f, 0.0f);
   
  gl2.glEnd();
  
  tex.unbind(gl);
 }
 
}
