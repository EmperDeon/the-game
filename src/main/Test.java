package main;

import com.trolltech.qt.gui.QApplication;

public class Test {

 public static void main ( String args[] ) {
  QApplication.initialize(args);
  MainForm form = new MainForm();
  form.show();
  QApplication.execStatic();
  QApplication.shutdown();
 }
}
