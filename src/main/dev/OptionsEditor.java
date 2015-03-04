package main.dev;

import com.trolltech.qt.gui.*;
import java.util.*;

public final class OptionsEditor extends QMainWindow {
 private final QTableWidget prop;
 private final QPushButton save;
 private final QPushButton padd;
 private final QLineEdit pkey;
 private final QLineEdit pval;
 public final Map<String, String> list = new TreeMap<>();

 public OptionsEditor () {
  QWidget mainForm = new QWidget();
  QVBoxLayout main = new QVBoxLayout();
  QHBoxLayout form = new QHBoxLayout();

  prop = new QTableWidget();
  padd = new QPushButton("Add");
  save = new QPushButton("Save");
  pkey = new QLineEdit();
  pval = new QLineEdit();

  prop.insertColumn(0);
  prop.setColumnWidth(0, 200);
  prop.insertColumn(1);
  prop.setColumnWidth(1, 400);

  prop.setHorizontalHeaderLabels(Arrays.asList(new String[]{ "Key", "Value" }));

  padd.clicked.connect(this, "add()");
  save.clicked.connect(this, "save()");

  form.addWidget(pkey);
  form.addWidget(pval);
  form.addWidget(padd);
  form.addWidget(save);

  main.addWidget(prop);
  main.addLayout(form);

  mainForm.setLayout(main);
  setCentralWidget(mainForm);
  load();
 }

 public void add ( String k, String v ) {
  list.put(k, v);
  int row = list.size() - 1;
  prop.insertRow(row);
  prop.setItem(row, 0, new QTableWidgetItem(k));
  prop.setItem(row, 1, new QTableWidgetItem(v));
 }

 public void add () {
  add(this.pkey.text(), this.pval.text());
 }

 public void load () {
  
 }

 public void save () {
  
 }
}
