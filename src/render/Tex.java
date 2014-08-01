package render;
 import com.jogamp.common.util.IOUtil;
 import com.jogamp.opengl.GLExtensions;
 import com.jogamp.opengl.util.texture.*;
 import static com.jogamp.opengl.util.texture.TextureIO.newTextureData;
 import com.jogamp.opengl.util.texture.spi.DDSImage;
 import java.io.*;
 import java.nio.Buffer;
 import java.nio.ByteBuffer;
 import javax.media.nativewindow.NativeWindowFactory;
 import javax.media.opengl.*;
 import javax.media.opengl.glu.GLU;
 import jogamp.opengl.Debug;
 import main.Main;
     
 public class Tex {
  
  private Texture texture;
  private int target;
  private int texID = 0;
  private int texWidth;
  private int texHeight;
  private int imgWidth;
  private int imgHeight;
  private float aspectRatio;
  private boolean mustFlipVertically;
  private boolean usingAutoMipmapGeneration;
  private static final boolean DEBUG = Debug.debug("Texture");
  private static final boolean VERBOSE = Debug.verbose();
  private int estimatedMemorySize;
  private TextureCoords coords;
  
  public Tex(GL gl, String s) {
   File file = new File (s);
   GLProfile glp;
   TextureData data ;
   try {
    glp = gl.getGLProfile();
    data = newTextureData(glp, file, true, IOUtil.getFileSuffix(file));

    update(gl, data, 0);
    
    data.flush();
   } catch (  IOException | GLException ex ) {
    Main.err.addE("Tex . init()", ex);
   }
  }
     
  public void bind(GL gl) {
   try{
    texture.enable(gl);
    texture.bind(gl);
    //gl.getGL2().glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
    } catch ( NullPointerException | GLException ex ) {
     Main.err.addE("Tex . bind()", ex);
    }
   
   }
  
  public void free(GL gl) {
   texture.destroy(gl);
  }
  
  public void unbind(GL gl) {
   texture.disable(gl);
  }
  
  private boolean validateTexID(GL gl) {
   if( 0 == texID ) {
    if( null != gl ) {
     int[] tmp = new int[1];
     gl.glGenTextures(1, tmp, 0);
     texID = tmp[0];
     if ( 0 == texID ) {
      Main.err.addE("Tex . bind()", 
                   new GLException("Create texture ID invalid: texID "+texID+", glerr 0x"
                                   +Integer.toHexString(gl.glGetError())));
      }
     } else 
      Main.err.addE("Tex . bind()", 
                   new GLException("No GL context given, can't create texture ID"));      
     }
     return 0 != texID;
    }
  
  private static boolean isPowerOfTwo(int val) {
   return ((val & (val - 1)) == 0);
  }
  
  private static boolean haveNPOT(GL gl) {
   return gl.isNPOTTextureAvailable();// !disableNPOT &&
  }
  
  private static int nextPowerOfTwo(int val) {
   int ret = 1;
   while (ret < val) {
       ret <<= 1;
   }
   return ret;
  }
  
  private static boolean preferTexRect(GL gl) {
   // Prefer GL_ARB_texture_rectangle on ATI hardware on Mac OS X
   // due to software fallbacks
   // Prefer GL_ARB_texture_rectangle on ATI hardware on Mac OS X
   if (NativeWindowFactory.TYPE_MACOSX.equals(NativeWindowFactory.getNativeWindowType(false))) {
    String vendor = gl.glGetString(GL.GL_VENDOR);
    if (vendor != null && vendor.startsWith("ATI")) {
     return true;
    }
   } 
   return false;
  }
  
  private static boolean haveTexRect(GL gl) {
   return (TextureIO.isTexRectEnabled() && // !disableTexRect &&
    gl.isExtensionAvailable(GLExtensions.ARB_texture_rectangle));
  }
  
  private void setImageSize(int width, int height, int target) {
   imgWidth = width;
   imgHeight = height;
   if (target == GL2.GL_TEXTURE_RECTANGLE_ARB) {
    if (mustFlipVertically) {
     coords = new TextureCoords(0, imgHeight, imgWidth, 0);
    } else {
     coords = new TextureCoords(0, 0, imgWidth, imgHeight);
    }
   } else {
    if (mustFlipVertically) {
     coords = new TextureCoords(0,                                      // l
                                (float) imgHeight / (float) texHeight,  // b
                                (float) imgWidth / (float) texWidth,    // r
                                0                                       // t
                               );
     } else {
      coords = new TextureCoords(0,                                      // l
                                 0,                                      // b
                                 (float) imgWidth / (float) texWidth,    // r
                                 (float) imgHeight / (float) texHeight   // t
                                 );
     }
    }
   }
   
   private void checkCompressedTextureExtensions(GL gl, TextureData data) {
    if (data.isDataCompressed()) {
     switch (data.getInternalFormat()) {
      case GL.GL_COMPRESSED_RGB_S3TC_DXT1_EXT:
      case GL.GL_COMPRESSED_RGBA_S3TC_DXT1_EXT:
      case GL.GL_COMPRESSED_RGBA_S3TC_DXT3_EXT:
      case GL.GL_COMPRESSED_RGBA_S3TC_DXT5_EXT:
      if (!gl.isExtensionAvailable(GLExtensions.EXT_texture_compression_s3tc) &&
       !gl.isExtensionAvailable(GLExtensions.NV_texture_compression_vtc)) {
       Main.err.addE("Tex . checkCompressedTextureExtensions()", 
                    new GLException("DXTn compressed textures not supported by this graphics card"));
      }break;
      default:break;
     }
    }
   }
   
  private void update(GL gl, TextureData data, int targetOverride){
   validateTexID(gl);

   imgWidth = data.getWidth();
   imgHeight = data.getHeight();
   aspectRatio = (float) imgWidth / (float) imgHeight;
   mustFlipVertically = data.getMustFlipVertically();

   int texTarget = 0;
   int texParamTarget = this.target;

   // See whether we have automatic mipmap generation support
   boolean haveAutoMipmapGeneration =
       (gl.isExtensionAvailable(GLExtensions.VERSION_1_4) ||
        gl.isExtensionAvailable(GLExtensions.SGIS_generate_mipmap));

   // Indicate to the TextureData what functionality is available
   data.setHaveEXTABGR(gl.isExtensionAvailable(GLExtensions.EXT_abgr));
   data.setHaveGL12(gl.isExtensionAvailable(GLExtensions.VERSION_1_2));

   // Indicates whether both width and height are power of two
   boolean isPOT = isPowerOfTwo(imgWidth) && isPowerOfTwo(imgHeight);

   // Note that automatic mipmap generation doesn't work for
   // GL_ARB_texture_rectangle
   if (!isPOT && !haveNPOT(gl)) {
    haveAutoMipmapGeneration = false;
   }

   boolean expandingCompressedTexture = false;
   boolean done = false;
   if (data.getMipmap() && !haveAutoMipmapGeneration) {
    imgWidth = nextPowerOfTwo(imgWidth);
    imgHeight = nextPowerOfTwo(imgHeight);
    texWidth = imgWidth;
    texHeight = imgHeight;
    texTarget = GL.GL_TEXTURE_2D;
    done = true;
   }

   if (!done && preferTexRect(gl) && !isPOT && 
    haveTexRect(gl) && !data.isDataCompressed() && !gl.isGL3() && !gl.isGLES()) {
    // GL_ARB_texture_rectangle does not work for compressed textures
    if (DEBUG) {
      System.err.println("Using GL_ARB_texture_rectangle preferentially on this hardware");
    }
    texWidth = imgWidth;
    texHeight = imgHeight;
    texTarget = GL2.GL_TEXTURE_RECTANGLE_ARB;
    done = true;
   }

   if (!done && (isPOT || haveNPOT(gl))) {
       if (DEBUG) {
           if (isPOT) {
               System.err.println("Power-of-two texture");
           } else {
               System.err.println("Using GL_ARB_texture_non_power_of_two");
           }
       }

       texWidth = imgWidth;
       texHeight = imgHeight;
       texTarget = GL.GL_TEXTURE_2D;
       done = true;
   }

   if (!done && haveTexRect(gl) && !data.isDataCompressed() && !gl.isGL3() && !gl.isGLES()) {
       // GL_ARB_texture_rectangle does not work for compressed textures
       if (DEBUG) {
           System.err.println("Using GL_ARB_texture_rectangle");
       }

       texWidth = imgWidth;
       texHeight = imgHeight;
       texTarget = GL2.GL_TEXTURE_RECTANGLE_ARB;
       done = true;
   }

   if (!done) {
       // If we receive non-power-of-two compressed texture data and
       // don't have true hardware support for compressed textures, we
       // can fake this support by producing an empty "compressed"
       // texture image, using glCompressedTexImage2D with that to
       // allocate the texture, and glCompressedTexSubImage2D with the
       // incoming data.
       if (data.isDataCompressed()) {
           if (data.getMipmapData() != null) {

               // We don't currently support expanding of compressed,
               // mipmapped non-power-of-two textures to the nearest power
               // of two; the obvious port of the non-mipmapped code didn't
               // work
               throw new GLException("Mipmapped non-power-of-two compressed textures only supported on OpenGL 2.0 hard(GL_ARB_texture_non_power_of_two)");
           }

           expandingCompressedTexture = true;
       }

       if (DEBUG) {
           System.err.println("Expanding texture to power-of-two dimensions");
       }

       if (data.getBorder() != 0) {
           throw new RuntimeException("Scaling up a non-power-of-two texture which has a border won't work");
       }
       texWidth = nextPowerOfTwo(imgWidth);
       texHeight = nextPowerOfTwo(imgHeight);
       texTarget = GL.GL_TEXTURE_2D;
   }

   texParamTarget = texTarget;
   setImageSize(imgWidth, imgHeight, texTarget);

   if (targetOverride != 0) {
       // Allow user to override auto detection and skip bind step (for
       // cubemap construction)
       texTarget = targetOverride;
       if (this.target == 0) {
           throw new GLException("Override of target failed; no target specified yet");
       }
       texParamTarget = this.target;
       gl.glBindTexture(texParamTarget, texID);
   } else {
       gl.glBindTexture(texTarget, texID);
   }

   if (data.getMipmap() && !haveAutoMipmapGeneration) {
       int[] align = new int[1];
       gl.glGetIntegerv(GL.GL_UNPACK_ALIGNMENT, align, 0); // save alignment
       gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, data.getAlignment());

       if (data.isDataCompressed()) {
           throw new GLException("May not request mipmap generation for compressed textures");
       }

       try {
           // FIXME: may need check for GLUnsupportedException
           GLU glu = GLU.createGLU(gl);
           glu.gluBuild2DMipmaps(texTarget, data.getInternalFormat(),
                                 data.getWidth(), data.getHeight(),
                                 data.getPixelFormat(), data.getPixelType(), data.getBuffer());
       } finally {
           gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, align[0]); // restore alignment
       }
   } else {
       checkCompressedTextureExtensions(gl, data);
       Buffer[] mipmapData = data.getMipmapData();
       if (mipmapData != null) {
           int width = texWidth;
           int height = texHeight;
           for (int i = 0; i < mipmapData.length; i++) {
               if (data.isDataCompressed()) {
                   // Need to use glCompressedTexImage2D directly to allocate and fill this image
                   // Avoid spurious memory allocation when possible
                   gl.glCompressedTexImage2D(texTarget, i, data.getInternalFormat(),
                                             width, height, data.getBorder(),
                                             mipmapData[i].remaining(), mipmapData[i]);
               } else {
                   // Allocate texture image at this level
                   gl.glTexImage2D(texTarget, i, data.getInternalFormat(),
                                   width, height, data.getBorder(),
                                   data.getPixelFormat(), data.getPixelType(), null);
                   updateSubImageImpl(gl, data, texTarget, i, 0, 0, 0, 0, data.getWidth(), data.getHeight());
               }

               width = Math.max(width / 2, 1);
               height = Math.max(height / 2, 1);
           }
       } else {
           if (data.isDataCompressed()) {
               if (!expandingCompressedTexture) {
                   // Need to use glCompressedTexImage2D directly to allocate and fill this image
                   // Avoid spurious memory allocation when possible
                   gl.glCompressedTexImage2D(texTarget, 0, data.getInternalFormat(),
                                             texWidth, texHeight, data.getBorder(),
                                             data.getBuffer().capacity(), data.getBuffer());
               } else {
                   ByteBuffer buf = DDSImage.allocateBlankBuffer(texWidth,
                                                                 texHeight,
                                                                 data.getInternalFormat());
                   gl.glCompressedTexImage2D(texTarget, 0, data.getInternalFormat(),
                                             texWidth, texHeight, data.getBorder(),
                                             buf.capacity(), buf);
                   updateSubImageImpl(gl, data, texTarget, 0, 0, 0, 0, 0, data.getWidth(), data.getHeight());
               }
           } else {
               if (data.getMipmap() && haveAutoMipmapGeneration && gl.isGL2ES1()) {
                   // For now, only use hardware mipmapping for uncompressed 2D
                   // textures where the user hasn't explicitly specified
                   // mipmap data; don't know about interactions between
                   // GL_GENERATE_MIPMAP and glCompressedTexImage2D
                   gl.glTexParameteri(texParamTarget, GL2ES1.GL_GENERATE_MIPMAP, GL.GL_TRUE);
                   usingAutoMipmapGeneration = true;
               }

               gl.glTexImage2D(texTarget, 0, data.getInternalFormat(),
                               texWidth, texHeight, data.getBorder(),
                               data.getPixelFormat(), data.getPixelType(), null);
               updateSubImageImpl(gl, data, texTarget, 0, 0, 0, 0, 0, data.getWidth(), data.getHeight());
           }
       }
   }

   int minFilter = (data.getMipmap() ? GL.GL_LINEAR_MIPMAP_LINEAR : GL.GL_LINEAR);
   int magFilter = GL.GL_LINEAR;
   int wrapMode = (gl.isExtensionAvailable(GLExtensions.VERSION_1_2)||!gl.isGL2()) ? GL.GL_CLAMP_TO_EDGE : GL2.GL_CLAMP;

   // REMIND: figure out what to do for GL_TEXTURE_RECTANGLE_ARB
   if (texTarget != GL2.GL_TEXTURE_RECTANGLE_ARB) {
       gl.glTexParameteri(texParamTarget, GL.GL_TEXTURE_MIN_FILTER, minFilter);
       gl.glTexParameteri(texParamTarget, GL.GL_TEXTURE_MAG_FILTER, magFilter);
       gl.glTexParameteri(texParamTarget, GL.GL_TEXTURE_WRAP_S, wrapMode);
       gl.glTexParameteri(texParamTarget, GL.GL_TEXTURE_WRAP_T, wrapMode);
       if (this.target == GL2.GL_TEXTURE_CUBE_MAP) {
           gl.glTexParameteri(texParamTarget, GL2.GL_TEXTURE_WRAP_R, wrapMode);
       }
   }

   // Don't overwrite target if we're loading e.g. faces of a cube
   // map
   if ((this.target == 0) ||
       (this.target == GL.GL_TEXTURE_2D) ||
       (this.target == GL2.GL_TEXTURE_RECTANGLE_ARB)) {
       this.target = texTarget;
   }

   // This estimate will be wrong for cube maps
   estimatedMemorySize = data.getEstimatedMemorySize();
  }
  
   private void updateSubImageImpl(GL gl, TextureData data, int newTarget, int mipmapLevel,
                                    int dstx, int dsty,
                                    int srcx, int srcy, int width, int height) throws GLException {
        data.setHaveEXTABGR(gl.isExtensionAvailable(GLExtensions.EXT_abgr));
        data.setHaveGL12(gl.isExtensionAvailable(GLExtensions.VERSION_1_2));

        Buffer buffer = data.getBuffer();
        if (buffer == null && data.getMipmapData() == null) {
            // Assume user just wanted to get the Texture object allocated
            return;
        }

        int rowlen = data.getRowLength();
        int dataWidth = data.getWidth();
        int dataHeight = data.getHeight();
        if (data.getMipmapData() != null) {
            // Compute the width, height and row length at the specified mipmap level
            // Note we do not support specification of the row length for
            // mipmapped textures at this point
            for (int i = 0; i < mipmapLevel; i++) {
                width = Math.max(width / 2, 1);
                height = Math.max(height / 2, 1);

                dataWidth = Math.max(dataWidth / 2, 1);
                dataHeight = Math.max(dataHeight / 2, 1);
            }
            rowlen = 0;
            buffer = data.getMipmapData()[mipmapLevel];
        }

        // Clip incoming rectangles to what is available both on this
        // texture and in the incoming TextureData
        if (srcx < 0) {
            width += srcx;
            srcx = 0;
        }
        if (srcy < 0) {
            height += srcy;
            srcy = 0;
        }
        // NOTE: not sure whether the following two are the correct thing to do
        if (dstx < 0) {
            width += dstx;
            dstx = 0;
        }
        if (dsty < 0) {
            height += dsty;
            dsty = 0;
        }

        if (srcx + width > dataWidth) {
            width = dataWidth - srcx;
        }
        if (srcy + height > dataHeight) {
            height = dataHeight - srcy;
        }
        if (dstx + width > texWidth) {
            width = texWidth - dstx;
        }
        if (dsty + height > texHeight) {
            height = texHeight - dsty;
        }

        checkCompressedTextureExtensions(gl, data);

        if (data.isDataCompressed()) {
            gl.glCompressedTexSubImage2D(newTarget, mipmapLevel,
                                         dstx, dsty, width, height,
                                         data.getInternalFormat(),
                                         buffer.remaining(), buffer);
        } else {
            int[] align = { 0 };
            int[] rowLength = { 0 };
            int[] skipRows = { 0 };
            int[] skipPixels = { 0 };
            gl.glGetIntegerv(GL.GL_UNPACK_ALIGNMENT,   align,      0); // save alignment
            if(gl.isGL2GL3()) {
                gl.glGetIntegerv(GL2GL3.GL_UNPACK_ROW_LENGTH,  rowLength,  0); // save row length
                gl.glGetIntegerv(GL2GL3.GL_UNPACK_SKIP_ROWS,   skipRows,   0); // save skipped rows
                gl.glGetIntegerv(GL2GL3.GL_UNPACK_SKIP_PIXELS, skipPixels, 0); // save skipped pixels
            }
            gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, data.getAlignment());
            if (DEBUG && VERBOSE) {
                System.out.println("Row length  = " + rowlen);
                System.out.println("skip pixels = " + srcx);
                System.out.println("skip rows   = " + srcy);
                System.out.println("dstx        = " + dstx);
                System.out.println("dsty        = " + dsty);
                System.out.println("width       = " + width);
                System.out.println("height      = " + height);
            }
            if(gl.isGL2GL3()) {
                gl.glPixelStorei(GL2GL3.GL_UNPACK_ROW_LENGTH, rowlen);
                gl.glPixelStorei(GL2GL3.GL_UNPACK_SKIP_ROWS, srcy);
                gl.glPixelStorei(GL2GL3.GL_UNPACK_SKIP_PIXELS, srcx);
            } else {
                if ( rowlen!=0 && rowlen!=width &&
                     srcy!=0 && srcx!=0 ) {
                    throw new GLException("rowlen and/or x/y offset only available for GL2");
                }
            }

            gl.glTexSubImage2D(newTarget, mipmapLevel,
                               dstx, dsty, width, height,
                               data.getPixelFormat(), data.getPixelType(),
                               buffer);
            gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT,   align[0]);      // restore alignment
            if(gl.isGL2GL3()) {
                gl.glPixelStorei(GL2GL3.GL_UNPACK_ROW_LENGTH,  rowLength[0]);  // restore row length
                gl.glPixelStorei(GL2GL3.GL_UNPACK_SKIP_ROWS,   skipRows[0]);   // restore skipped rows
                gl.glPixelStorei(GL2GL3.GL_UNPACK_SKIP_PIXELS, skipPixels[0]); // restore skipped pixels
            }
        }
    }
  
 }
