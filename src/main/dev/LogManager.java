/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dev;

import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTextEdit;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import static main.Main.LOG;
import utils.Logger.Type;

/**
 *
 * @author ilya
 */
public class LogManager extends QWidget {

 private Type active = Type.All;
 private final QPushButton mexcp;
 private final QPushButton mwarn;
 private final QPushButton minfo;
 private final QPushButton mdebg;
 private final QPushButton mall;
 private final QTextEdit edit;
 private final QVBoxLayout layout;
 private final QHBoxLayout blay;
 public LogManager () {
  layout = new QVBoxLayout(this);
  blay = new QHBoxLayout(this);
  
  setMinimumSize(300 , 400);

  edit = new QTextEdit(this);
  edit.setMinimumSize(300, 400);
    
  mexcp = new QPushButton ("Show exception level", this);
  mexcp.clicked.connect(this , "mexcp()");

  mwarn = new QPushButton ("Show warning level", this);
  mwarn.clicked.connect(this , "mwarn()");

  minfo = new QPushButton ("Show information level", this);
  minfo.clicked.connect(this , "minfo()");

  mdebg = new QPushButton ("Show debud level", this);
  mdebg.clicked.connect(this , "mdebg()");

  mall = new QPushButton ("Show all levels", this);
  mall.clicked.connect(this , "mall()");

  blay.addWidget(mexcp);
  blay.addWidget(mwarn);
  blay.addWidget(minfo);
  blay.addWidget(mdebg);
  blay.addWidget(mall);
  
  layout.addLayout(blay);
  layout.addWidget(edit);
  
  

  this.layout().activate();
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
