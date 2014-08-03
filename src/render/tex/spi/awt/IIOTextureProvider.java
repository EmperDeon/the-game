package render.tex.spi.awt;

import javax.media.opengl.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;
import jogamp.opengl.Debug;
import render.tex.TexData;
import render.tex.spi.TexProvider;

public class IIOTextureProvider implements TexProvider {
    private static final boolean DEBUG = Debug.debug("TextureIO");

    @Override
    public TexData newTexData(GLProfile glp, File file,
                                      int internalFormat,
                                      int pixelFormat,
                                      boolean mipmap,
                                      String fileSuffix) throws IOException {
        BufferedImage img = ImageIO.read(file);
        if (img == null) {
            return null;
        }
        if (DEBUG) {
            System.out.println("TextureIO.newTexData(): BufferedImage type for " + file + " = " +
                               img.getType());
        }
        return new AWTTexData(glp, internalFormat, pixelFormat, mipmap, img);
    }

    @Override
    public TexData newTexData(GLProfile glp, InputStream stream,
                                      int internalFormat,
                                      int pixelFormat,
                                      boolean mipmap,
                                      String fileSuffix) throws IOException {
        BufferedImage img = ImageIO.read(stream);
        if (img == null) {
            return null;
        }
        if (DEBUG) {
            System.out.println("TextureIO.newTexData(): BufferedImage type for stream = " +
                               img.getType());
        }
        return new AWTTexData(glp, internalFormat, pixelFormat, mipmap, img);
    }

    @Override
    public TexData newTexData(GLProfile glp, URL url,
                                      int internalFormat,
                                      int pixelFormat,
                                      boolean mipmap,
                                      String fileSuffix) throws IOException {
        InputStream stream = url.openStream();
        try {
            return newTexData(glp, stream, internalFormat, pixelFormat, mipmap, fileSuffix);
        } finally {
            stream.close();
        }
    }
}
