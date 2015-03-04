package main.dev;

import com.trolltech.qt.gui.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.tools.*;
import main.Main;
import mods.basemod.*;
import mods.basemod.containers.Mid;
import mods.basemod.resources.Model;
import utils.*;
import utils.json.JSONObject;

public final class ModEditor extends QMainWindow {

 public ModEditor () {
  QWidget mainW = new QWidget();
  QVBoxLayout mainLayout = new QVBoxLayout();
  QHBoxLayout panel = new QHBoxLayout();

  tabs = new QTabWidget(this);
  itab = new ItemsTab();
  ctab = new CraftTab();
  bgen = new QPushButton(("Generate"));
  bsave = new QPushButton(("Save mod"));
  modname = new QLineEdit();

  tabs.addTab(itab, ("Items"));
  tabs.addTab(ctab, ("Crafts"));

  panel.addWidget(new QLabel("Modname:"));
  panel.addWidget(modname);
  panel.addSpacerItem(new QSpacerItem(10, 10));
  panel.addWidget(bgen);
  panel.addWidget(bsave);

  bsave.clicked.connect(this, "save()");
  bgen.clicked.connect(this, "gen()");
  
  mainLayout.addLayout(panel);
  mainLayout.addWidget(tabs);
  mainW.setLayout(mainLayout);

  setCentralWidget(mainW);
 }

 private final class ItemsTab extends QWidget {

  public final QLineEdit iid;
  public final QLineEdit sid;
  public final QLineEdit model;
  public final QTableWidget view;
  public final QTableWidget prop;
  private final QPushButton badd;
  private final QPushButton padd;
  private final QLineEdit pkey;
  private final QLineEdit pval;
  //private final PropMap map;
  public final List<IItem> list = new ArrayList<>();
  private final Map<String, String> pmap = new TreeMap<>();

  public ItemsTab () {
   QVBoxLayout main = new QVBoxLayout();
   QHBoxLayout form3 = new QHBoxLayout();
   QFormLayout form = new QFormLayout();
   QVBoxLayout form2 = new QVBoxLayout();
   QHBoxLayout form4 = new QHBoxLayout();

   view = new QTableWidget();
   prop = new QTableWidget();
   iid = new QLineEdit();
   sid = new QLineEdit();
   model = new QLineEdit();
   badd = new QPushButton("Add item");
   padd = new QPushButton("Add");
   pkey = new QLineEdit();
   pval = new QLineEdit();
//map = new PropMap();

   view.insertColumn(0);
   view.setColumnWidth(0, 40);
   view.insertColumn(1);
   view.setColumnWidth(1, 40);
   view.insertColumn(2);
   view.setColumnWidth(2, 500);
   view.insertColumn(3);
   view.setColumnWidth(3, 500);

   view.setHorizontalHeaderLabels(Arrays.asList(new String[]{ "Item id", "Sub-item id", "Model", "Parameters" }));

   prop.insertColumn(0);
   prop.setColumnWidth(0, 150);
   prop.insertColumn(1);
   prop.setColumnWidth(1, 200);

   prop.setHorizontalHeaderLabels(Arrays.asList(new String[]{ "Key", "Value" }));
   prop.setMaximumSize(800, 90);
   prop.setMinimumSize(100, 90);

   padd.clicked.connect(this, "padd()");
   badd.clicked.connect(this, "add()");

   form2.addWidget(prop);
   form4.addWidget(pkey);
   form4.addWidget(pval);
   form4.addWidget(padd);
   form2.addLayout(form4);

   form.addRow("Item id:", iid);
   form.addRow("SUb-item id:", sid);
   form.addRow("Model:", model);
   form.addRow(badd);

   form3.addLayout(form);
   form3.addLayout(form2);

   main.addLayout(form3);
   main.addWidget(view);

   setLayout(main);
  }

  public void add ( IItem e ) {
   list.add(e);
   int row = list.size() - 1;
   view.insertRow(row);
   view.setItem(row, 0, new QTableWidgetItem(e.getId().getIid()));
   view.setItem(row, 1, new QTableWidgetItem(e.getId().getSid()));
   view.setItem(row, 2, new QTableWidgetItem(e.getModel().getFile()));
   view.setItem(row, 3, new QTableWidgetItem(e.getAllP()));
  }

