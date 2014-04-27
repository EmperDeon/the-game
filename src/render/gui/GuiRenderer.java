package render.gui;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import render.Renderer;

public class GuiRenderer {
 private final Renderer rend;
 public GuiRenderer(Renderer rend){

   this.rend = rend;

 }
    
 public void init(GLAutoDrawable drawable) {  

    GL2 gl  = drawable.getGL().getGL2();
   // glu = new GLU();

    float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };
    float red[] = { 0.8f, 0.1f, 0.0f, 0.7f };
    float green[] = { 0.0f, 0.8f, 0.2f, 0.7f };
    float blue[] = { 0.2f, 0.2f, 1.0f, 0.7f };

    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0);
    gl.glEnable(GL2.GL_CULL_FACE);
    gl.glEnable(GL2.GL_LIGHTING);
    gl.glEnable(GL2.GL_LIGHT0);
    gl.glEnable(GL2.GL_DEPTH_TEST);
    gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
    
    gl.glEnable(GL2.GL_NORMALIZE);
   }
 
 public void render(GLAutoDrawable drawable){
   /*
   if(b) gl.glPopMatrix();
   //if(b) gl.glTranslatef(player.x, player.y, player.z);
   if(b){ gl.glTranslatef( 0, plcoord.x, 0); if (plcoord.x!=0)plcoord.x=0;}
   if(b){ gl.glTranslatef( plcoord.y, 0, 0); if (plcoord.y!=0)plcoord.y=0;}
   if(b){ gl.glTranslatef( 0, plcoord.z, 0); if (plcoord.z!=0)plcoord.z=0;}
   
   if(b){ gl.glRotatef(cmcoord.x, 0, 0, 1); if (cmcoord.x!=0)cmcoord.x=0;}
   if(b){ gl.glRotatef(cmcoord.y, 1, 0, 0); if (cmcoord.y!=0)cmcoord.y=0;}
   if(b){ gl.glPushMatrix();b=true; }

   for (int x = -50; x <50; x++)
    { 
    gl.glBegin(GL2.GL_QUADS);
    for (int y = -50; y <50; y++)
      {
       
        gl.glVertex3f(x,y,0);
        gl.glColor3f(1, 0, 1);                         
        gl.glVertex3f(x+1,y,0);
        gl.glVertex3f(x+1,y+1,0);
        gl.glVertex3f(x,y+1,0);
      }
    gl.glEnd();
    
}*/
    
  rend.render();
 }
}
