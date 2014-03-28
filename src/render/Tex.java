package render;
    import java.awt.Graphics2D;
    import java.awt.color.ColorSpace;
    import java.awt.image.BufferedImage;
    import java.awt.image.ComponentColorModel;
    import java.awt.image.DataBuffer;
    import java.awt.image.DataBufferByte;
    import java.awt.image.Raster;
    import java.awt.image.WritableRaster;
    import java.io.DataInputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.net.URL;
    import java.net.URLConnection;
    import java.nio.ByteBuffer;
     
    import javax.imageio.ImageIO;
    import javax.media.opengl.GL;
    import javax.media.opengl.GL2;
import utils.render.GLBuffer;
     
    public class Tex implements GLBuffer {
            private int id;
           
            public static Tex loadURL(GL gl1, String src) throws IOException {
                    URL url = new URL(src);
                    URLConnection urlConn = url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setUseCaches(false);
                InputStream in = new DataInputStream(urlConn.getInputStream());
                Tex image = new Tex(gl1, in);
                in.close();
                    return image;
            }
           
            public static Tex loadClassPath(GL gl1, String src) throws IOException {
                InputStream in = Tex.class.getResourceAsStream(src);
                Tex image = new Tex(gl1, in);
                in.close();
                    return image;
            }
           
            public static ComponentColorModel colorModel=
                    new ComponentColorModel (ColorSpace.getInstance(ColorSpace.CS_sRGB),
                    new int[] {8,8,8,8},
                    true,
                    false,
                    ComponentColorModel.TRANSLUCENT,
                    DataBuffer.TYPE_BYTE
            );
     
            private int width, height;
           
            public Tex(GL gl1, InputStream in) throws IOException {
                    GL2 gl = gl1.getGL2();
                   
                    int[] glID = new int[1];
                    gl.glGenTextures(1, glID, 0);
                    this.id = glID[0];
                   
                    BufferedImage bufferedImage = ImageIO.read(in);
               
                width = bufferedImage.getWidth();
                height = bufferedImage.getHeight();
                WritableRaster raster = Raster.createInterleavedRaster (DataBuffer.TYPE_BYTE, width, height, 4, null);
                BufferedImage dukeImg = new BufferedImage (colorModel, raster, false, null);
                Graphics2D g = dukeImg.createGraphics();
                g.drawImage(bufferedImage, null, null);
                DataBufferByte dukeBuf = (DataBufferByte)raster.getDataBuffer();
                byte[] dukeRGBA = dukeBuf.getData();
                ByteBuffer bb = ByteBuffer.wrap(dukeRGBA);
                bb.position(0);
                    bb.mark();
                    gl.glBindTexture(GL.GL_TEXTURE_2D, this.id);
                    gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
                    gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP);
                    gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL2.GL_CLAMP);
                    gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
                    gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
                    gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
                    gl.glTexImage2D (GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, bb);
            }
     
            @Override
            public void bind(GL gl) {
                    gl.glEnable(GL.GL_TEXTURE_2D);
                    gl.glBindTexture (GL.GL_TEXTURE_2D, this.id);
            }
     
            @Override
            public void free(GL gl) {
                    gl.glDeleteTextures(1, new int[]{this.id}, 0);         
            }
     
    }
