package render.tex.spi.awt;

import java.awt.image.*;
import javax.media.opengl.*;
import render.tex.Tex;
import render.tex.TexData;
import render.tex.TexIO;

public class AWTTexIO extends TexIO {
    /**
     * Creates a TexData from the given BufferedImage. Does no
     * OpenGL work.
     * We assume a desktop GLProfile GL2GL3, otherwise use the other factory.
     *
     * @param glp the OpenGL Profile this texture data should be
     *                  created for.
     * @param image the BufferedImage containing the texture data
     * @param mipmap     whether mipmaps should be produced for this
     *                   texture by autogenerating them
     * @return the texture data from the image
     *
     * @see #newTexData(GLProfile, BufferedImage, boolean)
     */
    public static TexData newTexData(GLProfile glp, BufferedImage image,
                                             boolean mipmap) {
        return newTexDataImpl(glp, image, 0, 0, mipmap);
    }

    /**
     * Creates a TexData from the given BufferedImage, using the
     * specified OpenGL internal format and pixel format for the texture
     * which will eventually result. The internalFormat and pixelFormat
     * must be specified and may not be zero; to use default values, use
     * the variant of this method which does not take these
     * arguments. Does no OpenGL work.
     *
     * @param glp the OpenGL Profile this texture data should be
     *                  created for.
     * @param image the BufferedImage containing the texture data
     * @param internalFormat the OpenGL internal format of the texture
     *                   which will eventually result from the TexData
     * @param pixelFormat the OpenGL pixel format of the texture
     *                    which will eventually result from the TexData
     * @param mipmap     whether mipmaps should be produced for this
     *                   texture either by autogenerating them or
     *                   reading them from the file. Some file formats
     *                   support multiple mipmaps in a single file in
     *                   which case those mipmaps will be used rather
     *                   than generating them.
     * @return the texture data from the image
     * @throws IllegalArgumentException if either internalFormat or
     *                                  pixelFormat was 0
     */
    public static TexData newTexData(GLProfile glp, BufferedImage image,
                                             int internalFormat,
                                             int pixelFormat,
                                             boolean mipmap) throws IllegalArgumentException {
        if ((internalFormat == 0) || (pixelFormat == 0)) {
            throw new IllegalArgumentException("internalFormat and pixelFormat must be non-zero");
        }

        return newTexDataImpl(glp, image, internalFormat, pixelFormat, mipmap);
    }

    /**
     * Creates an OpenGL texture object from the specified BufferedImage
     * using the current OpenGL context.
     *
     * @param glp the OpenGL Profile this texture data should be
     *                  created for.
     * @param image the BufferedImage from which to read the texture data
     * @param mipmap     whether mipmaps should be produced for this
     *                   texture by autogenerating them
     * @throws GLException if no OpenGL context is current or if an
     *                     OpenGL error occurred
     */
    public static Tex newTex(GLProfile glp, BufferedImage image, boolean mipmap) throws GLException {
        TexData data = newTexData(glp, image, mipmap);
        Tex texture = new Tex(data);
        data.flush();
        return texture;
    }

    private static TexData newTexDataImpl(GLProfile glp,
                                                  BufferedImage image,
                                                  int internalFormat,
                                                  int pixelFormat,
                                                  boolean mipmap) {
        return new AWTTexData(glp, internalFormat, pixelFormat, mipmap, image);
    }
}
