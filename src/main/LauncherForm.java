package main;

import com.trolltech.qt.core.QUrl;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QToolBar;
import com.trolltech.qt.webkit.QWebSettings.WebAttribute;
import com.trolltech.qt.webkit.QWebView;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;

public final class LauncherForm extends QMainWindow {

 private final QWebView browser;
 private final QLineEdit field;
 private final QAction forward;
 private final QAction backward;
 private final QAction reload;
 private final QAction stop;

 public LauncherForm () {
  super();
  show();

  field = new QLineEdit();
  browser = new QWebView();
  browser.page().settings().setAttribute(WebAttribute.JavascriptEnabled, false);

  // browser.setHtml("<!DOCTYPE><html><head></head><body><div style=\\\"color:rgb(200, 200, 200)\\\">Some Text</div></body></html>");
  QToolBar toolbar = addToolBar("Actions");
  backward = toolbar.addAction("Backward");
  forward = toolbar.addAction("Forward");
  reload = toolbar.addAction("Reload");
  stop = toolbar.addAction("Stop");
  toolbar.addWidget(field);
  toolbar.setFloatable(false);
  toolbar.setMovable(false);

  setCentralWidget(browser);
  statusBar().show();

  setWindowTitle("Hello WebKit");
  setWindowIcon(new QIcon("classpath:com/trolltech/images/qt-logo.png"));

 // browser.settings().setAttribute(WebAttribute.JavascriptEnabled, true);
  browser.settings().setAttribute(WebAttribute.JavaEnabled, true);
  browser.settings().setAttribute(WebAttribute.DeveloperExtrasEnabled, true);
  //browser.settings().setAttribute(WebAttribute.JavascriptCanOpenWindows, true);
  browser.settings().setAttribute(WebAttribute.AutoLoadImages, true);
  browser.settings().setAttribute(WebAttribute.LinksIncludedInFocusChain, true);
  
  
  
  field.returnPressed.connect(this , "open()");

  browser.loadStarted.connect(this , "loadStarted()");
  browser.loadProgress.connect(this , "loadProgress(int)");
  browser.loadFinished.connect(this , "loadDone()");
  browser.urlChanged.connect(this , "urlChanged(QUrl)");

  forward.triggered.connect(browser , "forward()");
  backward.triggered.connect(browser , "back()");
  reload.triggered.connect(browser , "reload()");
  stop.triggered.connect(browser , "stop()");
 
  QApplication.invokeLater(() -> {
   field.setText("http://yandex.ru");
   open();
  });

 }

 public void urlChanged ( QUrl url ) {
  field.setText(url.toString());
 }

 public void loadStarted () {
  statusBar().showMessage("Starting to load: " + field.text());
 }

 public void loadDone () {
  statusBar().showMessage("Loading done");
 }

 public void loadProgress ( int x ) {
  statusBar().showMessage("Loading: " + x + " %");
 }

 public void open () {
  String url = field.text();
  if ( !url.contains("://") ) {
   url = "http://" + url;
  }

  StringBuilder html = new StringBuilder();

  try ( LineNumberReader r = new LineNumberReader(new InputStreamReader(new URL(
          url).
          openStream())) ) {
   String s = r.readLine();
   while ( s != null ) {
    html.append(s);
    s = r.readLine();
   }

  } catch ( IOException ex ) {

  }

  browser.load(new QUrl(url));
  
  browser.setHtml(html.toString() , new QUrl(url));
  browser.font().setPixelSize(5);
 }

 @Override
 protected void closeEvent ( QCloseEvent event ) {
  browser.loadProgress.disconnect(this);
  browser.loadFinished.disconnect(this);
 }

}
