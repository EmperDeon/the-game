package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public final class LibLoader {

 public static void loadLibs () {
  try {
   for ( File e : new File(main.Main.mdir + "lib/").listFiles() ) {
    Method method = URLClassLoader.class.getDeclaredMethod("addURL" ,
                                                           new Class[]{URL.class});
    method.setAccessible(true);
    method.invoke(ClassLoader.getSystemClassLoader() , new Object[]{e.toURI().
     toURL()});
   }
  } catch ( NoSuchMethodException | SecurityException | IllegalAccessException |
            IllegalArgumentException | InvocationTargetException | IOException ex ) {
   main.Main.LOG.addE(ex);
  }
 }
}
