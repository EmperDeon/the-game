package render;
 import com.jogamp.common.util.IOUtil;
 import com.jogamp.opengl.util.texture.*;
 import static com.jogamp.opengl.util.texture.TextureIO.newTextureData;
 import java.io.*;
 import javax.media.opengl.*;
 import main.Main;
     
 public class Tex {
  private Texture texture;
  
  public Tex(GL gl, String s) {
   try {
    File file = new File (s);
    GLProfile glp = gl.getGLProfile();
    TextureData data = newTextureData(glp, file, true, IOUtil.getFileSuffix(file));
    this.texture = new Texture(gl, data);
    data.flush();
   } catch ( IOException | GLException ex ) {
    Main.err.add("Tex . init()", ex);
   }
  }
     
  public void bind(GL gl) {
   texture.enable(gl);
   texture.bind(gl);
   //gl.getGL2().glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
  }
  
  public void free(GL gl) {
   texture.destroy(gl);
  }
  
  public void unbind(GL gl) {
   texture.disable(gl);
  }
 }
