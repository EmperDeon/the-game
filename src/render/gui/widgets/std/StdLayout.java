package render.gui.widgets.std;
import java.io.IOException;
import java.util.List;
import org.fenggui.Container;
import org.fenggui.IWidget;
import org.fenggui.layout.LayoutManager;
import static org.fenggui.theme.xml.IXMLStreamable.GENERATE_NAME;
import org.fenggui.theme.xml.IXMLStreamableException;
import org.fenggui.theme.xml.InputOutputStream;
import org.fenggui.util.Dimension;

public class StdLayout extends LayoutManager{
 @Override public void doLayout(Container app, List<IWidget> content){
  int y = 0;

  for (int i = 0; i < content.size(); i++){
   IWidget w;
   w = content.get(content.size() - i - 1);

   w.setY(y);
   y += w.getSize().getHeight();
   w.setX(app.getAppearance().getContentWidth() / 2 - w.getSize().getWidth() / 2);
  }
 }
 @Override public Dimension computeMinSize(List<IWidget> content) {
    int minW = 0;
    int minH = 0;

    for (IWidget c : content)
    {if (minW < getValidMinWidth(c))
          minW = getValidMinWidth(c);
        minH += getValidMinHeight(c);
      }
    return new Dimension(minW, minH);
  }
 @Override public void process(InputOutputStream stream) throws IOException, IXMLStreamableException {}
 @Override public String getUniqueName() {return GENERATE_NAME;}
}
