/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import mods.basemod.CraftE;
import mods.basemod.IItem;
import mods.basemod.LevBlock;
import mods.basemod.Model;
import mods.basemod.Speeds;
import mods.basemod.containers.Mid;
import utils.Unzipper;
import utils.Zipper;
import utils.json.JSONObject;

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

  jLabel1.setText("Name:");
  jLabel1.setBounds(15 , 15 , 50 , 20);

//  gen.setText("Generate option file for actions");
//  gen.setBounds(427 , 10 , 230 , 30);
//  gen.addMouseListener(new MouseAdapter() {
//   @Override
//   public void mouseClicked ( MouseEvent evt ) {
//    gen();
//   }
//  });
  save.setText("Save");
  save.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( java.awt.event.MouseEvent evt ) {
    save();
   }
  });
  save.setBounds(660 , 10 , 60 , 30);

  modname.setBounds(70 , 10 , 130 , 30);

  jTabbedPane1.setBorder(null);
  jTabbedPane1.setBounds(5 , 40 , 720 , 435);

  jPanel7.setLayout(null);
  btable.setModel(bm);
  btable.getTableHeader().setReorderingAllowed(false);
  jScrollPane1.setViewportView(btable);
  jScrollPane1.setBounds(5 , 105 , 707 , 235);

  jLabel21.setText("Block name");
  jLabel21.setBounds(5 , 10 , 100 , 20);
  bbname.setBounds(95 , 5 , 125 , 30);

  jLabel22.setText("Sub name");
  jLabel22.setBounds(5 , 45 , 100 , 20);
  bsname.setBounds(95 , 40 , 125 , 30);

  jLabel23.setText("Durability");
  jLabel23.setBounds(5 , 80 , 100 , 20);
  bdurab.setBounds(95 , 75 , 125 , 30);

  jLabel18.setText("Model name");
  jLabel18.setBounds(280 , 10 , 100 , 20);
  bmodel.setBounds(385 , 5 , 125 , 30);

  jLabel19.setText("Dicitionary");
  jLabel19.setBounds(280 , 45 , 100 , 20);
  bdict.setBounds(385 , 40 , 125 , 30);

  jLabel20.setText("Speed modifiers");
  jLabel20.setBounds(280 , 80 , 120 , 20);
  bspeed.setBounds(385 , 75 , 125 , 30);

  badd.setText("Add");
  badd.setBounds(615 , 5 , 100 , 30);
  badd.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    bm.add();
   }
  });

  bdel.setText("Delete");
  bdel.setBounds(615 , 40 , 100 , 30);
  bdel.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    bm.delete();
   }
  });

  jPanel8.setLayout(null);
  ctable.setModel(cm);
  ctable.getTableHeader().setReorderingAllowed(false);
  jScrollPane2.setViewportView(ctable);
  jScrollPane2.setBounds(5 , 40 , 707 , 300);

  jLabel14.setText("Type");
  jLabel14.setBounds(5 , 10 , 40 , 20);
  ctype.setBounds(40 , 5 , 50 , 30);

  jLabel15.setText("Craft Grid");
  jLabel15.setBounds(110 , 10 , 60 , 20);
  cgrid.setBounds(175 , 5 , 50 , 30);

  jLabel16.setText("Elements");
  jLabel16.setBounds(235 , 10 , 60 , 20);
  celem.setBounds(300 , 5 , 270 , 30);

  cdel.setText("Delete");
  cdel.setBounds(647 , 5 , 70 , 30);
  cdel.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    cm.delete();
   }
  });

  cadd.setText("Add");
  cadd.setBounds(577 , 4 , 70 , 30);
  cadd.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    cm.add();
   }
  });

  jPanel9.setLayout(null);

  itable.setModel(im);
  itable.getTableHeader().setReorderingAllowed(false);
  jScrollPane3.setViewportView(itable);
  jScrollPane3.setBounds(5 , 105 , 707 , 235);

  jLabel2.setText("Item name");
  jLabel2.setBounds(5 , 10 , 100 , 20);
  iiname.setBounds(95 , 5 , 125 , 30);

  jLabel3.setText("Sub name");
  jLabel3.setBounds(5 , 45 , 100 , 20);
  isname.setBounds(95 , 40 , 125 , 30);

  jLabel4.setText("Durability");
  jLabel4.setBounds(5 , 80 , 100 , 20);
  idurab.setBounds(95 , 75 , 125 , 30);

  jLabel5.setText("Model");
  jLabel5.setBounds(280 , 10 , 100 , 20);
  imodel.setBounds(385 , 5 , 125 , 30);

  jLabel6.setText("Type");
  jLabel6.setBounds(280 , 45 , 100 , 20);
  itype.setBounds(385 , 40 , 125 , 30);

  jLabel7.setText("Speed modifiers");
  jLabel7.setBounds(280 , 80 , 120 , 20);
  ispeed.setBounds(385 , 75 , 125 , 30);

  iadd.setText("Add");
  iadd.setBounds(615 , 5 , 100 , 30);
  iadd.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    im.add();
   }
  });

  idel.setText("Delete");
  idel.setBounds(615 , 40 , 100 , 30);
  idel.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    im.delete();
   }
  });

  jPanel7.add(badd);
  jPanel7.add(bbname);
  jPanel7.add(bdel);
  jPanel7.add(bdict);
  jPanel7.add(bdurab);
  jPanel7.add(bmodel);
  jPanel7.add(bsname);
  jPanel7.add(bspeed);
  jPanel7.add(jScrollPane1);
  jPanel7.add(jLabel18);
  jPanel7.add(jLabel19);
  jPanel7.add(jLabel20);
  jPanel7.add(jLabel21);
  jPanel7.add(jLabel22);
  jPanel7.add(jLabel23);

  jPanel8.add(cadd);
  jPanel8.add(cdel);
  jPanel8.add(celem);
  jPanel8.add(cgrid);
  jPanel8.add(jScrollPane2);
  jPanel8.add(ctype);
  jPanel8.add(jLabel14);
  jPanel8.add(jLabel15);
  jPanel8.add(jLabel16);

  jPanel9.add(iadd);
  jPanel9.add(idel);
  jPanel9.add(idurab);
  jPanel9.add(iiname);
  jPanel9.add(imodel);
  jPanel9.add(isname);
  jPanel9.add(ispeed);
  jPanel9.add(jScrollPane3);
  jPanel9.add(itype);
  jPanel9.add(jLabel3);
  jPanel9.add(jLabel4);
  jPanel9.add(jLabel5);
  jPanel9.add(jLabel6);
  jPanel9.add(jLabel7);
  jPanel9.add(jLabel2);

  jTabbedPane1.addTab("Blocks" , jPanel7);
  jTabbedPane1.addTab("Crafts" , jPanel8);
  jTabbedPane1.addTab("Items" , jPanel9);

  this.add(gen);
  this.add(jLabel1);
  this.add(jTabbedPane1);
  this.add(modname);
  this.add(save);

  this.repaint();
 }

 private void save () {
  JSONObject s = new JSONObject();
  String t = null;
  s.put("name" , modname.getText());
  bm.save(s);
  cm.save(s);
  im.save(s);
//  JFileChooser f = new JFileChooser(Main.mdir+"mods/");
//  if ( f.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
//   t = f.getSelectedFile().getAbsolutePath();
//  }

  int x = JOptionPane.showConfirmDialog(null ,
                                        "Are you have a mod archive or mod folder ?" ,
                                        "Mod archive" ,
                                        JOptionPane.YES_NO_OPTION ,
                                        JOptionPane.QUESTION_MESSAGE);
  switch ( x ) {
   case 0:
    JFileChooser f = new JFileChooser(Main.mdir + "mods/");
    if ( f.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
     t = f.getSelectedFile().getAbsolutePath();
    }
    Unzipper.unzipmod(t);
    s.savemod(t);
    new File(t).delete();
    Zipper.zipmod(t);
    break;
   case 1:
    t = main.Main.mdir+"mods/"+modname.getText()+".zip";
    new File(main.Main.mdir+"tmp/"+modname.getText()+"/").mkdirs();
    s.savemod(t);
    Zipper.zipmod(t);
    break;
   default:
    break;
   
  }
//  Unzipper.unzipmod(t.substring(t.lastIndexOf("/")+1, t.lastIndexOf(".zip")));
//  s.save("tmp"+t.substring(t.lastIndexOf("/"), t.lastIndexOf(".zip"))+"/"+modname.getText()+"properties.mod");
//  new File(t).delete();
//  Zipper.zip("tmp"+t.substring(t.lastIndexOf("/"), t.lastIndexOf(".zip"))+"/", t);
 }

