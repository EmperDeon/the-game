package utils;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;

public final class LibLoader {

 public static void loadLibs () {
  try {
   for ( File e : new File(main.Main.DIR + "lib/").listFiles() ) {
    Method method = URLClassLoader.class.getDeclaredMethod("addURL",
                                                           new Class[]{
                                                            URL.class });
    method.setAccessible(true);
    method.invoke(ClassLoader.getSystemClassLoader(), new Object[]{ e.toURI().
     toURL() });
   }
  } catch ( NoSuchMethodException | SecurityException | IllegalAccessException |
            IllegalArgumentException | InvocationTargetException | IOException ex ) {
   main.Main.LOG.addE(ex);
  }
 }
}
