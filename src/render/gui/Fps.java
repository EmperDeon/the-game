package render.gui;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.media.opengl.GLAutoDrawable;

public class Fps {
 private final TextRenderer font;
 private String sec="";
 private int t;
 private Integer fps;
 public Fps(TextRenderer font){
  this.font = font;
 }
 public void render(GLAutoDrawable draw){
  Date date=new Date();
  if(sec.equals(new SimpleDateFormat("ss").format(date))) 
   t+=1;
  else{
   sec = new SimpleDateFormat("ss").format(date);
   fps = t;
   t=0;
  }
  font.beginRendering(draw.getWidth(), draw.getHeight());
  font.setColor(1.0f, 0.2f, 0.2f, 0.8f);
  font.draw(fps.toString() , 0, 0);
  font.endRendering();
 }
}
