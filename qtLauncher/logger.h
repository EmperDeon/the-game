#include <QtGui>

class QWebView;
QT_BEGIN_NAMESPACE
class QLineEdit;

<< << << < HEAD

QT_END_NAMESPACE

class Logger : public QMainWindow {
 Q_OBJECT

public:
 Logger();

protected slots:
 void onTimer();
 void lErr();
 void lWarn();
 void lInfo();
 void lDebg();

private:
 QWebView *view;
 QTimer *timer;
 QAction *mErrs;
 QAction *mWarn;
 QAction *mInfo;
 QAction *mDebg;
 QMenu *mlevel;

 == == == =
         QT_END_NAMESPACE

         class Logger : public QMainWindow {
  Q_OBJECT

 public:
  Logger();

 protected slots:
  void onTimer();
  void lErr();
  void lWarn();
  void lInfo();
  void lDebg();

 private:
  QWebView *view;
  QTimer *timer;
  QAction *mErrs;
  QAction *mWarn;
  QAction *mInfo;
  QAction *mDebg;
  QMenu *mlevel;
  >>>>>>> origin / master
 };
