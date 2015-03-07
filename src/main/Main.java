package main;

import com.trolltech.qt.gui.QApplication;
import main.dev.*;
import mods.basemod.containers.*;
import render.Render;
import utils.*;
import utils.containers.json.JSONObject;

public final class Main implements Runnable {
 public final static String DIR = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("/") + 1);
 
 public final static JSONObject OPTIONS = new JSONObject(DIR + "options.json");
 public final static Logger LOG = new Logger();
 public final static Server SERVER = new Server();
 public final static Translator TRANSLATE = new Translator();
 
 public final static Forms FORMS = new Forms();
 
 public final static render.Render rend = new Render();
 public final static Main main = new Main();
 public final static Thread Tm = new Thread(main);
 public final static Thread Tr = new Thread(rend);

 @Override
 public void run () {
  System.getProperties().stringPropertyNames().stream().
     forEach(( e ) -> {
      LOG.addI(e + ": " + System.getProperties().getProperty(e));
     });
  SERVER.getMods().load();
  LibLoader.loadLibs();
 }

 public void reinit () {

 }

 public void destroy () {

 }

 public static void main ( String args[] ) {
  QApplication.initialize(args);

  FORMS.init();

  //Tm.start();
  QApplication.execStatic();
  QApplication.shutdown();
 }

 public final static String t ( String k ) {
  return TRANSLATE.get(k);
 }
}