  public void add ( String iid, String sid, String model,
                    Map<String, String> params ) {
   add(new IItem(new Mid(modname.text(), iid, sid), new Model(new Mid(modname.
                 text(), iid, sid), model), params));
  }

  public void add () {
   add(this.iid.text(), this.sid.text(), this.model.text(), this.pmap);
  }

  public void padd () {
   this.pmap.put(this.pkey.text(), this.pval.text());
   int row = this.pmap.size() - 1;
   this.prop.insertRow(row);
   this.prop.setItem(row, 0, new QTableWidgetItem(this.pkey.text()));
   this.prop.setItem(row, 1, new QTableWidgetItem(this.pval.text()));
  }

  public int lsize () {
   return this.list.size();
  }

  public boolean isEmpty () {
   return list.isEmpty();
  }

  public void save ( JSONObject o ) {
      JSONObject t = new JSONObject();
   IItem e;
   for ( int i = 0 ; i < list.size() ; i++ ) {
    e = list.get(i);
    e.toJSON(t);
    o.put("Item" + i , t);
    t.clear();
   }
  }
 }

 private final class CraftTab extends QWidget {

  public final QLineEdit type;
  public final QLineEdit elements;
  public final QLineEdit size;
  public final QTableWidget view;
  public final QTableWidget prop;
  private final QPushButton badd;
  private final QPushButton padd;
  private final QLineEdit pkey;
  public final List<CraftE> list = new ArrayList<>();
  public final List<String> pmap = new ArrayList<>();
  
  public CraftTab () {
   QVBoxLayout main = new QVBoxLayout();
   QHBoxLayout form3 = new QHBoxLayout();
   QFormLayout form = new QFormLayout();
   QVBoxLayout form2 = new QVBoxLayout();
   QHBoxLayout form4 = new QHBoxLayout();
   
   view = new QTableWidget();
   type = new QLineEdit();
   elements = new QLineEdit();
   size = new QLineEdit();
   badd = new QPushButton("Add craft");
   prop = new QTableWidget();
   padd = new QPushButton("Add");
   pkey = new QLineEdit();
   
   view.insertColumn(0);
   view.setColumnWidth(0, 40);
   view.insertColumn(1);
   view.setColumnWidth(1, 40);
   view.insertColumn(2);
   view.setColumnWidth(2, 500);
   view.insertColumn(3);
   view.setColumnWidth(3, 500);

   view.setHorizontalHeaderLabels(Arrays.asList(new String[]{ "Type", "Grid", "Elements", "Parameters" }));

   prop.insertColumn(0);
   prop.setColumnWidth(0, 300);

   prop.setHorizontalHeaderLabels(Arrays.asList(new String[]{ "Key"}));
   prop.setMaximumSize(800, 90);
   prop.setMinimumSize(100, 90);
   
   padd.clicked.connect(this, "padd()");
   badd.clicked.connect(this, "add()");
   
   form2.addWidget(prop);
   form4.addWidget(pkey);
   form4.addWidget(padd);
   form2.addLayout(form4);
   
   form.addRow("Type:", type);
   form.addRow("Elements: ", elements);
   form.addRow("Grid:", size);
   form.addRow(badd);
   
   form3.addLayout(form);
   form3.addLayout(form2);

   main.addLayout(form3);
   main.addWidget(view);

   setLayout(main);
  }

  public void add ( CraftE e ) {
   list.add(e);
   int row = list.size() - 1;
   view.insertRow(row);
   view.setItem(row, 0, new QTableWidgetItem(e.getType().toString()));
   view.setItem(row, 1, new QTableWidgetItem(e.getGrid()));
   view.setItem(row, 2, new QTableWidgetItem(e.getElements()));
   view.setItem(row, 3, new QTableWidgetItem(e.getParams()));
  }

  public void add ( Integer type, String grid, String elements , List param) {
   add(new CraftE(type, grid, elements, param));
  }

  public void add ( Integer type, String grid, String elements ) {
   add(new CraftE(type, grid, elements));
  }
  
  public void add () {
   add(Integer.parseInt(type.text()), size.text(), elements.text(), pmap);
  }

  public void padd () {
   this.pmap.add(this.pkey.text());
   int row = this.pmap.size() - 1;
   this.prop.insertRow(row);
   this.prop.setItem(row, 0, new QTableWidgetItem(this.pkey.text()));
  }
  
