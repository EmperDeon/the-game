package render.gui.entitys.types;

import com.jogamp.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import render.Tex;
import render.gui.entitys.Type;
import utils.vec.Vec4i;

public class Button extends Entity{
 public String s;
 private Boolean firstrun = true; 
 private Tex tex ;
 //public Action act;
 public Button ( Vec4i pos , String s /*, Action act*/, Tex tex) {
  super(Type.Button , pos );
  this.s = s;
 }
 
 @Override
 public void render(GLAutoDrawable drawable){
  GL gl = drawable.getGL();
  GL2 gl2 = gl.getGL2();
 
  gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);
  gl2.glLoadIdentity();
  gl2.glTranslatef(-1f, 1f, 0);
  gl2.glScalef(1.0f, -1.0f, 0f);
   
  tex.bind(gl);
              
  gl2.glBegin(GL2.GL_QUADS);
   gl2.glTexCoord2f(0, 0);
   gl2.glVertex3f(0.0f, 0.0f, 0.0f);
  
   gl2.glTexCoord2f(1f , 0);
   gl2.glVertex3f(1f, 0.0f, 0.0f);
   
   gl2.glTexCoord2f(1f, 1f);
   gl2.glVertex3f(1f, 1f, 0.0f);
   
   gl2.glTexCoord2f(0, 1f);
   gl2.glVertex3f(0.0f, 1f, 0.0f);
  gl2.glEnd();
   
  tex.unbind(gl);
 }
 
     public static void drawQuad(GLAutoDrawable draw, float var1, float var2, float var3, float var4, Texture var5) {
        GL2 gl = draw.getGL().getGL2();
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_BLEND);
        gl.glEnable(GL2.GL_ALPHA_TEST);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glAlphaFunc(GL2.GL_EQUAL, 1.0F);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, var5.getTextureObject());
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0F, 1.0F);
        gl.glVertex3f(var1, var2, 0.0F);
        gl.glTexCoord2f(1.0F, 1.0F);
        gl.glVertex3f(var1 + (var3 - var1), var2, 0.0F);
        gl.glTexCoord2f(1.0F, 0.0F);
        gl.glVertex3f(var1 + (var3 - var1), var2 + (var4 - var2), 0.0F);
        gl.glTexCoord2f(0.0F, 0.0F);
        gl.glVertex3f(var1, var2 + (var4 - var2), 0.0F);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
        gl.glDisable(GL2.GL_ALPHA_TEST);
        gl.glDisable(GL2.GL_BLEND);
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }
 
 @Override
 public void free(GLAutoDrawable drawable){
  tex.free(drawable.getGL());
 }
 
}
