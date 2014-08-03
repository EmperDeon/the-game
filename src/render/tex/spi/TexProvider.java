
package render.tex.spi;

import java.io.*;
import java.net.*;
import javax.media.opengl.GLProfile;
import render.tex.TexData;

/** Plug-in interface to TextureIO to support reading OpenGL textures
    from new file formats. For all methods, either internalFormat or
    pixelFormat may be 0 in which case they must be inferred as
    e.g. RGB or RGBA depending on the file contents.
*/

public interface TexProvider {

    /**
     * Produces a TextureData object from a file, or returns null if the
     * file format was not supported by this TextureProvider. Does not
     * do any OpenGL-related work. The resulting TextureData can be
     * converted into an OpenGL texture in a later step.
     *
     * @param glp the OpenGL Profile this texture data should be
     *                  created for.
     * @param file         the file from which to read the texture data
     *
     * @param internalFormat the OpenGL internal format to be used for
     *                       the texture, or 0 if it should be inferred
     *                       from the file's contents
     *
     * @param pixelFormat    the OpenGL pixel format to be used for
     *                       the texture, or 0 if it should be inferred
     *                       from the file's contents
     *
     * @param mipmap     whether mipmaps should be produced for this
     *                   texture either by autogenerating them or
     *                   reading them from the file. Some file formats
     *                   support multiple mipmaps in a single file in
     *                   which case those mipmaps will be used rather
     *                   than generating them.
     *
     * @param fileSuffix     the file suffix to be used as a hint to the
     *                       provider to more quickly decide whether it
     *                       can handle the file, or null if the
     *                       provider should infer the type from the
     *                       file's contents
     *
     * @throws IOException if an error occurred while reading the file
     */
    public TexData newTexData(GLProfile glp, File file,
                                      int internalFormat,
                                      int pixelFormat,
                                      boolean mipmap,
                                      String fileSuffix) throws IOException;

    /**
     * Produces a TextureData object from a stream, or returns null if
     * the file format was not supported by this TextureProvider. Does
     * not do any OpenGL-related work. The resulting TextureData can be
     * converted into an OpenGL texture in a later step.
     *
     * @param glp the OpenGL Profile this texture data should be
     *                  created for.
     * @param stream       the stream from which to read the texture data
     *
     * @param internalFormat the OpenGL internal format to be used for
     *                       the texture, or 0 if it should be inferred
     *                       from the file's contents
     *
     * @param pixelFormat    the OpenGL pixel format to be used for
     *                       the texture, or 0 if it should be inferred
     *                       from the file's contents
     *
     * @param mipmap     whether mipmaps should be produced for this
     *                   texture either by autogenerating them or
     *                   reading them from the file. Some file formats
     *                   support multiple mipmaps in a single file in
     *                   which case those mipmaps will be used rather
     *                   than generating them.
     *
     * @param fileSuffix     the file suffix to be used as a hint to the
     *                       provider to more quickly decide whether it
     *                       can handle the file, or null if the
     *                       provider should infer the type from the
     *                       file's contents
     *
     * @throws IOException if an error occurred while reading the stream
     */
    public TexData newTexData(GLProfile glp, InputStream stream,
                                      int internalFormat,
                                      int pixelFormat,
                                      boolean mipmap,
                                      String fileSuffix) throws IOException;

    /**
     * Produces a TextureData object from a URL, or returns null if the
     * file format was not supported by this TextureProvider. Does not
     * do any OpenGL-related work. The resulting TextureData can be
     * converted into an OpenGL texture in a later step.
     *
     * @param glp the OpenGL Profile this texture data should be
     *                  created for.
     * @param url          the URL from which to read the texture data
     *
     * @param internalFormat the OpenGL internal format to be used for
     *                       the texture, or 0 if it should be inferred
     *                       from the file's contents
     *
     * @param pixelFormat    the OpenGL pixel format to be used for
     *                       the texture, or 0 if it should be inferred
     *                       from the file's contents
     *
     * @param mipmap     whether mipmaps should be produced for this
     *                   texture either by autogenerating them or
     *                   reading them from the file. Some file formats
     *                   support multiple mipmaps in a single file in
     *                   which case those mipmaps will be used rather
     *                   than generating them.
     *
     * @param fileSuffix     the file suffix to be used as a hint to the
     *                       provider to more quickly decide whether it
     *                       can handle the file, or null if the
     *                       provider should infer the type from the
     *                       file's contents
     *
     * @throws IOException if an error occurred while reading the URL
     */
    public TexData newTexData(GLProfile glp, URL url,
                                      int internalFormat,
                                      int pixelFormat,
                                      boolean mipmap,
                                      String fileSuffix) throws IOException;
}
