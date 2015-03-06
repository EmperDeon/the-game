package main.dev;

import com.trolltech.qt.gui.*;
import java.util.*;
import utils.json.JSONObject;

public final class OptionsEditor extends QMainWindow {
 private final QTableWidget prop;
 private final QPushButton padd;
 private final QLineEdit pkey;
 private final QLineEdit pval;
 private final QAction anew;
 private final QAction aload;
 private final QAction asave;
 private final QAction asaveas;
 private final QMenu menu;
 private String file;
 private final JSONObject json = new JSONObject();

 public OptionsEditor () {
  QWidget mainForm = new QWidget();
  QVBoxLayout main = new QVBoxLayout();
  QHBoxLayout form = new QHBoxLayout();

  setMinimumSize(10, 40);
  setMaximumSize(1920, 1080);

  anew = new QAction("New", this);
  anew.setShortcut(tr("Ctrl+N"));
  anew.setStatusTip("Create a new json file");
  anew.triggered.connect(this, "newF()");

  aload = new QAction("Open", this);
  aload.setShortcut(tr("Ctrl+O"));
  aload.setStatusTip("Open json file");
  aload.triggered.connect(this, "load()");

  asave = new QAction("Save", this);
  asave.setShortcut(tr("Ctrl+S"));
  asave.setStatusTip("Save json file");
  asave.triggered.connect(this, "save()");

  asaveas = new QAction("Save as", this);
  asaveas.setShortcut(tr("Ctrl+Shift+S"));
  asaveas.setStatusTip("Save json file as");
  asaveas.triggered.connect(this, "saveAs()");

  menu = menuBar().addMenu("File");
  menu.addAction(anew);
  menu.addAction(aload);
  menu.addAction(asave);
  menu.addAction(asaveas);

  prop = new QTableWidget();
  padd = new QPushButton("Add");
  pkey = new QLineEdit();
  pval = new QLineEdit();

  prop.insertColumn(0);
  prop.setColumnWidth(0, 200);
  prop.insertColumn(1);
  prop.setColumnWidth(1, 400);

  prop.setHorizontalHeaderLabels(Arrays.asList(new String[]{ "Key", "Value" }));

  padd.clicked.connect(this, "add()");

  form.addWidget(pkey);
  form.addWidget(pval);
  form.addWidget(padd);

  main.addWidget(prop);
  main.addLayout(form);

  mainForm.setLayout(main);
  setCentralWidget(mainForm);
 }

 public void add ( String k, String v ) {
  int row = json.length() - 1;
  prop.insertRow(row);
  prop.setItem(row, 0, new QTableWidgetItem(k));
  prop.setItem(row, 1, new QTableWidgetItem(v));
 }

 public void add () {
  json.put(this.pkey.text(), this.pval.text());
  add(this.pkey.text(), this.pval.text());
 }

 public void newF () {
  save();
  json.clear();
 }

 public void load () {
  String filen = QFileDialog.getOpenFileName(
     this,
     "Open",
     main.Main.DIR,
     new QFileDialog.Filter(
        String.format("%1$s Files (*.%2$s);;All Files (*)", "json".toUpperCase(), "json"))
  );

  if ( !"".equals(filen) ) {
   file = filen;
   json.load(file);
  } else {
   newF();
  }

  for ( String m : json.keySet() ) {
   add(m, json.get(m).toString());
  }
 }

 public void save () {

 }

 public void saveAs () {

 }
}
