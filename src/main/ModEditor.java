package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import mods.basemod.CraftE;
import mods.basemod.IItem;
import mods.basemod.LevBlock;
import mods.basemod.Model;
import mods.basemod.containers.Mid;
import utils.Unzipper;
import utils.Zipper;
import utils.json.JSONObject;
import utils.vec.Vec2;

public final class ModEditor extends javax.swing.JFrame {

 public ModEditor () {
  this.setVisible(false);
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

  gen.setText("Generate option file for actions");
  gen.setBounds(427 , 10 , 230 , 30);
  gen.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    gen();
   }
  });
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
  String t = null;
  JSONMod s = new JSONMod();
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
    s.save(main.Main.mdir + "tmp/" + modname.getText() + "/");
    Zipper.zipmod(t);
    break;
   case 1:
    t = main.Main.mdir + "mods/" + modname.getText() + ".mod";
    new File(main.Main.mdir + "tmp/" + modname.getText() + "/").mkdirs();
    s.save(main.Main.mdir + "tmp/" + modname.getText() + "/");
    Zipper.zipmod(t);
    break;
   default:
    break;

  }
 }

 private void gen () {
  for ( int i = 0 ; i < 5000 ; i++ ) {
   bm.add(new Mid("0" , "Block" + i , "0") , new Model("file1") ,
          new HashMap<>());
//   cm.add(i , "1x1" , "1=1");
//   im.add(new Mid("0" , "Item" + i , "0") , 1 , new Model("file1") , 1 ,
//          new Speeds("1,1"));
  }
  save();
 }

 private final class JSONMod {

  public void save ( String dir ) {
   JSONObject mod = new JSONObject();
   JSONObject ibc = new JSONObject();

   mod.put("name" , modname.getText());
   mod.put("class" , "mods." + modname.getText() + ".main");
   if ( bm.items.isEmpty() && im.items.isEmpty() && cm.crafts.isEmpty() ) {
    mod.put("isEmpty" , true);
   } else {
    mod.put("isEmpty" , false);
   }

   mod.put("Blocks" , bm.items.size());
   mod.put("Items" , im.items.size());
   mod.put("Crafts" , cm.crafts.size());

   bm.save(ibc);
   im.save(ibc);
   cm.save(ibc);

   mod.save(dir + "/mod.json");
   ibc.save(dir + "/ibc.json");
   
   gen(dir);
  }

  public void gen ( String dir ) {
   String srcdir = main.Main.mdir + "src/mods/" + modname.getText() + "/";
   File d = new File(srcdir);
   d.mkdirs();

   try ( FileWriter t = new FileWriter(new File(d , "TextMod.java")) ) {
    t.write("package mods." + modname.getText() + ";\n");
    t.write("\n");
    t.write("import java.io.File;\n");
    t.write("import java.io.IOException;\n");
    t.write("import java.net.URL;\n");
    t.write("import java.net.URLClassLoader;\n");
    t.write("import mods.basemod.IItem;\n");
    t.write("import mods.basemod.LevBlock;\n");
    t.write("import mods.basemod.containers.Mid;\n");
    t.write("import mods.basemod.containers.ModsContainer;\n");
    t.write("import mods.basemod.interfaces.BaseMod;\n");
    t.write("import utils.Unzipper;\n");
    t.write("import utils.json.JSONObject;\n");
    t.write("\n");
    t.write("public class TextMod implements BaseMod {\n");
    t.write("\n");
    t.write(" private final Mid id;\n");
    t.write(" private final Boolean props = false;\n");
    t.write(" private final JSONObject mod;\n");
    t.write(" private final JSONObject ibc;\n");
    t.write(" private final String cl;\n");
    t.write(" private final boolean isEmpty;\n");
    t.write("\n");
    t.write(" public TextMod ( String file ) {\n");
    t.write("  Unzipper.unzipmod(file);\n");
    t.
            write("  mod = new JSONObject(main.Main.mdir + \"tmp/\" + file.substring(file.\n");
    t.
            write("          lastIndexOf(\"/\") + 1 , file.lastIndexOf(\".mod\")) + \"/mod.json\");\n");
    t.write("  \n");
    t.write("  isEmpty = mod.getBoolean(\"isEmpty\");\n");
    t.write("  id = new Mid(mod.getString(\"name\"));\n");
    t.write(" // cl = mod.getString(\"class\");\n");
    t.write("  cl = null;\n");
    t.write("  if ( isEmpty ) {\n");
    t.write("   ibc = null;\n");
    t.write("  } else {\n");
    t.
            write("   ibc = new JSONObject(main.Main.mdir + \"tmp/\" + file.substring(file.\n");
    t.
            write("           lastIndexOf(\"/\") + 1 , file.lastIndexOf(\".mod\")) + \"/ibc.json\");\n");
    t.write("  }\n");
    t.write("\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public boolean isClass () {\n");
    t.write("  return false;\n");
    t.write(" // return !cl.isEmpty();\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public boolean isEmpty () {\n");
    t.write("  return isEmpty;\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public TextMod get ( File zip ) {\n");
    t.write("  if(isClass())\n");
    t.write("  try {\n");
    t.write("   URLClassLoader classLoader = new URLClassLoader(\n");
    t.write("           new URL[]{zip.toURI().toURL()});\n");
    t.
            write("   TextMod b = ( TextMod ) classLoader.loadClass(cl).newInstance();\n");
    t.write("   return b;\n");
    t.
            write("  } catch ( IOException | IllegalArgumentException | ClassNotFoundException |\n");
    t.
            write("            InstantiationException | IllegalAccessException e ) {\n");
    t.write("   main.Main.LOG.addE(\"Containers.loadDir()\" , e);\n");
    t.write("  }\n");
    t.write("  return this;\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public void init ( ModsContainer c ) {\n");
    t.write("  JSONObject t;\n");
    t.write("  if ( !isEmpty ) {\n");
    t.write("   for ( int i = 0 ; i < mod.getInt(\"Blocks\") ; i++ ) {\n");
    t.write("    t = ibc.getJSONObject(\"Block\" + i);\n");
    t.write("    c.put(new LevBlock(mod.getString(\"name\") , t));\n");
    t.
            write("    main.Main.LOG.addI(\"mods.containers.ModsContainer.loadDir\" , \"Loaded block\");\n");
    t.write("   }\n");
    t.write("\n");
    t.write("   for ( int i = 0 ; i < mod.getInt(\"Items\") ; i++ ) {\n");
    t.write("    t = ibc.getJSONObject(\"Item\" + i);\n");
    t.write("    c.put(new IItem(mod.getString(\"name\") , t));\n");
    t.
            write("    main.Main.LOG.addI(\"mods.containers.ModsContainer.loadDir\" , \"Loaded item\");\n");
    t.write("   }\n");
    t.write("\n");
    t.write("   for ( int i = 0 ; i < mod.getInt(\"Crafts\") ; i++ ) {\n");
    t.write("    t = ibc.getJSONObject(\"Craft\" + i);\n");
    t.write("    c.putCraft(t.getInt(\"Type\") ,\n");
    t.write("               t.getString(\"Grid\") ,\n");
    t.write("               t.getString(\"Elements\")\n");
    t.write("    );\n");
    t.
            write("    main.Main.LOG.addI(\"mods.containers.ModsContainer.loadDir\" , \"Loaded craft\");\n");
    t.write("   }\n");
    t.write("  }\n");
    t.write("\n");
    t.write("  //put actions to modact.java, item.java, block.java \n");
    t.write("  ModAct.putAll(c);\n");
    t.write("  ItemAct.putAll(c);\n");
    t.write("  BlockAct.putAll(c);\n");
    t.
            write("  //Ex. c.addAction(id of Block/Item (Mid) , id of action(String) , () -> { action } (Action));  () -> {  } is a lambda expreesion\n");
    t.
            write("  //Or  c.addAction(Mid , action (String) , ( int act , boolean shift ) -> { action } (Action)); for multiaction with mouse\n");
    t.write(" \n");
    t.write("  c.initF(id);\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public void postinit ( ModsContainer c ) {\n");
    t.write("  \n");
    t.write("  c.postinitF(id);\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public Mid getId () {\n");
    t.write("  return this.id;\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public boolean isProps () {\n");
    t.write("  return false;\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public void reinit ( ModsContainer c ) {\n");
    t.write("  \n");
    t.write("  \n");
    t.write(" }\n");
    t.write("}\n");
    t.flush();
   } catch ( IOException ex ) {

   }

   try ( FileWriter t = new FileWriter(new File(d , "ModAct.java")) ) {
    t.write("package mods." + modname.getText() + ";\n");
    t.write("import mods.basemod.containers.ModsContainer;\n");
    t.write("public class ModAct {\n");
    t.write("public static void putAll(ModsContainer c){\n");
    t.write("//put actions\n");
    t.write("}\n");
    t.write("}\n");
   } catch ( IOException ex ) {

   }
   try ( FileWriter t = new FileWriter(new File(d , "ItemAct.java")) ) {
    t.write("package mods." + modname.getText() + ";\n");
    t.write("import mods.basemod.containers.ModsContainer;\n");
    t.write("public class ItemAct {\n");
    t.write("public static void putAll(ModsContainer c){\n");
    t.write("//put actions\n");
    t.write("}\n");
    t.write("}\n");
   } catch ( IOException ex ) {

   }
   try ( FileWriter t = new FileWriter(new File(d , "BlockAct.java")) ) {
    t.write("package mods." + modname.getText() + ";\n");
    t.write("import mods.basemod.containers.ModsContainer;\n");
    t.write("public class BlockAct {\n");
    t.write("public static void putAll(ModsContainer c){\n");
    t.write("//put actions\n");
    t.write("}\n");
    t.write("}\n");
   } catch ( IOException ex ) {
    main.Main.LOG.addE(ex);
   }
   compile(srcdir);   
  }

  public void compile(String dir){
   ArrayList<String> src = new ArrayList<>();
   src.add(dir + "TextMod.java");
   src.add(dir + "ModAct.java");
   src.add(dir + "ItemAct.java");
   src.add(dir + "BlockAct.java");

   ArrayList<String> arg = new ArrayList<>();
   arg.add("-d");
   arg.add(main.Main.mdir + "tmp/" + modname.getText() + "/");
   arg.add("-classpath");
   arg.add(main.Main.mdir + "game.jar");

   JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
   DiagnosticCollector<JavaFileObject> coll = new DiagnosticCollector<>();

   StandardJavaFileManager fileManager = compiler.getStandardFileManager(
           coll , null , null);

   JavaCompiler.CompilationTask task = compiler.getTask(null , fileManager ,
                                                        coll ,
                                                        arg ,
                                                        null , fileManager.
                                                        getJavaFileObjectsFromStrings(
                                                                src));

   boolean success = task.call();
   System.out.println("Success: " + success);

   coll.getDiagnostics().stream().
           forEach(( e ) -> {
            System.out.println(e.getMessage(Locale.ENGLISH));
           });
  }
 }

 private final class ItemsTable implements TableModel {

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
   return 3;
  }

  @Override
  public String getColumnName ( int columnIndex ) {
   switch ( columnIndex ) {
    case 0:
     return "I name";
    case 1:
     return "S name";
    case 2:
     return "Model";
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
     return items.get(rowIndex).getModel().getFile();
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
   add(new Mid(modname.getText() , iiname.getText() , isname.getText()) ,
       new Model(imodel.getText()) ,
       ip.getMap());
   iiname.setText("");
   isname.setText("");
   imodel.setText("");
  }

  public void add ( Mid id , Model model , Map<String , String> map ) {
   items.add(new IItem(id , model , map));
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
   JSONObject t = new JSONObject();
   IItem e;
   for ( int i = 0 ; i < items.size() ; i++ ) {
    e = items.get(i);
    e.toJSON(t);
    obj.put("Item" + i , t);
    t.clear();
   }
  }
 }

 private final class BlocksTable implements TableModel {

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
   return 3;
  }

  @Override
  public String getColumnName ( int columnIndex ) {
   switch ( columnIndex ) {
    case 0:
     return "B name";
    case 1:
     return "S name";
    case 2:
     return "Model";
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
     return items.get(rowIndex).getModel().getFile();
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
   add(new Mid(modname.getText() , bbname.getText() , bsname.getText()) ,
       new Model(bmodel.getText()) , bp.getMap());
  }

  public void add ( Mid id , Model model , Map<String , String> map ) {
   items.add(new LevBlock(id , model , map));
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
   JSONObject t = new JSONObject();
   int i = 0;
   for ( LevBlock e : items ) {
    e.toJSON(t);
    obj.put("Block" + i , t.getMap());
    i++;
    t.clear();
   }
  }
 }

 private final class CraftsTable implements TableModel {

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
   JSONObject t = new JSONObject();
   CraftE e;
   for ( int i = 0 ; i < crafts.size() ; i++ ) {
    e = crafts.get(i);
    t.put("Type" , e.getType());
    t.put("Grid" , e.getGrid());
    t.put("Elements" , e.getElements());
    obj.put("Craft" + i , t);
    t.clear();
   }
  }
 }

 private final class ParamF extends JFrame {

  private final JLabel pl1 = new JLabel();
  private final JLabel pl2 = new JLabel();
  private final JTextField pk = new JTextField();
  private final JTextField pv = new JTextField();
  private final JButton padd = new JButton();
  private final JButton pdel = new JButton();
  private final JTable ptab = new JTable();
  private final ParamTable pmod = new ParamTable();
  private final JScrollPane pscr = new JScrollPane();

  public ParamF ( String str ) {
   this.setVisible(false);
   this.setTitle(str + " params editor");
   this.setLayout(null);
   this.setBounds(300 , 300 , 500 , 350);
   this.setResizable(false);
   this.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing ( WindowEvent e ) {

    }
   });

   pl1.setText("Key:");
   pl1.setBounds(5 , 10 , 50 , 20);

   pk.setBounds(35 , 5 , 100 , 30);

   pl2.setText("Value:");
   pl2.setBounds(160 , 10 , 50 , 20);

   pv.setBounds(205 , 5 , 100 , 30);

   padd.setText("Add");
   padd.setBounds(320 , 5 , 80 , 30);
   padd.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked ( MouseEvent evt ) {
     pmod.add();
     pk.setText("");
     pv.setText("");
    }
   });

   pdel.setText("Delete");
   pdel.setBounds(405 , 5 , 80 , 30);
   pdel.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked ( MouseEvent evt ) {
     pmod.delete(ptab);
    }
   });

   ptab.setModel(pmod);
   ptab.getTableHeader().setReorderingAllowed(false);
   pscr.setViewportView(ptab);
   pscr.setBounds(5 , 35 , 490 , 300);

   add(pl1);
   add(pl2);
   add(padd);
   add(pdel);
   add(pk);
   add(pv);
   add(pscr);
  }

  public Map<String , String> getMap () {
   return pmod.get();
  }

  public void clear () {
   pmod.clear();
  }

  private final class ParamTable implements TableModel {

   private TableModelListener listener;

   private final ArrayList<Vec2<String>> map = new ArrayList<>();

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
    return 2;
   }

   @Override
   public String getColumnName ( int columnIndex ) {
    switch ( columnIndex ) {
     case 0:
      return "Key";
     case 1:
      return "Value";
    }
    return "";
   }

   @Override
   public int getRowCount () {
    return map.size();
   }

   @Override
   public Object getValueAt ( int rowIndex , int columnIndex ) {
    switch ( columnIndex ) {
     case 0:
      return map.get(rowIndex).gX();
     case 1:
      return map.get(rowIndex).gY();
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
    map.add(new Vec2<>(pk.getText() , pv.getText()));
    listener.tableChanged(null);
   }

   public void delete ( JTable tab ) {
    int n = tab.getSelectedRow();

    if ( n != -1 ) {
     map.remove(n);
    } else {
     System.out.println("No selected index");
    }
    listener.tableChanged(null);
   }

   public Map<String , String> get () {
    HashMap<String , String> m = new HashMap<>();
    map.stream().forEach(( e ) -> {
     m.put(e.gX() , e.gY());
    });
    return m;
   }

   public void clear () {
    map.clear();
    listener.tableChanged(null);
   }
  }

 }

 private final ParamF bp = new ParamF("Block");
 private final ParamF ip = new ParamF("Item");
// private final ParamF cp = new ParamF();

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
