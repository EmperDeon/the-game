package main;

import com.trolltech.qt.gui.QApplication;
import main.dev.DevForm;
import main.dev.LevelEditor;
import main.dev.ModEditor;
import main.dev.ModelEditor;
import main.dev.OptionsEditor;

public class Test {
 public static ModEditor modeditor;
 public static ModelEditor modeleditor;
 public static LevelEditor leveleditor;
 public static OptionsEditor optionseditor;
 public static DevForm devform;
 public static void main ( String args[] ) {

  QApplication.initialize(args);
  
  devform = new DevForm();
  modeditor = new ModEditor();
  modeleditor = new ModelEditor();
  leveleditor = new LevelEditor();
  optionseditor = new OptionsEditor();

//  devform.show();
  modeditor.show();
//  modeleditor.show();
//  leveleditor.show();
//  optionseditor.show();
  
  QApplication.execStatic();
  QApplication.shutdown();

 }
}