// private void gen () {
//  for(int i = 0 ; i<1000000 ; i++){
//   bm.add(new Mid("0","Block"+i,"0"), 1, new Model("file1"), new Speeds("1,1"), "block"+i);
//   cm.add(i,"1x1","1=1");
//   im.add(new Mid("0","Item"+i,"0"), 1, new Model("file1"), 1, new Speeds("1,1"));
//  }
//  save();
// }
 public class ItemsTable implements TableModel {

  private TableModelListener listener;

  private final ArrayList<IItem> items = new ArrayList<>();

  @Override
  public void addTableModelListener ( TableModelListener listener ) {
   this.listener = listener;
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
     return items.get(rowIndex).getDurab();
    case 3:
     return items.get(rowIndex).getModel().getFile();
    case 4:
     return items.get(rowIndex).getType();
    case 5:
     return items.get(rowIndex).getSpeed().getStr();

   }
   return "";
  }

  @Override
  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
   return false;
  }

  @Override
  public void removeTableModelListener ( TableModelListener listener ) {
   listener = null;
  }

  @Override
  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {

  }

  public void add () {
   add(new Mid(modname.getText() , iiname.getText() , isname.
               getText()) , Integer.parseInt(idurab.getText()) ,
       new Model(imodel.getText()) , Integer.parseInt(itype.
               getText()) , new Speeds(ispeed.getText()));
  }

  public void add ( Mid id , Integer durab , Model model , Integer type ,
                    Speeds speeds ) {
   items.add(new IItem(id , durab , model , type , speeds));
   listener.tableChanged(null);
  }

  public void delete () {
   int n = itable.getSelectedRow();

   if ( n != -1 ) {
    items.remove(n);
   } else {
    System.out.println("No selected index");
   }
   listener.tableChanged(null);
  }

  public void save ( JSONObject obj ) {
   obj.put("Items" , items.size());
   JSONObject t = new JSONObject();
   IItem e;
   for ( int i = 0 ; i < items.size() ; i++ ) {
    e = items.get(i);
    t.put("Iid" , e.getId().getIid());
    t.put("Sid" , e.getId().getSid());
    t.put("Durab" , e.getDurab());
    t.put("Model" , e.getModel().getFile());
    t.put("Type" , e.getType());
    t.put("Speed" , e.getSpeed().getStr());
    obj.put("Item" + i , t);
   }
  }
 }

 public class BlocksTable implements TableModel {

  private TableModelListener listener;

  private final ArrayList<LevBlock> items = new ArrayList<>();

  @Override
  public void addTableModelListener ( TableModelListener listener ) {
   this.listener = listener;
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
     return items.get(rowIndex).getDurab();
    case 3:
     return items.get(rowIndex).getModel().getFile();
    case 4:
     return items.get(rowIndex).getDictionary();
    case 5:
     return items.get(rowIndex).getSpeed().getStr();

   }
   return "";
  }

  @Override
  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
   return false;
  }

  @Override
  public void removeTableModelListener ( TableModelListener listener ) {
   listener = null;
  }

  @Override
  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {

  }

  public void add () {
   add(new Mid(modname.getText() , bbname.getText() ,
               bsname.getText()) , Integer.parseInt(
               bdurab.getText()) , new Model(bmodel.
               getText()) , new Speeds(bspeed.getText()) ,
       bdict.getText());
  }

  public void add ( Mid id , Integer durab , Model model , Speeds speeds ,
                    String dict ) {
   items.add(new LevBlock(id , durab , model , speeds , dict));
   listener.tableChanged(null);
  }

  public void delete () {
   int n = btable.getSelectedRow();

   if ( n != -1 ) {
    items.remove(n);
   } else {
    System.out.println("No selected index");
   }
   listener.tableChanged(null);
  }

  public void save ( JSONObject obj ) {
   obj.put("Blocks" , items.size());
   JSONObject t = new JSONObject();
   LevBlock e;
   for ( int i = 0 ; i < items.size() ; i++ ) {
    e = items.get(i);
    t.put("Iid" , e.getId().getIid());
    t.put("Sid" , e.getId().getSid());
    t.put("Durab" , e.getDurab());
    t.put("Model" , e.getModel().getFile());
    t.put("Dict" , e.getDictionary());
    t.put("Speed" , e.getSpeed().getStr());
    obj.put("Block" + i , t);
   }
  }
 }

 public class CraftsTable implements TableModel {

  private TableModelListener listener;

  private final ArrayList<CraftE> crafts = new ArrayList<>();

  @Override
  public void addTableModelListener ( TableModelListener listener ) {
   this.listener = listener;
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
     return crafts.get(rowIndex).getType();
    case 1:
     return crafts.get(rowIndex).getGrid();
    case 2:
     return crafts.get(rowIndex).getElements();
   }
   return "";
  }

  @Override
  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
   return false;
  }

  @Override
  public void removeTableModelListener ( TableModelListener listener ) {
   listener = null;
  }

  @Override
  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {

  }

  public void add () {
   add(Integer.parseInt(ctype.getText()) , cgrid.getText() , celem.getText());
  }

  public void add ( Integer type , String grid , String elements ) {
   crafts.add(new CraftE(type , grid , elements));
   listener.tableChanged(null);
  }

  public void delete () {
   int n = ctable.getSelectedRow();

   if ( n != -1 ) {
    crafts.remove(n);
   } else {
    System.out.println("No selected index");
   }
   listener.tableChanged(null);
  }

  public void save ( JSONObject obj ) {
   obj.put("Crafts" , crafts.size());
   JSONObject t = new JSONObject();
   CraftE e;
   for ( int i = 0 ; i < crafts.size() ; i++ ) {
    e = crafts.get(i);
    t.put("Type" , e.getType());
    t.put("Grid" , e.getGrid());
    t.put("Elements" , e.getElements());
    obj.put("Craft" + i , t);
   }
  }
 }

 private final BlocksTable bm = new BlocksTable();
 private final CraftsTable cm = new CraftsTable();
 private final ItemsTable im = new ItemsTable();
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
}
