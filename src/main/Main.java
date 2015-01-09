package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mods.basemod.containers.ModsContainer;
import mods.basemod.containers.Repository;
import mods.basemod.containers.Resources;
import render.Render;
import utils.LibLoader;
import utils.Logger;
import utils.MActionListener;
import utils.json.JSONObject;

public final class Main implements Runnable {

 public final static render.Render rend = new Render();
 public final static Main main = new Main();
 public final static Thread Tm = new Thread(main);
 public final static Thread Tr = new Thread(rend);

 public final static String mdir = System.getProperty("user.dir").substring(0 ,
                                                                            System.
                                                                            getProperty(
                                                                                    "user.dir").
                                                                            lastIndexOf(
                                                                                    "/") + 1);

 public final static Resources RES = new Resources();
 public final static Repository REP = new Repository();
 public final static Logger LOG = new Logger();
 public final static JSONObject OPTIONS = new JSONObject(mdir + "options.db");
 public final static MActionListener TIMER = new MActionListener();
 public final static ModsContainer mods = new ModsContainer();

 public static LogManager logmanager;
 public static ModEditor modeditor;
 public static ModelEditor modeleditor;
 public static LevelEditor leveleditor;
 public static OptionsEditor optionseditor;
 public static MainForm mainform;

 public boolean running = true;

 @Override
 public void run () {
  System.getProperties().stringPropertyNames().stream().
          forEach(( e ) -> {
           LOG.addI(e + ": " + System.getProperties().getProperty(e));
          });
  mods.load();
  LibLoader.loadLibs();
 }

 public void destroy () {
  mainform.setVisible(true);
 }

 public static void main ( String args[] ) {
  try {
   for ( UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
    if ( "Nimbus".equals(info.getName()) ) {
     UIManager.setLookAndFeel(info.getClassName());
     break;
    }
   }
  } catch ( ClassNotFoundException | InstantiationException |
            IllegalAccessException | UnsupportedLookAndFeelException ex ) {
  }

  logmanager = new LogManager();
  logmanager.setVisible(true);
  modeditor = new ModEditor();
  modeleditor = new ModelEditor();
  leveleditor = new LevelEditor();
  optionseditor = new OptionsEditor();
  mainform = new MainForm();

  Tm.start();
 }

}
