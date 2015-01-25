package main;

import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QFileInfo;
import com.trolltech.qt.core.QUrl;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.network.QHttp;
import com.trolltech.qt.network.QHttpResponseHeader;
import com.trolltech.qt.webkit.QWebView;

public class UrlToString {

 private QHttp http;
 private QFile file;
 private int httpGetId;
 private boolean httpRequestAborted;
 private QWebView browser;

 public UrlToString ( QWebView browser ) {
  this.browser = browser;

  http = new QHttp();

  http.requestFinished.connect(this , "httpRequestFinished(int, boolean)");
  http.responseHeaderReceived.connect(this ,
                                      "readResponseHeader(QHttpResponseHeader)");

 }

 private void downloadFile ( String url1 ) {

  QUrl url = new QUrl(url1);
  if ( url1.endsWith(url.host()) ) {
   url = new QUrl(url1 + "/");
  }

  QFileInfo fileInfo = new QFileInfo(url.path());
  String fileName = fileInfo.fileName();
  if ( fileName.equals("") ) {
   fileName = "index.html";
  }

  file = new QFile(fileName);

  QHttp.ConnectionMode mode = url.scheme().toLowerCase().equals("https")
          ? QHttp.ConnectionMode.ConnectionModeHttps
          : QHttp.ConnectionMode.ConnectionModeHttp;
  http.setHost(url.host() , mode , url.port() == -1 ? 0 : url.port());

  if ( !url.userName().equals("") ) {
   http.setUser(url.userName() , url.password());
  }

  httpRequestAborted = false;
  httpGetId = http.get(url.path() , file);

 }

 private void httpRequestFinished ( int requestId , boolean error ) {
  if ( requestId != httpGetId ) {
   return;
  }
  if ( httpRequestAborted ) {
   if ( file != null ) {
    file.close();
    file.remove();
    file = null;
   }

   return;
  }

  if ( requestId != httpGetId ) {
   return;
  }

  file.close();

  file.fileName();
  
  file = null;
 }

 private void readResponseHeader ( QHttpResponseHeader responseHeader ) {
  if ( responseHeader.statusCode() != 200 ) {
   QMessageBox.information(null , "HTTP" ,
                           "Download failed: "
                           + responseHeader.reasonPhrase() + ".");
   httpRequestAborted = true;
   http.abort();
  }
 }

}
