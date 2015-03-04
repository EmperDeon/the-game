package utils.qt;

import com.trolltech.qt.core.*;
import java.util.*;

/**
 * A pretty basic node implementation...
 */
class Node implements Cloneable {

 public Node ( String s, QTreeModel model, Node parent ) {
  this.text = s;
  this.model = model;
  this.parent = parent;
 }

 @Override
 public String toString () {
  return text + ":" + counter;
 }

 @Override
 public Object clone () {
  Node newNode = new Node(text, model, parent);
  newNode.counter = counter;

  children.stream().
     forEach(( n ) -> {
      newNode.children.add((Node) n.clone());
     });

  return newNode;
 }

 public boolean isChildOf ( Node parent ) {
  Node node = this;
  while ( node != null ) {
   if ( node == parent ) {
    return true;
   } else {
    node = node.parent;
   }
  }
  return false;
 }

 List<Node> children = new ArrayList<>();
 String text;
 int counter;
 QTreeModel model;
 Node parent;
}

class NodeRefMimeData extends QMimeData {

 public NodeRefMimeData ( QObject parent ) {
 }
 public Node node;

 @Override
 public String toString () {
  return "NodeRefMimeData(" + node.toString() + ")";
 }
}

/**
 * An example model implementation. It reimplements child(), childCount() and text() to represent the data in a tree of Node's
 */
public class QTreeModel extends com.trolltech.qt.gui.QTreeModel {

 /**
  * Called to query the child of parent at index. If parent is null we have only one child, the root.
  * <p>
  * @param parent
  * @param index
  * <p>
  * @return
  */
 @Override
 public Object child ( Object parent, int index ) {
  if ( parent == null ) {
   return root;
  }
  return ((Node) parent).children.get(index);
 }

 /**
  * Called to query the number of children of the given object or the number of root objects if parent is null.
  * <p>
  * @param parent
  * <p>
  * @return
  */
 @Override
 public int childCount ( Object parent ) {
  int count = parent == null ? 1 : ((Node) parent).children.size();
  return count;
 }

 /**
  * Convenience virtual function to get the textual value of an object. I could also implement icon() for pixmap data or the data() function for other types of roles.
  * <p>
  * @param value
  * <p>
  * @return
  */
 @Override
 public String text ( Object value ) {
  return "" + value;
 }

 @Override
 public Qt.ItemFlags flags ( QModelIndex index ) {
  return defaultFlags;
 }

 /**
  * We implement this to indicate which mimetypes we support...
  * <p>
  * @return
  */
 @Override
 public List<String> mimeTypes () {
  List<String> types = new ArrayList<>();
  types.add("text/plain");
  return types;
 }

 @Override
 public QMimeData mimeData ( List<QModelIndex> list ) {
  if ( list.size() > 0 ) {
   Node node = (Node) indexToValue(list.get(0));
   NodeRefMimeData data = new NodeRefMimeData(this);
   data.node = node;
   data.setText(node.toString());
   return data;
  }
  return null;
 }

 @Override
 public boolean dropMimeData ( QMimeData data, Qt.DropAction action,
                               int row, int col, QModelIndex parentIndex ) {
  if ( data instanceof NodeRefMimeData ) {

   NodeRefMimeData nodeData = (NodeRefMimeData) data;
   Node parent = (Node) indexToValue(parentIndex);
   if ( parent == null ) {
    return false;
   }
   Node child = nodeData.node;

   // Copy...
//             Node cloned = (Node) child.clone();
//             cloned.parent = parent;
//             int pos = parent.children.size();
//             parent.children.add(cloned);
//             childrenInserted(valueToIndex(parent), pos, pos);
//             return true;
   // Move
   if ( parent.isChildOf(child) ) {
    System.out.println("Cannot move parent into child...\n");
    return false;
   }

   Node oldParent = child.parent;
   int oldPos = oldParent.children.indexOf(child);
   oldParent.children.remove(child);
   childrenRemoved(valueToIndex(oldParent), oldPos, oldPos);
   int newPos = parent.children.size();
   parent.children.add(child);
   childrenInserted(valueToIndex(parent), newPos, newPos);
   return true;

  }

  return false;
 }

 public Node root () {
  return root;
 }

 private final Node root = new Node("Root", this, null);

 private static final Qt.ItemFlags defaultFlags
                                   = new Qt.ItemFlags(
       Qt.ItemFlag.ItemIsDragEnabled,
       Qt.ItemFlag.ItemIsDropEnabled,
       Qt.ItemFlag.ItemIsSelectable,
       Qt.ItemFlag.ItemIsEnabled);
}
