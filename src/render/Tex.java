package render;
 import com.jogamp.opengl.util.texture.Texture;
 import com.jogamp.opengl.util.texture.TextureData;
 import com.jogamp.opengl.util.texture.TextureIO;
 import static com.jogamp.opengl.util.texture.TextureIO.newTextureData;
 import java.io.File;
 import java.io.IOException;
 import javax.media.opengl.GL;
import javax.media.opengl.GL2;
 import javax.media.opengl.GLException;
 import javax.media.opengl.GLProfile;
 import main.Main;
     
 public class Tex {
  private Texture text;
  
  public Tex(GL gl, String s) {
   try {
    File file = new File ("/usr/games/game/res/bsett.png");
    GLProfile glp = gl.getGLProfile();
    TextureData data = newTextureData(glp, file, false, TextureIO.PNG);
    this.text = new Texture(gl, data);
    data.flush();
   } catch ( IOException | GLException ex ) {
    Main.err.add("Button . render()", ex);
   }
  }
     
  public void bind(GL gl) {
   text.enable(gl);
   text.bind(gl);
   gl.getGL2().glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
  }
  
  public void free(GL gl) {
   text.destroy(gl);
  }
  
  public void unbind(GL gl) {
   text.disable(gl);
  }
 }
