package render.gui.entitys.types;

import java.util.Objects;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import render.gui.entitys.Type;
import render.tex.Tex;
import render.tex.TexCoords;
import utils.Action;
import utils.CoordConv;
import utils.vec.Vec4;

public class Button extends Entity{
 private final String s;
 private final Tex tex ;
 private final Vec4<Float> pos;
 private final Action act;
 private final CoordConv CC = main.Main.CC;
 public Button (GLAutoDrawable draw, Vec4<Integer> pos , String s , Action act, Tex tex) {
  super(Type.Button , pos );
  this.s = s;
  this.tex = tex;
  this.act = act;
  this.pos = CC.conv4(pos);
 }
 
 @Override
 public void action(){
  act.action();
 }
 
 @Override
 public void render(GLAutoDrawable drawable){
  GL gl = drawable.getGL();
  GL2 gl2 = gl.getGL2();
  
  gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);
   
  tex.bind(gl);
  TexCoords crd = tex.getCoords();
  
  gl2.glBegin(GL2.GL_QUADS);
  
   gl2.glTexCoord2f(crd.left(), crd.top());
   gl2.glVertex3f(  pos.gX1() , pos.gY1(), 0.0f);
                 
   gl2.glTexCoord2f(crd.right(), crd.top());
   gl2.glVertex3f(  pos.gX2()  , pos.gY1(), 0.0f);
                 
   gl2.glTexCoord2f(crd.right(), crd.bottom());
   gl2.glVertex3f(  pos.gX2()  , pos.gY2(), 0.0f);
                
   gl2.glTexCoord2f(crd.left(), crd.bottom());
   gl2.glVertex3f(  pos.gX1() , pos.gY2(), 0.0f);
   
  gl2.glEnd();
   
  tex.unbind(gl); 
 }
 
     public void drawQuad(GLAutoDrawable draw, float var1, float var2, float var3, float var4) {
        GL2 gl = draw.getGL().getGL2();
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_BLEND);
        gl.glEnable(GL2.GL_ALPHA_TEST);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glAlphaFunc(GL2.GL_EQUAL, 1.0F);
        tex.bind(gl);
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

 @Override public void free(GL gl){tex.free(gl);}
 @Override public int hashCode(){
  return s.hashCode() + tex.hashCode() + pos.hashCode();
 }
 @Override public boolean equals ( Object obj ) {
  if ( obj == null ) {
   return false;
  }
  if ( getClass() != obj.getClass() ) {
   return false;
  }
  final Button other = ( Button ) obj;
  if ( !Objects.equals(this.s , other.s) ) {
   return false;
  }
  if ( !Objects.equals(this.tex , other.tex) ) {
   return false;
  }
  if ( !Objects.equals(this.pos , other.pos) ) {
   return false;
  }
  if ( !Objects.equals(this.act , other.act) ) {
   return false;
  }
  return true;
 }
}
