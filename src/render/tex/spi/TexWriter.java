package render.tex.spi;

import java.io.*;
import render.tex.TexData;

public interface TexWriter {
    public boolean write(File file, TexData data) throws IOException;
}
