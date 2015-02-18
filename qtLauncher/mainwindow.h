#include <QtGui>
#include <QtNetwork>

class QWebView;
QT_BEGIN_NAMESPACE
class QLineEdit;
QT_END_NAMESPACE

class MainWindow : public QMainWindow{
  Q_OBJECT

 public:
  MainWindow();

 protected slots:
  void adjustLocation();
  void changeLocation();
  void onStart();
  void onLogger();
  void downFinish(QNetworkReply *reply);
  void unZip();

 private:
  QWebView *view;
  QLineEdit *locationEdit;
  QAction *rotateAction;
  QVBoxLayout *down;
  QHBoxLayout *menu;
  QLineEdit *login;
  QLineEdit *passw;
  QListView *vers;
  QLabel *lvers;
  QLabel *lpass;
  QLabel *llogin;
  QSpacerItem *space;
  QPushButton *start;
  QPushButton *logger;

};
