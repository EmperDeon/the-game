package render.tex.spi.awt;

import com.jogamp.common.util.IOUtil;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.texture.*;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import java.nio.*;
import javax.imageio.*;

import javax.media.opengl.*;

import render.tex.TexData;
import render.tex.spi.TexWriter;

public class IIOTextureWriter implements TexWriter {
    @Override
    public boolean write(File file,
                         TexData data) throws IOException {
        int pixelFormat = data.getPixelFormat();
        int pixelType   = data.getPixelType();
        if ((pixelFormat == GL.GL_RGB ||
             pixelFormat == GL.GL_RGBA) &&
            (pixelType == GL.GL_BYTE ||
             pixelType == GL.GL_UNSIGNED_BYTE)) {
            // Convert TextureData to appropriate BufferedImage
            // FIXME: almost certainly not obeying correct pixel order
            BufferedImage image = new BufferedImage(data.getWidth(), data.getHeight(),
                                                    (pixelFormat == GL.GL_RGB) ?
                                                    BufferedImage.TYPE_3BYTE_BGR :
                                                    BufferedImage.TYPE_4BYTE_ABGR);
            byte[] imageData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            ByteBuffer buf = (ByteBuffer) data.getBuffer();
            if (buf == null) {
                buf = (ByteBuffer) data.getMipmapData()[0];
            }
            buf.rewind();
            buf.get(imageData);
            buf.rewind();

            // Swizzle image components to be correct
            if (pixelFormat == GL.GL_RGB) {
                for (int i = 0; i < imageData.length; i += 3) {
                    byte red  = imageData[i + 0];
                    byte blue = imageData[i + 2];
                    imageData[i + 0] = blue;
                    imageData[i + 2] = red;
                }
            } else {
                for (int i = 0; i < imageData.length; i += 4) {
                    byte red   = imageData[i + 0];
                    byte green = imageData[i + 1];
                    byte blue  = imageData[i + 2];
                    byte alpha = imageData[i + 3];
                    imageData[i + 0] = alpha;
                    imageData[i + 1] = blue;
                    imageData[i + 2] = green;
                    imageData[i + 3] = red;
                }
            }

            // Flip image vertically for the user's convenience
            ImageUtil.flipImageVertically(image);

            // Happened to notice that writing RGBA images to JPEGS is broken
            if (TextureIO.JPG.equals(IOUtil.getFileSuffix(file)) &&
                image.getType() == BufferedImage.TYPE_4BYTE_ABGR) {
                BufferedImage tmpImage = new BufferedImage(image.getWidth(), image.getHeight(),
                                                           BufferedImage.TYPE_3BYTE_BGR);
                Graphics g = tmpImage.getGraphics();
                g.drawImage(image, 0, 0, null);
                g.dispose();
                image = tmpImage;
            }

            return ImageIO.write(image, IOUtil.getFileSuffix(file), file);
        }

        throw new IOException("ImageIO writer doesn't support this pixel format / type (only GL_RGB/A + bytes)");
    }
}