  public int lsize () {
   return this.list.size();
  }

  public boolean isEmpty () {
   return list.isEmpty();
  }

  public void save ( JSONObject o ) {
   JSONObject t = new JSONObject();
   CraftE e;
   for ( int i = 0 ; i < list.size() ; i++ ) {
    e = list.get(i);
    t.put("Type" , e.getType());
    t.put("Grid" , e.getGrid());
    t.put("Elements" , e.getElements());
    o.put("Craft" + i , t);
    t.clear();
   }
  }
 }

 private final ItemsTab itab;
 private final CraftTab ctab;
 private final QTabWidget tabs;
 private final QLineEdit modname;
 private final QPushButton bsave;
 private final QPushButton bgen;

 public void save () {
  String t = null;
  JSONMod s = new JSONMod();
  int x = JOptionPane.showConfirmDialog(null,
                                        "Are you have a mod archive or mod folder ?",
                                        "Mod archive",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
  switch ( x ) {
   case 0:
    JFileChooser f = new JFileChooser(Main.DIR + "mods/");
    if ( f.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
     t = f.getSelectedFile().getAbsolutePath();
    }
    Unzipper.unzipmod(t);
    s.save(main.Main.DIR + "tmp/" + modname.text() + "/");
    Zipper.zipmod(t);
    break;
   case 1:
    t = main.Main.DIR + "mods/" + modname.text() + ".mod";
    new File(main.Main.DIR + "tmp/" + modname.text() + "/").mkdirs();
    s.save(main.Main.DIR + "tmp/" + modname.text() + "/");
    Zipper.zipmod(t);
    break;
   default:
    break;

  }
 }

 public void gen () {
  for ( int i = 0 ; i < 100000 ; i++ ) {
   itab.add("Block" + i, "0", "file1", new HashMap<>());
   ctab.add(1, "1x1", Integer.toString(i));
  }
  save();
 }
//

 private final class JSONMod {

  public void save ( String dir ) {
   JSONObject mod = new JSONObject();
   JSONObject ibc = new JSONObject();

   mod.put("name", modname.text());
   mod.put("class", "mods." + modname.text() + ".main");
   if ( itab.isEmpty() && ctab.isEmpty() ) {
    mod.put("isEmpty", true);
   } else {
    mod.put("isEmpty", false);
   }

   mod.put("Items", itab.lsize());
   mod.put("Crafts", ctab.lsize());

   itab.save(ibc);
   ctab.save(ibc);

   mod.save(dir + "/mod.json");
   ibc.save(dir + "/ibc.json");

   gen(dir);
  }

  public void gen ( String dir ) {
   String srcdir = main.Main.DIR + "src/mods/" + modname.text() + "/";
   File d = new File(srcdir);
   d.mkdirs();

   try ( FileWriter t = new FileWriter(new File(d, "Mod.java")) ) {
    t.write("package mods." + modname.text() + ";\n");
    t.write("\n");
    t.write("import java.io.File;\n");
    t.write("import java.io.IOException;\n");
    t.write("import java.net.URL;\n");
    t.write("import java.net.URLClassLoader;\n");
    t.write("import mods.basemod.IItem;\n");
    t.write("import mods.basemod.containers.Mid;\n");
    t.write("import mods.basemod.containers.ModsContainer;\n");
    t.write("import mods.basemod.interfaces.BaseMod;\n");
    t.write("import utils.Unzipper;\n");
    t.write("import utils.json.JSONObject;\n");
    t.write("\n");
    t.write("public class Mod implements BaseMod {\n");
    t.write("\n");
    t.write(" private final Mid id;\n");
    t.write(" private final Boolean props = false;\n");
    t.write(" private final JSONObject mod;\n");
    t.write(" private final JSONObject ibc;\n");
    t.write(" private final String cl;\n");
    t.write(" private final boolean isEmpty;\n");
    t.write("\n");
    t.write(" public Mod ( String file ) {\n");
    t.write("  Unzipper.unzipmod(file);\n");
    t.write(
       "  mod = new JSONObject(main.Main.mdir + \"tmp/\" + file.substring(file.\n");
    t.write(
       "          lastIndexOf(\"/\") + 1 , file.lastIndexOf(\".mod\")) + \"/mod.json\");\n");
    t.write("  \n");
    t.write("  isEmpty = mod.getBoolean(\"isEmpty\");\n");
    t.write("  id = new Mid(mod.getString(\"name\"));\n");
    t.write(" // cl = mod.getString(\"class\");\n");
    t.write("  cl = null;\n");
    t.write("  if ( isEmpty ) {\n");
    t.write("   ibc = null;\n");
    t.write("  } else {\n");
    t.write(
       "   ibc = new JSONObject(main.Main.mdir + \"tmp/\" + file.substring(file.\n");
    t.write(
       "           lastIndexOf(\"/\") + 1 , file.lastIndexOf(\".mod\")) + \"/ibc.json\");\n");
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
    t.write(
       "   TextMod b = ( TextMod ) classLoader.loadClass(cl).newInstance();\n");
    t.write("   return b;\n");
    t.write(
       "  } catch ( IOException | IllegalArgumentException | ClassNotFoundException |\n");
    t.write(
       "            InstantiationException | IllegalAccessException e ) {\n");
    t.write("   main.Main.LOG.addE(e);\n");
    t.write("  }\n");
    t.write("  return this;\n");
    t.write(" }\n");
    t.write("\n");
    t.write(" @Override\n");
    t.write(" public void init ( ModsContainer c ) {\n");
    t.write("  JSONObject t;\n");
    t.write("  if ( !isEmpty ) {\n");
    t.write("\n");
    t.write("   for ( int i = 0 ; i < mod.getInt(\"Items\") ; i++ ) {\n");
    t.write("    t = ibc.getJSONObject(\"Item\" + i);\n");
    t.write("    c.put(new IItem(mod.getString(\"name\") , t));\n");
    t.write("    main.Main.LOG.addI(\"Loaded item\");\n");
    t.write("   }\n");
    t.write("\n");
    t.write("   for ( int i = 0 ; i < mod.getInt(\"Crafts\") ; i++ ) {\n");
    t.write("    t = ibc.getJSONObject(\"Craft\" + i);\n");
    t.write("    c.putCraft(t.getInt(\"Type\") ,\n");
    t.write("               t.getString(\"Grid\") ,\n");
    t.write("               t.getString(\"Elements\")\n");
    t.write("    );\n");
    t.write("    main.Main.LOG.addI(\"Loaded craft\");\n");
    t.write("   }\n");
    t.write("  }\n");
    t.write("\n");
    t.write("  //put actions to modact.java, item.java \n");
    t.write("  ModAct.putAll(c);\n");
    t.write("  ItemAct.putAll(c);\n");
    t.write(
       "  //Ex. c.addAction(id of Block/Item (Mid) , id of action(String) , () -> { action } (Action));  () -> {  } is a lambda expreesion\n");
    t.write(
       "  //Or  c.addAction(Mid , action (String) , ( int act , boolean shift ) -> { action } (Action)); for multiaction with mouse\n");
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

   try ( FileWriter t = new FileWriter(new File(d, "ModAct.java")) ) {
    t.write("package mods." + modname.text() + ";\n");
    t.write("import mods.basemod.containers.ModsContainer;\n");
    t.write("public class ModAct {\n");
    t.write("public static void putAll(ModsContainer c){\n");
    t.write("//put actions\n");
    t.write("}\n");
    t.write("}\n");
   } catch ( IOException ex ) {

   }
   try ( FileWriter t = new FileWriter(new File(d, "ItemAct.java")) ) {
    t.write("package mods." + modname.text() + ";\n");
    t.write("import mods.basemod.containers.ModsContainer;\n");
    t.write("public class ItemAct {\n");
    t.write("public static void putAll(ModsContainer c){\n");
    t.write("//put actions\n");
    t.write("}\n");
    t.write("}\n");
   } catch ( IOException ex ) {

   }
   compile(srcdir);
  }

  public void compile ( String dir ) {
   ArrayList<String> src = new ArrayList<>();
   src.add(dir + "Mod.java");
   src.add(dir + "ModAct.java");
   src.add(dir + "ItemAct.java");

   ArrayList<String> arg = new ArrayList<>();
   arg.add("-d");
   arg.add(main.Main.DIR + "tmp/" + modname.text() + "/");
   arg.add("-classpath");
   arg.add(main.Main.DIR + "game.jar");

   JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
   DiagnosticCollector<JavaFileObject> coll = new DiagnosticCollector<>();

   StandardJavaFileManager fileManager = compiler.getStandardFileManager(
      coll, null, null);

   JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                                                        coll,
                                                        arg,
                                                        null, fileManager.
                                                        getJavaFileObjectsFromStrings(
                                                           src));

   boolean success = task.call();
   main.Main.LOG.addI("Mod build " + (success ? "success" : "not success"));

   coll.getDiagnostics().stream().
      forEach(( e ) -> {
       System.out.println(e.getMessage(Locale.ENGLISH));
      });
  }
 }

// private final class ItemsTable implements TableModel {
//
//  private TableModelListener listener;
//
//  private final ArrayList<IItem> items = new ArrayList<>();
//
//  @Override
//  public void addTableModelListener ( TableModelListener listener ) {
//   this.listener = listener;
//  }
//
//  @Override
//  public Class<?> getColumnClass ( int columnIndex ) {
//   return String.class;
//  }
//
//  @Override
//  public int getColumnCount () {
//   return 3;
//  }
//
//  @Override
//  public String getColumnName ( int columnIndex ) {
//   switch ( columnIndex ) {
//    case 0:
//     return "I name";
//    case 1:
//     return "S name";
//    case 2:
//     return "Model";
//   }
//   return "";
//  }
//
//  @Override
//  public int getRowCount () {
//   return items.size();
//  }
//
//  @Override
//  public Object getValueAt ( int rowIndex , int columnIndex ) {
//   switch ( columnIndex ) {
//    case 0:
//     return items.get(rowIndex).getId().getIid();
//    case 1:
//     return items.get(rowIndex).getId().getSid();
//    case 2:
//     return items.get(rowIndex).getModel().getFile();
//   }
//   return "";
//  }
//
//  @Override
//  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
//   return false;
//  }
//
//  @Override
//  public void removeTableModelListener ( TableModelListener listener ) {
//   listener = null;
//  }
//
//  @Override
//  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {
//
//  }
//
//  public void add () {
//   add(new Mid(modname.getText() , iiname.getText() , isname.getText()) ,
//       new Model(
//               new Mid(modname.getText() , iiname.getText() , isname.getText()) ,
//               imodel.getText()) ,
//       ip.getMap());
//   iiname.setText("");
//   isname.setText("");
//   imodel.setText("");
//  }
//
//  public void add ( Mid id , Model model , Map<String , String> map ) {
//   items.add(new IItem(id , model , map));
//   listener.tableChanged(null);
//  }
//
//  public void delete () {
//   int n = itable.getSelectedRow();
//
//   if ( n != -1 ) {
//    items.remove(n);
//   } else {
//    System.out.println("No selected index");
//   }
//   listener.tableChanged(null);
//  }
//
//  public void save ( JSONObject obj ) {
//   JSONObject t = new JSONObject();
//   IItem e;
//   for ( int i = 0 ; i < items.size() ; i++ ) {
//    e = items.get(i);
//    e.toJSON(t);
//    obj.put("Item" + i , t);
//    t.clear();
//   }
//  }
// }
//
// private final class BlocksTable implements TableModel {
//
//  private TableModelListener listener;
//
//  private final ArrayList<LevBlock> items = new ArrayList<>();
//
//  @Override
//  public void addTableModelListener ( TableModelListener listener ) {
//   this.listener = listener;
//  }
//
//  @Override
//  public Class<?> getColumnClass ( int columnIndex ) {
//   return String.class;
//  }
//
//  @Override
//  public int getColumnCount () {
//   return 3;
//  }
//
//  @Override
//  public String getColumnName ( int columnIndex ) {
//   switch ( columnIndex ) {
//    case 0:
//     return "B name";
//    case 1:
//     return "S name";
//    case 2:
//     return "Model";
//   }
//   return "";
//  }
//
//  @Override
//  public int getRowCount () {
//   return items.size();
//  }
//
//  @Override
//  public Object getValueAt ( int rowIndex , int columnIndex ) {
//   switch ( columnIndex ) {
//    case 0:
//     return items.get(rowIndex).getId().getIid();
//    case 1:
//     return items.get(rowIndex).getId().getSid();
//    case 2:
//     return items.get(rowIndex).getModel().getFile();
//   }
//   return "";
//  }
//
//  @Override
//  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
//   return false;
//  }
//
//  @Override
//  public void removeTableModelListener ( TableModelListener listener ) {
//   listener = null;
//  }
//
//  @Override
//  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {
//
//  }
//
//  public void add () {
//   add(new Mid(modname.getText() , bbname.getText() , bsname.getText()) ,
//       new Model(
//               new Mid(modname.getText() , bbname.getText() , bsname.getText()) ,
//               bmodel.getText()) , bp.getMap());
//  }
//
//  public void add ( Mid id , Model model , Map<String , String> map ) {
//   items.add(new LevBlock(id , model , map));
//   listener.tableChanged(null);
//  }
//
//  public void delete () {
//   int n = btable.getSelectedRow();
//
//   if ( n != -1 ) {
//    items.remove(n);
//   } else {
//    System.out.println("No selected index");
//   }
//   listener.tableChanged(null);
//  }
//
//  public void save ( JSONObject obj ) {
//   JSONObject t = new JSONObject();
//   int i = 0;
//   for ( LevBlock e : items ) {
//    e.toJSON(t);
//    obj.put("Block" + i , t.getMap());
//    i++;
//    t.clear();
//   }
//  }
// }
//
// private final class CraftsTable implements TableModel {
//
//  private TableModelListener listener;
//
//  private final ArrayList<CraftE> crafts = new ArrayList<>();
//
//  @Override
//  public void addTableModelListener ( TableModelListener listener ) {
//   this.listener = listener;
//  }
//
//  @Override
//  public Class<?> getColumnClass ( int columnIndex ) {
//   return String.class;
//  }
//
//  @Override
//  public int getColumnCount () {
//   return 3;
//  }
//
//  @Override
//  public String getColumnName ( int columnIndex ) {
//   switch ( columnIndex ) {
//    case 0:
//     return "Type";
//    case 1:
//     return "Crafting Grid";
//    case 2:
//     return "Elements";
//   }
//   return "";
//  }
//
//  @Override
//  public int getRowCount () {
//   return crafts.size();
//  }
//
//  @Override
//  public Object getValueAt ( int rowIndex , int columnIndex ) {
//   switch ( columnIndex ) {
//    case 0:
//     return crafts.get(rowIndex).getType();
//    case 1:
//     return crafts.get(rowIndex).getGrid();
//    case 2:
//     return crafts.get(rowIndex).getElements();
//   }
//   return "";
//  }
//
//  @Override
//  public boolean isCellEditable ( int rowIndex , int columnIndex ) {
//   return false;
//  }
//
//  @Override
//  public void removeTableModelListener ( TableModelListener listener ) {
//   listener = null;
//  }
//
//  @Override
//  public void setValueAt ( Object value , int rowIndex , int columnIndex ) {
//
//  }
//
//  public void add () {
//   add(Integer.parseInt(ctype.getText()) , cgrid.getText() , celem.getText());
//  }
//
//  public void add ( Integer type , String grid , String elements ) {
//   crafts.add(new CraftE(type , grid , elements));
//   listener.tableChanged(null);
//  }
//
//  public void delete () {
//   int n = ctable.getSelectedRow();
//
//   if ( n != -1 ) {
//    crafts.remove(n);
//   } else {
//    System.out.println("No selected index");
//   }
//   listener.tableChanged(null);
//  }
//
//  public void save ( JSONObject obj ) {
//   JSONObject t = new JSONObject();
//   CraftE e;
//   for ( int i = 0 ; i < crafts.size() ; i++ ) {
//    e = crafts.get(i);
//    t.put("Type" , e.getType());
//    t.put("Grid" , e.getGrid());
//    t.put("Elements" , e.getElements());
//    obj.put("Craft" + i , t);
//    t.clear();
//   }
//  }
// }
//
// private final class ParamF extends JFrame {
//
//  private final JLabel pl1 = new JLabel();
//  private final JLabel pl2 = new JLabel();
//  private final JTextField pk = new JTextField();
//  private final JTextField pv = new JTextField();
//  private final JButton padd = new JButton();
//  private final JButton pdel = new JButton();
//  private final JTable ptab = new JTable();
//  private final ParamTable pmod = new ParamTable();
//  private final JScrollPane pscr = new JScrollPane();
//
//  public ParamF ( String str ) {
//   this.setVisible(false);
//   this.setTitle(str + " params editor");
//   this.setLayout(null);
//   this.setBounds(300 , 300 , 500 , 350);
//   this.setResizable(false);
//   this.addWindowListener(new WindowAdapter() {
//    @Override
//    public void windowClosing ( WindowEvent e ) {
//
//    }
//   });
//
//   pl1.setText("Key:");
//   pl1.setBounds(5 , 10 , 50 , 20);
//
//   pk.setBounds(35 , 5 , 100 , 30);
//
//   pl2.setText("Value:");
//   pl2.setBounds(160 , 10 , 50 , 20);
//
//   pv.setBounds(205 , 5 , 100 , 30);
//
//   padd.setText("Add");
//   padd.setBounds(320 , 5 , 80 , 30);
//   padd.addMouseListener(new MouseAdapter() {
//    @Override
//    public void mouseClicked ( MouseEvent evt ) {
//     pmod.add();
//     pk.setText("");
//     pv.setText("");
//    }
//   });
//
//   pdel.setText("Delete");
//   pdel.setBounds(405 , 5 , 80 , 30);
//   pdel.addMouseListener(new MouseAdapter() {
//    @Override
//    public void mouseClicked ( MouseEvent evt ) {
//     pmod.delete(ptab);
//    }
//   });
//
//   ptab.setModel(pmod);
//   ptab.getTableHeader().setReorderingAllowed(false);
//   pscr.setViewportView(ptab);
//   pscr.setBounds(5 , 35 , 490 , 300);
//
//   add(pl1);
//   add(pl2);
//   add(padd);
//   add(pdel);
//   add(pk);
//   add(pv);
//   add(pscr);
//  }
//
//  public Map<String , String> getMap () {
//   return pmod.get();
//  }
//
//  public void clear () {
//   pmod.clear();
//  }
//
//  private final class ParamTable implements TableModel {
//
//   private TableModelListener listener;
//
//   private final ArrayList<Vec2<String>> map = new ArrayList<>();
//
//   @Override
//   public void addTableModelListener ( TableModelListener listener ) {
//    this.listener = listener;
//   }
//
//   @Override
//   public Class<?> getColumnClass ( int columnIndex ) {
//    return String.class;
//   }
//
//   @Override
//   public int getColumnCount () {
//    return 2;
//   }
//
//   @Override
//   public String getColumnName ( int columnIndex ) {
//    switch ( columnIndex ) {
//     case 0:
//      return "Key";
//     case 1:
//      return "Value";
//    }
//    return "";
//   }
//
//   @Override
//   public int getRowCount () {
//    return map.size();
//   }
//
//   @Override
//   public Object getValueAt ( int rowIndex , int columnIndex ) {
//    switch ( columnIndex ) {
//     case 0:
//      return map.get(rowIndex).gX();
//     case 1:
//      return map.get(rowIndex).gY();
//    }
//    return "";
//   }
//
//   @Override
//   public boolean isCellEditable ( int rowIndex , int columnIndex ) {
//    return false;
//   }
//
//   @Override
//   public void removeTableModelListener ( TableModelListener listener ) {
//    listener = null;
//   }
//
//   @Override
//   public void setValueAt ( Object value , int rowIndex , int columnIndex ) {
//
//   }
//
//   public void add () {
//    map.add(new Vec2<>(pk.getText() , pv.getText()));
//    listener.tableChanged(null);
//   }
//
//   public void delete ( JTable tab ) {
//    int n = tab.getSelectedRow();
//
//    if ( n != -1 ) {
//     map.remove(n);
//    } else {
//     System.out.println("No selected index");
//    }
//    listener.tableChanged(null);
//   }
//
//   public Map<String , String> get () {
//    HashMap<String , String> m = new HashMap<>();
//    map.stream().forEach(( e ) -> {
//     m.put(e.gX() , e.gY());
//    });
//    return m;
//   }
//
//   public void clear () {
//    map.clear();
//    listener.tableChanged(null);
//   }
//  }
//
// }
}
