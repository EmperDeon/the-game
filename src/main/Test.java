package main;

import com.trolltech.qt.gui.QApplication;

public class Test {

 public static void main ( String args[] ) {
  QApplication.initialize(args);
  LauncherForm form = new LauncherForm();
  form.show();
  QApplication.execStatic();
  QApplication.shutdown();
 }
}
