/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dev;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QTextEdit;
import static main.Main.LOG;
import utils.Logger.Type;

/**
 *
 * @author ilya
 */
public class LogManager extends QMainWindow {

 private Type active = Type.All;
 private final QMenu menu;
 private final QAction mexcp;
 private final QAction mwarn;
 private final QAction minfo;
 private final QAction mdebg;
 private final QAction mall;
 private final QTextEdit edit;

 public LogManager () {
  setMinimumSize(300 , 400);

  edit = new QTextEdit(this);
  
  menu = menuBar().addMenu("Show other level");
  
  mexcp = new QAction("Show exception level", this);
  mexcp.triggered.connect(this , "mexcp()");
  menu.addAction(mexcp);
  mwarn = new QAction("Show warning level", this);
  mwarn.triggered.connect(this , "mwarn()");

  minfo = new QAction("Show information level", this);
  minfo.triggered.connect(this , "minfo()");

  mdebg = new QAction("Show debud level", this);
  mdebg.triggered.connect(this , "mdebg()");

  mall = new QAction("Show all levels", this);
  mall.triggered.connect(this , "mall()");

  menu.addAction(mexcp);
  menu.addAction(mwarn);
  menu.addAction(minfo);
  menu.addAction(mdebg);
  menu.addAction(mall);
  
  layout().addWidget(edit);

  

  show();
 }

 public void mexcp () {
  edit.setHtml(LOG.getE());
  active = Type.Error;
 }

 public void mwarn () {
  edit.setHtml(LOG.getW());
  active = Type.Warning;
 }

 public void minfo () {
  edit.setHtml(LOG.getI());
  active = Type.Info;
 }

 public void mdebg () {
  edit.setHtml(LOG.getD());
  active = Type.Debug;
 }

 public void mall () {
  edit.setHtml(LOG.getAll());
  active = Type.All;
 }

 public void update1 () {
  switch ( active ) {
   case Error:
    mexcp();
   case Warning:
    mwarn();
   case Info:
    minfo();
   case Debug:
    mdebg();
   case All:
    mall();
  }
 }
}
