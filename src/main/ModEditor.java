/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import mods.basemod.IItem;
import mods.basemod.LevBlock;
import mods.basemod.Model;
import mods.basemod.Speeds;
import mods.basemod.containers.Mid;

public final class ModEditor extends javax.swing.JFrame {

 public ModEditor () {
  initComponents();
 }

 private void initComponents () {
  this.setVisible(true);
  this.setTitle("Mod Editor");
  this.setLayout(null);
  this.setBounds(300 , 300 , 727 , 440);
  this.setResizable(false);
  this.addWindowListener(new WindowAdapter() {
   @Override
   public void windowClosing ( WindowEvent e ) {
    main.Main.main.destroy();
   }
  });
  this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

  jLabel1.setText("Name:");

  gen.setText("Generate option file for actions");

  save.setText("Save");
  save.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( java.awt.event.MouseEvent evt ) {
    saveMouseClicked(evt);
   }
  });

  jTabbedPane1.setBorder(null);

  jPanel8.setBorder(null);

  btable.setModel(bm);
  btable.getTableHeader().setReorderingAllowed(false);
  jScrollPane2.setViewportView(btable);

  jLabel18.setText("Model name");
  jLabel18.setToolTipText("");

  jLabel19.setText("Dicitionary");

  jLabel20.setText("Speed modifiers");

  jLabel21.setText("Block name");

  jLabel22.setText("Sub name");

  badd.setText("Add");

  bdel.setText("Delete");

  jLabel23.setText("Durability");

  jTabbedPane1.addTab("Blocks" , jPanel8);

  ctable.setModel(cm);

  ctable.getTableHeader().setReorderingAllowed(false);
  jScrollPane3.setViewportView(ctable);

  jLabel14.setText("Type");

  jLabel15.setText("Craft Grid");

  jLabel16.setText("Elements");

  cdel.setText("Delete");

  cadd.setText("Add");

  jTabbedPane1.addTab("Crafting" , jPanel9);

  jLabel2.setText("Item name");

  jLabel3.setText("Sub name");

  jLabel4.setText("Durability");

  jLabel7.setText("Speeds(separate by ;)");

  jLabel6.setText("Type");

  jLabel5.setText("Model");
  jLabel5.setToolTipText("");

  iadd.setText("Add");

  idel.setText("Delete");
  idel.setToolTipText("");

  itable.setModel(im);

  itable.setName(""); // NOI18N
  itable.getTableHeader().setReorderingAllowed(false);
  jScrollPane1.setViewportView(itable);

  jTabbedPane1.addTab("Items" , jPanel7);

  this.add(badd);
  this.add(bbname);
  this.add(bdel);
  this.add(bdict);
  this.add(bdurab);
  this.add(bmodel);
  this.add(bsname);
  this.add(bspeed);
  this.add(btable);
  this.add(cadd);
  this.add(cdel);
  this.add(celem);
  this.add(cgrid);
  this.add(ctable);
  this.add(ctype);
  this.add(gen);
  this.add(iadd);
  this.add(idel);
  this.add(idurab);
  this.add(iiname);
  this.add(imodel);
  this.add(isname);
  this.add(ispeed);
  this.add(itable);
  this.add(itype);
  this.add(jLabel1);
  this.add(jLabel14);
  this.add(jLabel15);
  this.add(jLabel16);
  this.add(jLabel18);
  this.add(jLabel19);
  this.add(jLabel2);
  this.add(jLabel20);
  this.add(jLabel21);
  this.add(jLabel22);
  this.add(jLabel23);
  this.add(jLabel3);
  this.add(jLabel4);
  this.add(jLabel5);
  this.add(jLabel6);
  this.add(jLabel7);
  this.add(jPanel7);
  this.add(jPanel8);
  this.add(jPanel9);
  this.add(jScrollPane1);
  this.add(jScrollPane2);
  this.add(jScrollPane3);
  this.add(jTabbedPane1);
  this.add(modname);
  this.add(save);
 }// </editor-fold>                        

 private void saveMouseClicked ( java.awt.event.MouseEvent evt ) {

 }

 public class ItemsTable implements TableModel {

  private final Set<TableModelListener> listeners = new HashSet<>();

  private final ArrayList<IItem> items = new ArrayList<>();

  @Override
  public void addTableModelListener ( TableModelListener listener ) {
   listeners.add(listener);
  }

  @Override
  public Class<?> getColumnClass ( int columnIndex ) {
   return String.class;
  }

  @Override
  public int getColumnCount () {
   return 6;
  }

  @Override
  public String getColumnName ( int columnIndex ) {
   switch ( columnIndex ) {
    case 0:
     return "I name";
    case 1:
     return "S name";
    case 2:
     return "Durability";
    case 3:
     return "Model";
    case 4:
     return "Type";
    case 5:
     return "Speeds";
   }
   return "";
  }

  @Override
  public int getRowCount () {
   return items.size();
  }

  @Override
  public Object getValueAt ( int rowIndex , int columnIndex ) {
   switch ( columnIndex ) {
    case 0:
     return items.get(rowIndex).getId().getIid();
    case 1:
     return items.get(rowIndex).getId().getSid();
    case 2:
     return items.get(rowIndex).getDurab().toString();
    case 3:
     return items.get(rowIndex).getModel().toString();
    case 4:
     return items.get(rowIndex).getType().toString();
    case 5:
     return items.get(rowIndex).getSpeed().toString();

   }
   return "";
  }

  @Override
  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
   return false;
  }

  @Override
  public void removeTableModelListener ( TableModelListener listener ) {
   listeners.remove(listener);
  }

  @Override
  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {

  }

  public void add () {
   IItem t = new IItem(new Mid(modname.getText() , iiname.getText() , isname.
                               getText()) , Integer.parseInt(idurab.getText()) ,
                       new Model(imodel.getText()) , Integer.parseInt(itype.
                               getText()) , new Speeds(ispeed.getText()));
   this.items.add(t);
  }

 }

 public class BlocksTable implements TableModel {

  private final Set<TableModelListener> listeners = new HashSet<>();

  private final ArrayList<LevBlock> items = new ArrayList<>();

  @Override
  public void addTableModelListener ( TableModelListener listener ) {
   listeners.add(listener);
  }

  @Override
  public Class<?> getColumnClass ( int columnIndex ) {
   return String.class;
  }

  @Override
  public int getColumnCount () {
   return 6;
  }

  @Override
  public String getColumnName ( int columnIndex ) {
   switch ( columnIndex ) {
    case 0:
     return "B name";
    case 1:
     return "S name";
    case 2:
     return "Durability";
    case 3:
     return "Model";
    case 4:
     return "Dictionary";
    case 5:
     return "Speed modifier";
   }
   return "";
  }

  @Override
  public int getRowCount () {
   return items.size();
  }

  @Override
  public Object getValueAt ( int rowIndex , int columnIndex ) {
   switch ( columnIndex ) {
    case 0:
     return items.get(rowIndex).getId().getIid();
    case 1:
     return items.get(rowIndex).getId().getSid();
    case 2:
     return items.get(rowIndex).getDurab().toString();
    case 3:
     return items.get(rowIndex).getModel().toString();
    case 4:
     return items.get(rowIndex).getType().toString();
    case 5:
     return items.get(rowIndex).getSpeed().toString();

   }
   return "";
  }

  @Override
  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
   return false;
  }

  @Override
  public void removeTableModelListener ( TableModelListener listener ) {
   listeners.remove(listener);
  }

  @Override
  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {

  }

  public void add ( LevBlock item ) {
   this.items.add(item);
   this.listeners.stream().forEach(( a ) -> {
    a.tableChanged(null);
   });
   System.out.println(item.toString());
  }

 }

 public class CraftsTable implements TableModel {

  private final Set<TableModelListener> listeners = new HashSet<>();

  private final ArrayList<IItem> crafts = new ArrayList<>();

  @Override
  public void addTableModelListener ( TableModelListener listener ) {
   listeners.add(listener);
  }

  @Override
  public Class<?> getColumnClass ( int columnIndex ) {
   return String.class;
  }

  @Override
  public int getColumnCount () {
   return 3;
  }

  @Override
  public String getColumnName ( int columnIndex ) {
   switch ( columnIndex ) {
    case 0:
     return "Type";
    case 1:
     return "Crafting Grid";
    case 2:
     return "Elements";
   }
   return "";
  }

  @Override
  public int getRowCount () {
   return crafts.size();
  }

  @Override
  public Object getValueAt ( int rowIndex , int columnIndex ) {
   switch ( columnIndex ) {
    case 0:
     return crafts.get(rowIndex).getId().getIid();
    case 1:
     return crafts.get(rowIndex).getId().getSid();
    case 2:
     return crafts.get(rowIndex).getDurab().toString();
    case 3:
     return crafts.get(rowIndex).getModel().toString();
    case 4:
     return crafts.get(rowIndex).getType().toString();
    case 5:
     return crafts.get(rowIndex).getSpeed().toString();

   }
   return "";
  }

  @Override
  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
   return false;
  }

  @Override
  public void removeTableModelListener ( TableModelListener listener ) {
   listeners.remove(listener);
  }

  @Override
  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {

  }

  public void add ( IItem item ) {
   this.crafts.add(item);
  }

 }

 private class ItemsPop extends JPopupMenu {

  private final JMenuItem enab;
  private final JMenuItem disb;
  private final JMenuItem delt;

  public ItemsPop ( JList l ) {

   enab = new JMenuItem("Добавить предмет");
   enab.addActionListener(e -> {

   });

   disb = new JMenuItem("Редактировать выбранный предмет");
   disb.addActionListener(e -> {

   });

   delt = new JMenuItem("Удалить выбранный предмет");
   delt.addActionListener(e -> {

   });

   add(enab);
   add(disb);
   add(delt);
  }
 }

 private final BlocksTable bm = new BlocksTable();
 private final CraftsTable cm = new CraftsTable();
 private final ItemsTable im = new ItemsTable();

 // Variables declaration - do not modify                     
 private final JButton badd = new JButton();
 private final JTextField bbname = new JTextField();
 private final JButton bdel = new JButton();
 private final JTextField bdict = new JTextField();
 private final JTextField bdurab = new JTextField();
 private final JTextField bmodel = new JTextField();
 private final JTextField bsname = new JTextField();
 private final JTextField bspeed = new JTextField();
 private final JTable btable = new JTable();
 private final JButton cadd = new JButton();
 private final JButton cdel = new JButton();
 private final JTextField celem = new JTextField();
 private final JTextField cgrid = new JTextField();
 private final JTable ctable = new JTable();
 private final JTextField ctype = new JTextField();
 private final JButton gen = new JButton();
 private final JButton iadd = new JButton();
 private final JButton idel = new JButton();
 private final JTextField idurab = new JTextField();
 private final JTextField iiname = new JTextField();
 private final JTextField imodel = new JTextField();
 private final JTextField isname = new JTextField();
 private final JTextField ispeed = new JTextField();
 private final JTable itable = new JTable();
 private final JTextField itype = new JTextField();
 private final JLabel jLabel1 = new JLabel();
 private final JLabel jLabel14 = new JLabel();
 private final JLabel jLabel15 = new JLabel();
 private final JLabel jLabel16 = new JLabel();
 private final JLabel jLabel18 = new JLabel();
 private final JLabel jLabel19 = new JLabel();
 private final JLabel jLabel2 = new JLabel();
 private final JLabel jLabel20 = new JLabel();
 private final JLabel jLabel21 = new JLabel();
 private final JLabel jLabel22 = new JLabel();
 private final JLabel jLabel23 = new JLabel();
 private final JLabel jLabel3 = new JLabel();
 private final JLabel jLabel4 = new JLabel();
 private final JLabel jLabel5 = new JLabel();
 private final JLabel jLabel6 = new JLabel();
 private final JLabel jLabel7 = new JLabel();
 private final JPanel jPanel7 = new JPanel();
 private final JPanel jPanel8 = new JPanel();
 private final JPanel jPanel9 = new JPanel();
 private final JScrollPane jScrollPane1 = new JScrollPane();
 private final JScrollPane jScrollPane2 = new JScrollPane();
 private final JScrollPane jScrollPane3 = new JScrollPane();
 private final JTabbedPane jTabbedPane1 = new JTabbedPane();
 private final JTextField modname = new JTextField();
 private final JButton save = new JButton();
 // End of variables declaration                   
}
