package render.gui.widgets;
import java.io.IOException;
import java.util.List;
import org.fenggui.theme.xml.IXMLStreamable;
import static org.fenggui.theme.xml.IXMLStreamable.GENERATE_NAME;
import org.fenggui.theme.xml.IXMLStreamableException;
import org.fenggui.theme.xml.InputOutputStream;
import org.fenggui.util.Dimension;

public class StdLayout implements IXMLStreamable{
 public void doLayout(StdAppearance app, List<StdWidget> content){
  int y = 0;

  for (int i = 0; i < content.size(); i++){
   StdWidget w;
   w = content.get(content.size() - i - 1);

   w.setY(y);
   y += w.getSize().getHeight();
   w.setX(app.getContentWidth() / 2 - w.getSize().getWidth() / 2);
  }
 }
 public int getValidMinHeight(StdWidget w) {return w.getSize().getHeight();}
 public int getValidMinWidth(StdWidget w) {return w.getSize().getWidth();  }
 protected int getSumOfAllHeights(List<StdWidget> content) {
    int sum = 0;
    sum = content.stream().
          map(( c ) -> getValidMinHeight(c)).
          reduce(sum ,
                 Integer::sum);
    return sum;
  }
 public Dimension computeMinSize(List<StdWidget> content) {
    int minW = 0;
    int minH = 0;

    for (StdWidget c : content)
    {if (minW < getValidMinWidth(c))
          minW = getValidMinWidth(c);
        minH += getValidMinHeight(c);
      }
    return new Dimension(minW, minH);
  }
 @Override public void process(InputOutputStream stream) throws IOException, IXMLStreamableException {}
 @Override public String getUniqueName() {return GENERATE_NAME;}
}
