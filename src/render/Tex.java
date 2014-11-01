package render;

import com.jogamp.common.util.IOUtil;
import com.jogamp.opengl.GLExtensions;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.spi.DDSImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import javax.media.nativewindow.NativeWindowFactory;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLES2;
import javax.media.opengl.GLException;
import javax.media.opengl.GLProfile;
import javax.media.opengl.glu.GLU;
import main.Main;

public class Tex {

 private final GL2 gl;
 private File file;
 private int target;
 private int texID;
 private int texWidth;
 private int texHeight;
 private int imgWidth;
 private int imgHeight;
 private boolean mustFlipVertically;
 public TextureCoords coords;

 public Tex ( String s ) {
  gl = main.Main.rend.gl;
  file = new File(s);
  GLProfile glp;
  try {
   glp = gl.getGLProfile();
   TextureData data = TextureIO.newTextureData(glp , file , true , IOUtil.
                                               getFileSuffix(file));
   update(data);
   data.flush();
  } catch ( IOException | GLException ex ) {
   Main.LOG.addE("Tex . init()" , ex);
  }
 }

 public void bind () {
  try {
   if ( !gl.isGLcore() && GLES2.GL_TEXTURE_EXTERNAL_OES != target ) {
    gl.glEnable(target);
   }
   validateTexID();
   gl.glBindTexture(target , texID);
  } catch ( NullPointerException | GLException ex ) {
   Main.LOG.addE("Tex . bind()" , ex);
  }
 }

 public void free () {
  if ( 0 != texID ) {
   gl.glDeleteTextures(1 , new int[]{texID} , 0);
   texID = 0;
  }
 }

 public void unbind () {
  if ( !gl.isGLcore() && GLES2.GL_TEXTURE_EXTERNAL_OES != target ) {
   gl.glDisable(target);
  }
 }

 private boolean validateTexID () {
  if ( 0 == texID ) {
   if ( null != gl ) {
    int[] tmp = new int[1];
    gl.glGenTextures(1 , tmp , 0);
    texID = tmp[0];
   } else {
    Main.LOG.addE("Tex . bind()" , new GLException(
                  "No GL context given, can't create texture ID"));
   }
  }
  return 0 != texID;
 }

 private boolean isPowerOfTwo ( int val ) {
  return ( ( val & ( val - 1 ) ) == 0 );
 }

 private boolean haveNPOT () {
  return gl.isNPOTTextureAvailable();// !disableNPOT &&
 }

 private static int nextPowerOfTwo ( int val ) {
  int ret = 1;
  while ( ret < val ) {
   ret <<= 1;
  }
  return ret;
 }

 private boolean preferTexRect () {
  if ( NativeWindowFactory.TYPE_MACOSX.equals(NativeWindowFactory.
          getNativeWindowType(false)) ) {
   String vendor = gl.glGetString(GL.GL_VENDOR);
   if ( vendor != null && vendor.startsWith("ATI") ) {
    return true;
   }
  }
  return false;
 }

 private boolean haveTexRect () {
  return ( TextureIO.isTexRectEnabled() && // !disableTexRect &&
          gl.isExtensionAvailable(GLExtensions.ARB_texture_rectangle) );
 }

 private void setImageSize ( int width , int height , int target ) {
  imgWidth = width;
  imgHeight = height;
  if ( target == GL2.GL_TEXTURE_RECTANGLE_ARB ) {
   if ( mustFlipVertically ) {
    coords = new TextureCoords(0 , imgHeight , imgWidth , 0);
   } else {
    coords = new TextureCoords(0 , 0 , imgWidth , imgHeight);
   }
  } else {
   if ( mustFlipVertically ) {
    coords = new TextureCoords(0 , // l
                               ( float ) imgHeight / ( float ) texHeight , // b
                               ( float ) imgWidth / ( float ) texWidth , // r
                               0 // t
    );
   } else {
    coords = new TextureCoords(0 , // l
                               0 , // b
                               ( float ) imgWidth / ( float ) texWidth , // r
                               ( float ) imgHeight / ( float ) texHeight // t
    );
   }
  }
 }

