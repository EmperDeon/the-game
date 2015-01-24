package main;

import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QListView;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import utils.qt.QTreeModel;

public final class MainForm extends QWidget {

// volatile QPixmap originalPixmap;
//
// private final QLabel screenshotLabel;
// private final QGroupBox optionsGroupBox;
// private final QSpinBox delaySpinBox;
// private final QLabel delaySpinBoxLabel;
// private final QCheckBox hideThisWindowCheckBox;
// private final QPushButton newScreenshotButton;
// private final QPushButton saveScreenshotButton;
// private final QPushButton quitScreenshotButton;
//

// private final QGridLayout optionsGroupBoxLayout;
// private final QHBoxLayout buttonsLayout;
 private final QHBoxLayout mainLayout = new QHBoxLayout();
 private final QListView mmods = new QListView();
 private final QListView cmods = new QListView();
 private final QTreeModel mmodel = new QTreeModel();
 private final QTreeModel cmodel = new QTreeModel();
 private final QVBoxLayout panel = new QVBoxLayout();

 public MainForm () {
  super();
  setMinimumSize(700 , 400);
  setWindowIcon(new QIcon("classpath:com/trolltech/images/qt-logo.png"));
  setWindowTitle(tr("Screenshot"));
  resize(700 , 400);

  mmods.setModel(mmodel);

  cmods.setModel(cmodel);
  
  
  
//  QPushButton button = new QPushButton(text);
//  button.clicked.connect(receiver , member);
  
  mainLayout.addWidget(mmods,30);
  mainLayout.addWidget(cmods,30);
  mainLayout.addLayout(panel);
  setLayout(mainLayout);


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