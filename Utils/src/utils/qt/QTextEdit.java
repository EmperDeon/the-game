package utils.qt;
import com.trolltech.qt.QNoNativeResourcesException;
import com.trolltech.qt.gui.QWidget;

public class QTextEdit extends com.trolltech.qt.gui.QTextEdit{
 public QTextEdit(QWidget w){
  super(w);
 }
 
 public QTextEdit(){
  super();
 }
 
 public final void setHtml1(java.lang.String text)  {
 // com.trolltech.qt.GeneratorUtilities.threadCheck(this);
        if (nativeId() == 0)
            throw new QNoNativeResourcesException("Function call on incomplete object of type: " +getClass().getName());
        __qt_setHtml_String(nativeId(), text);
    }
    native void __qt_setHtml_String(long __this__nativeId, java.lang.String text);
 }