 private void checkCompressedTextureExtensions ( TextureData data ) {
  if ( data.isDataCompressed() ) {
   switch ( data.getInternalFormat() ) {
    case GL.GL_COMPRESSED_RGB_S3TC_DXT1_EXT:
    case GL.GL_COMPRESSED_RGBA_S3TC_DXT1_EXT:
    case GL.GL_COMPRESSED_RGBA_S3TC_DXT3_EXT:
    case GL.GL_COMPRESSED_RGBA_S3TC_DXT5_EXT:
     if ( !gl.isExtensionAvailable(GLExtensions.EXT_texture_compression_s3tc)
          && !gl.isExtensionAvailable(GLExtensions.NV_texture_compression_vtc) ) {
      Main.LOG.addE("Tex . checkCompressedTextureExtensions()" ,
                    new GLException(
                            "DXTn compressed textures not supported by this graphics card"));
     }
     break;
    default:
     break;
   }
  }
 }

 private void update ( TextureData data ) {
  validateTexID();

  imgWidth = data.getWidth();
  imgHeight = data.getHeight();
  //aspectRatio = (float) imgWidth / (float) imgHeight;
  mustFlipVertically = data.getMustFlipVertically();

  int texTarget = 0;
  int texParamTarget;

  boolean haveAutoMipmapGeneration
          = ( gl.isExtensionAvailable(GLExtensions.VERSION_1_4)
              || gl.isExtensionAvailable(GLExtensions.SGIS_generate_mipmap) );

  data.setHaveEXTABGR(gl.isExtensionAvailable(GLExtensions.EXT_abgr));
  data.setHaveGL12(gl.isExtensionAvailable(GLExtensions.VERSION_1_2));

  boolean isPOT = isPowerOfTwo(imgWidth) && isPowerOfTwo(imgHeight);

  if ( !isPOT && !haveNPOT() ) {
   haveAutoMipmapGeneration = false;
  }

  boolean expandingCompressedTexture = false;
  boolean done = false;
  if ( data.getMipmap() && !haveAutoMipmapGeneration ) {
   imgWidth = nextPowerOfTwo(imgWidth);
   imgHeight = nextPowerOfTwo(imgHeight);
   texWidth = imgWidth;
   texHeight = imgHeight;
   texTarget = GL.GL_TEXTURE_2D;
   done = true;
  }

  if ( !done && preferTexRect() && !isPOT
       && haveTexRect() && !data.isDataCompressed() && !gl.isGL3() && !gl.
          isGLES() ) {

   texWidth = imgWidth;
   texHeight = imgHeight;
   texTarget = GL2.GL_TEXTURE_RECTANGLE_ARB;
   done = true;
  }

  if ( !done && ( isPOT || haveNPOT() ) ) {
   texWidth = imgWidth;
   texHeight = imgHeight;
   texTarget = GL.GL_TEXTURE_2D;
   done = true;
  }

  if ( !done && haveTexRect() && !data.isDataCompressed() && !gl.isGL3() && !gl.
          isGLES() ) {
   texWidth = imgWidth;
   texHeight = imgHeight;
   texTarget = GL2.GL_TEXTURE_RECTANGLE_ARB;
   done = true;
  }

  if ( !done ) {
   if ( data.isDataCompressed() ) {
    if ( data.getMipmapData() != null ) {
     Main.LOG.addE("Tex . update() " , new GLException(
                   "Mipmapped non-power-of-two compressed textures only supported on OpenGL 2.0 hard(GL_ARB_texture_non_power_of_two)"));
    }

    expandingCompressedTexture = true;
   }

   if ( data.getBorder() != 0 ) {
    Main.LOG.addE("Tex . update() " , new RuntimeException(
                  "Scaling up a non-power-of-two texture which has a border won't work"));
   }
   texWidth = nextPowerOfTwo(imgWidth);
   texHeight = nextPowerOfTwo(imgHeight);
   texTarget = GL.GL_TEXTURE_2D;
  }

  texParamTarget = texTarget;
  setImageSize(imgWidth , imgHeight , texTarget);

  gl.glBindTexture(texTarget , texID);

  if ( data.getMipmap() && !haveAutoMipmapGeneration ) {
   int[] align = new int[1];
   gl.glGetIntegerv(GL.GL_UNPACK_ALIGNMENT , align , 0); // save alignment
   gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT , data.getAlignment());

   if ( data.isDataCompressed() ) {
    Main.LOG.addE("Tex . update() " , new GLException(
                  "May not request mipmap generation for compressed textures"));
   }

   try {
    // FIXME: may need check for GLUnsupportedException
    GLU glu = GLU.createGLU(gl);
    glu.gluBuild2DMipmaps(texTarget , data.getInternalFormat() ,
                          data.getWidth() , data.getHeight() ,
                          data.getPixelFormat() , data.getPixelType() , data.
                          getBuffer());
   } finally {
    gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT , align[0]); // restore alignment
   }
  } else {
   checkCompressedTextureExtensions(data);
   Buffer[] mipmapData = data.getMipmapData();
   if ( mipmapData != null ) {
    int width = texWidth;
    int height = texHeight;
    for ( int i = 0 ; i < mipmapData.length ; i++ ) {
     if ( data.isDataCompressed() ) {
      // Need to use glCompressedTexImage2D directly to allocate and fill this image
      // Avoid spurious memory allocation when possible
      gl.glCompressedTexImage2D(texTarget , i , data.getInternalFormat() ,
                                width , height , data.getBorder() ,
                                mipmapData[i].remaining() , mipmapData[i]);
     } else {
      // Allocate texture image at this level
      gl.glTexImage2D(texTarget , i , data.getInternalFormat() ,
                      width , height , data.getBorder() ,
                      data.getPixelFormat() , data.getPixelType() , null);
      updateSubImageImpl(texTarget , i , data);
     }

     width = Math.max(width / 2 , 1);
     height = Math.max(height / 2 , 1);
    }
   } else {
    if ( data.isDataCompressed() ) {
     if ( !expandingCompressedTexture ) {
      gl.glCompressedTexImage2D(texTarget , 0 , data.getInternalFormat() ,
                                texWidth , texHeight , data.getBorder() ,
                                data.getBuffer().capacity() , data.getBuffer());
     } else {
      ByteBuffer buf = DDSImage.allocateBlankBuffer(texWidth ,
                                                    texHeight ,
                                                    data.getInternalFormat());
      gl.glCompressedTexImage2D(texTarget , 0 , data.getInternalFormat() ,
                                texWidth , texHeight , data.getBorder() ,
                                buf.capacity() , buf);
      updateSubImageImpl(texTarget , 0 , data);
     }
    } else {
     if ( data.getMipmap() && haveAutoMipmapGeneration && gl.isGL2ES1() ) {
      gl.
              glTexParameteri(texParamTarget , GL2ES1.GL_GENERATE_MIPMAP ,
                              GL.GL_TRUE);
      // usingAutoMipmapGeneration = true;
     }

     gl.glTexImage2D(texTarget , 0 , data.getInternalFormat() ,
                     texWidth , texHeight , data.getBorder() ,
                     data.getPixelFormat() , data.getPixelType() , null);
     updateSubImageImpl(texTarget , 0 , data);
    }
   }
  }

  int minFilter = ( data.getMipmap() ? GL.GL_LINEAR_MIPMAP_LINEAR : GL.GL_LINEAR );
  int magFilter = GL.GL_LINEAR;
  int wrapMode = ( gl.isExtensionAvailable(GLExtensions.VERSION_1_2) || !gl.
          isGL2() ) ? GL.GL_CLAMP_TO_EDGE : GL2.GL_CLAMP;

  if ( texTarget != GL2.GL_TEXTURE_RECTANGLE_ARB ) {
   gl.glTexParameteri(texParamTarget , GL.GL_TEXTURE_MIN_FILTER , minFilter);
   gl.glTexParameteri(texParamTarget , GL.GL_TEXTURE_MAG_FILTER , magFilter);
   gl.glTexParameteri(texParamTarget , GL.GL_TEXTURE_WRAP_S , wrapMode);
   gl.glTexParameteri(texParamTarget , GL.GL_TEXTURE_WRAP_T , wrapMode);
   if ( this.target == GL2.GL_TEXTURE_CUBE_MAP ) {
    gl.glTexParameteri(texParamTarget , GL2.GL_TEXTURE_WRAP_R , wrapMode);
   }
  }

  if ( ( this.target == 0 )
       || ( this.target == GL.GL_TEXTURE_2D )
       || ( this.target == GL2.GL_TEXTURE_RECTANGLE_ARB ) ) {
   this.target = texTarget;
  }

  //estimatedMemorySize = data.getEstimatedMemorySize();
 }

 private void updateSubImageImpl ( int newTarget , int mipmapLevel ,
                                   TextureData data ) throws GLException {

  data.setHaveEXTABGR(gl.isExtensionAvailable(GLExtensions.EXT_abgr));
  data.setHaveGL12(gl.isExtensionAvailable(GLExtensions.VERSION_1_2));

  Buffer buffer = data.getBuffer();
  if ( buffer == null && data.getMipmapData() == null ) {
   return;
  }

  int rowlen = data.getRowLength();
  int dataWidth = data.getWidth();
  int dataHeight = data.getHeight();
  if ( data.getMipmapData() != null ) {
   for ( int i = 0 ; i < mipmapLevel ; i++ ) {
    imgWidth = Math.max(imgWidth / 2 , 1);
    imgHeight = Math.max(imgHeight / 2 , 1);
    dataWidth = Math.max(dataWidth / 2 , 1);
    dataHeight = Math.max(dataHeight / 2 , 1);
   }
   rowlen = 0;
   buffer = data.getMipmapData()[mipmapLevel];
  }

  if ( imgWidth > dataWidth ) {
   imgWidth = dataWidth;
  }
  if ( imgHeight > dataHeight ) {
   imgHeight = dataHeight;
  }
  if ( imgWidth > texWidth ) {
   imgWidth = texWidth;
  }
  if ( imgHeight > texHeight ) {
   imgHeight = texHeight;
  }

  checkCompressedTextureExtensions(data);

  if ( data.isDataCompressed() ) {
   gl.glCompressedTexSubImage2D(newTarget , mipmapLevel ,
                                0 , 0 , imgWidth , imgHeight ,
                                data.getInternalFormat() ,
                                buffer.remaining() , buffer);
  } else {
   int[] align = {0};
   int[] rowLength = {0};
   int[] skipRows = {0};
   int[] skipPixels = {0};
   gl.glGetIntegerv(GL.GL_UNPACK_ALIGNMENT , align , 0);
   if ( gl.isGL2GL3() ) {
    gl.glGetIntegerv(GL2GL3.GL_UNPACK_ROW_LENGTH , rowLength , 0);
    gl.glGetIntegerv(GL2GL3.GL_UNPACK_SKIP_ROWS , skipRows , 0);
    gl.glGetIntegerv(GL2GL3.GL_UNPACK_SKIP_PIXELS , skipPixels , 0);
   }
   gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT , data.getAlignment());
   if ( gl.isGL2GL3() ) {
    gl.glPixelStorei(GL2GL3.GL_UNPACK_ROW_LENGTH , rowlen);
    gl.glPixelStorei(GL2GL3.GL_UNPACK_SKIP_ROWS , 0);
    gl.glPixelStorei(GL2GL3.GL_UNPACK_SKIP_PIXELS , 0);
   }

   gl.glTexSubImage2D(newTarget , mipmapLevel ,
                      0 , 0 , imgWidth , imgHeight ,
                      data.getPixelFormat() , data.getPixelType() ,
                      buffer);
   gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT , align[0]);
   if ( gl.isGL2GL3() ) {
    gl.glPixelStorei(GL2GL3.GL_UNPACK_ROW_LENGTH , rowLength[0]);
    gl.glPixelStorei(GL2GL3.GL_UNPACK_SKIP_ROWS , skipRows[0]);
    gl.glPixelStorei(GL2GL3.GL_UNPACK_SKIP_PIXELS , skipPixels[0]);
   }
  }
 }
}
