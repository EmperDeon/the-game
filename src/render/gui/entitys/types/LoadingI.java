package render.gui.entitys.types;

import com.jogamp.opengl.util.texture.TextureCoords;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.Type;
import render.Tex;

public class LoadingI extends Entity{
 private final Tex tex;
 public LoadingI (GLAutoDrawable draw) {
  super(Type.Image , null);
  this.tex = new Tex(draw.getGL() ,"/usr/games/game/res/init.png");
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
                
   gl2.glEnable(GL2.GL_TEXTURE_2D);
   gl2.glEnable(GL2.GL_BLEND);
   gl2.glEnable(GL2.GL_ALPHA_TEST);
   gl2.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
   gl2.glAlphaFunc(GL2.GL_EQUAL, 1.0F);
   tex.bind(gl);
   gl2.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
   gl2.glPushMatrix();

  TextureCoords crd = tex.coords;
  
  gl2.glBegin(GL2.GL_QUADS);
  
   gl2.glTexCoord2f(crd.left(),  crd.top());
   gl2.glVertex3f(  0.0f ,  0.0f, 0.0f);
                 
   gl2.glTexCoord2f(crd.right(), crd.top());
   gl2.glVertex3f(  1.0f  , 0.0f, 0.0f);
                 
   gl2.glTexCoord2f(crd.right(), crd.bottom());
   gl2.glVertex3f(  1.0f  , 1.0f, 0.0f);
                
   gl2.glTexCoord2f(crd.left(),  crd.bottom());
   gl2.glVertex3f(  0.0f,   1.0f, 0.0f);
   
  gl2.glEnd();

  gl2.glPopMatrix();
  tex.unbind(gl);
  gl2.glDisable(GL2.GL_ALPHA_TEST);
  gl2.glDisable(GL2.GL_BLEND);
  gl2.glDisable(GL2.GL_TEXTURE_2D);
 }
 
}
