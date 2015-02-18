#include <QtGui>
#include <QtNetwork>
#include <QtWebKit>
#include "logger.h"

int logsize;
int level;
Logger::Logger(){
 logsize = 0;
 level = 0;
 view = new QWebView(this);

 mErrs = new QAction(tr("Errors"), this);
 connect(mErrs, SIGNAL(triggered()), this, SLOT(lErr()));

 mWarn = new QAction(tr("Warnings"), this);
 connect(mWarn, SIGNAL(triggered()), this, SLOT(lWarn()));

 mInfo = new QAction(tr("Info"), this);
 connect(mInfo, SIGNAL(triggered()), this, SLOT(lInfo()));

 mDebg = new QAction(tr("Debug"), this);
 connect(mDebg, SIGNAL(triggered()), this, SLOT(lDebg()));

 mlevel = menuBar()->addMenu(tr("Change logging level:"));
 mlevel->addAction(mErrs);
 mlevel->addAction(mWarn);
 mlevel->addAction(mInfo);
 mlevel->addAction(mDebg);

 timer = new QTimer(this);
 connect(timer, SIGNAL(timeout()), this, SLOT(onTimer()));
 timer->start(1000);

 setCentralWidget(view);
}

void Logger::onTimer(){
 QFile file ("now.log");
 if((file.open(QFile::ReadOnly | QFile::Text))&&(logsize != file.size())){
  QString res = QString();
  res.append("<html><body>");

  QTextStream in(&file);
  while (!in.atEnd()) {
   QString line = in.readLine();
   QStringList fields = line.split('&');
   if (fields.size() >= 2) {
    QString type = fields.takeFirst();
    QString message = fields.takeFirst();
    if((type == "error"  ) && (level <= 3)) res.append("<div style=\" color: red     \" >" + message + "</div>");
    if((type == "warning") && (level <= 2)) res.append("<div style=\" color: #ffc100 \" >" + message + "</div>");
    if((type == "info"   ) && (level <= 1)) res.append("<div style=\" color: #009dff \" >" + message + "</div>");
    if((type == "debug"  ) && (level <= 0)) res.append("<div style=\" color: #9f9f9f \" >" + message + "</div>");
   }
  }

  logsize = file.size();
  res.append("</body></html>");
  view->setHtml(res);
 }
}

void Logger::lErr(){
 level = 3;
 onTimer();
}
void Logger::lWarn(){
 level = 2;
 onTimer();
}
void Logger::lInfo(){
 level = 1;
 onTimer();
}
void Logger::lDebg(){
 level = 0;
 onTimer();
}
