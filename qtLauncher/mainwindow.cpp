#include <QtGui>
#include <QtNetwork>
#include <QtWebKit>
#include "mainwindow.h"
#include "logger.h"

MainWindow::MainWindow(){
 QNetworkProxyFactory::setUseSystemConfiguration(true);

 view = new QWebView(this);
 view->load(QUrl("http://google.com/"));
 connect(view, SIGNAL(loadFinished(bool)), SLOT(adjustLocation()));

 locationEdit = new QLineEdit(this);
 locationEdit->setSizePolicy(QSizePolicy::Expanding, locationEdit->sizePolicy().verticalPolicy());
 connect(locationEdit, SIGNAL(returnPressed()), SLOT(changeLocation()));

 QToolBar *toolBar = addToolBar(tr("Navigation"));
 toolBar->addAction(view->pageAction(QWebPage::Back));
 toolBar->addAction(view->pageAction(QWebPage::Forward));
 toolBar->addAction(view->pageAction(QWebPage::Reload));
 toolBar->addAction(view->pageAction(QWebPage::Stop));
 toolBar->addWidget(locationEdit);

 QWidget *mainWidget = new QWidget;

 QVBoxLayout *mainLayout = new QVBoxLayout;

 QHBoxLayout *verslayout = new QHBoxLayout;
 QHBoxLayout *lognLayout = new QHBoxLayout;

 menu = new QHBoxLayout();

 login =new QLineEdit;
 passw = new QLineEdit;
 //    vers = new QListView;
 lvers = new QLabel(tr("Version:"));
 llogin = new QLabel(tr("Login:"));
 lpass = new QLabel(tr("Password:"));
 space = new QSpacerItem(100,20);
 start = new QPushButton(tr("Start"));
 logger = new QPushButton(tr("Logger"));

 login->setFixedSize(150, 30);
 passw->setFixedSize(150, 30);

 connect(start, SIGNAL(clicked()), SLOT(onStart()));
 connect(logger, SIGNAL(clicked()), SLOT(onLogger()));

 verslayout->addWidget(lvers);
 //    verslayout->addWidget(vers);

 lognLayout->addWidget(llogin);
 lognLayout->addWidget(login);
 lognLayout->addWidget(lpass);
 lognLayout->addWidget(passw);
 lognLayout->addWidget(start);
 lognLayout->addWidget(logger);

 menu->addLayout(verslayout);
 menu->addSpacerItem(space);
 menu->addLayout(lognLayout);

 mainLayout->addWidget(view);
 mainLayout->addLayout(menu);

 mainWidget->setLayout(mainLayout);

 setCentralWidget(mainWidget);
 setUnifiedTitleAndToolBarOnMac(true);
}
void MainWindow::adjustLocation(){
 locationEdit->setText(view->url().toString());
}
void MainWindow::changeLocation(){
 view->load(QUrl(locationEdit->text()));
 view->setFocus();
}
void MainWindow::onLogger(){
 Logger* logger = new Logger();
 logger->show();
}
void MainWindow::onStart(){
 QFile game ("game.jar");
 if(game.size() != 0){
  QProcess vec (this);
  vec.start("java -cp game.jar main/Main");
  vec.waitForFinished(-1);
  qDebug() << vec.readAll();
 }else{
  QNetworkAccessManager *qnam = new QNetworkAccessManager(this);
  connect(qnam, SIGNAL(finished(QNetworkReply *)), SLOT(downFinish(QNetworkReply *)));
  qnam->get(QNetworkRequest(QUrl("https://raw.githubusercontent.com/ilz2010/the-game/master/the-game.zip")));
 }
}

void MainWindow::downFinish(QNetworkReply *reply) {
 QFile file("temp.zip");
 if (file.open(QIODevice::WriteOnly)){
  file.write(reply->readAll());
  file.flush();
  file.close();
 }

 unZip();
}

void MainWindow::unZip(){

}

