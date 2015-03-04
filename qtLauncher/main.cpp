#include <QtGui>
#include "mainwindow.h"
#include "logger.h"

<< << << < HEAD

int main(int argc, char * argv[]) {
 QApplication app(argc, argv);
 MainWindow* browser = new MainWindow();
 browser->show();
 // Logger* logger = new Logger();
 // logger->show();
 == == == =
         int main(int argc, char * argv[]){
  QApplication app(argc, argv);
  MainWindow* browser = new MainWindow();
  browser->show();
  // Logger* logger = new Logger();
  // logger->show();
  >>>>>>> origin / master

  return app.exec();
 }
