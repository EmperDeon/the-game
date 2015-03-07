package main.dev;

import com.trolltech.qt.gui.*;
import main.Main;
import utils.qt.QTreeModel;

public class DevForm extends QMainWindow {

 private final QHBoxLayout mainLayout = new QHBoxLayout();
 private final QListView mmods = new QListView();
 private final QListView cmods = new QListView();
 private final QTreeModel mmodel = new QTreeModel();
 private final QTreeModel cmodel = new QTreeModel();
 private final QVBoxLayout panel = new QVBoxLayout();

 private final QPushButton boptions = new QPushButton(("Options"));
 private final QPushButton bmodsed = new QPushButton(("Mods Editor"));
 private final QPushButton bleveled = new QPushButton(("Level Editor"));
 private final QPushButton bmodeled = new QPushButton(("Model Editor"));
 private final QPushButton brinit = new QPushButton(("Reinitialize"));

 public DevForm () {
  super();
  QWidget mainW = new QWidget();
  setMinimumSize(400, 180);
  setWindowIcon(new QIcon("classpath:com/trolltech/images/qt-logo.png"));
  setWindowTitle(tr("Developer utils"));
  resize(400, 180);

  mmods.setModel(mmodel);
  cmods.setModel(cmodel);

  panel.addWidget(boptions);
  panel.addWidget(bmodsed);
  panel.addWidget(bleveled);
  panel.addWidget(bmodeled);
  panel.addWidget(brinit);

  boptions.clicked.connect(this, "options()");
  bmodsed.clicked.connect(this, "mods()");
  bleveled.clicked.connect(this, "level()");
  bmodeled.clicked.connect(this, "models()");
  brinit.clicked.connect(this, "reinit()");

  mainLayout.addWidget(mmods, 30);
  mainLayout.addWidget(cmods, 30);
  mainLayout.addLayout(panel);
  mainW.setLayout(mainLayout);

  setCentralWidget(mainW);
 }

 public void options () {
  Main.FORMS.optionseditor.show();
 }

 public void mods () {
  Main.FORMS.modeditor.show();
 }

 public void models () {
  Main.FORMS.modeditor.show();
 }

 public void level () {
  Main.FORMS.leveleditor.show();
 }

 public void reinit () {
  main.Main.main.reinit();
 }
}
//  screenshotLabel = new QLabel();
//  screenshotLabel.setSizePolicy(QSizePolicy.Policy.Expanding,
//                                QSizePolicy.Policy.Expanding);
//  screenshotLabel.setAlignment(Qt.AlignmentFlag.AlignCenter);
//  screenshotLabel.setMinimumSize(240, 160);
//
//  optionsGroupBox = new QGroupBox(tr("Options"));
//  
//  delaySpinBox = new QSpinBox();
//  delaySpinBox.setSuffix(tr(" s"));
//  delaySpinBox.setMaximum(60);
//  delaySpinBox.valueChanged.connect(this, "updateCheckBox()");
//
//  delaySpinBoxLabel = new QLabel(tr("Screenshot Delay:"));
//
//  hideThisWindowCheckBox = new QCheckBox(tr("Hide This Window"));
//
//  optionsGroupBoxLayout = new QGridLayout();
//  optionsGroupBoxLayout.addWidget(delaySpinBoxLabel, 0, 0);
//  optionsGroupBoxLayout.addWidget(delaySpinBox, 0, 1);
//  optionsGroupBoxLayout.addWidget(hideThisWindowCheckBox, 1, 0, 1, 2);
//  optionsGroupBox.setLayout(optionsGroupBoxLayout);
//
//  newScreenshotButton = createButton(tr("New Screenshot"), this,
//                                     "newScreenshot()");
//
//  saveScreenshotButton = createButton(tr("Save Screenshot"), this,
//                                      "saveScreenshot()");
//
//  quitScreenshotButton = createButton(tr("Quit"), this, "close()");
//
//  buttonsLayout = new QHBoxLayout();
//  buttonsLayout.addStretch();
//  buttonsLayout.addWidget(newScreenshotButton);
//  buttonsLayout.addWidget(saveScreenshotButton);
//  buttonsLayout.addWidget(quitScreenshotButton);
//

//  mainLayout.addWidget(cmods);
//  mainLayout.addLayout(buttonsLayout);
//
//  delaySpinBox.setValue(5);
