package main;

public class Test {

 public static void test () {
  StackTraceElement st = Thread.currentThread().getStackTrace()[2];
  System.out.println(st.getClassName());
  System.out.println(st);
 }

 public static void main ( String args[] ) {
  test();
  //  String s;
//  try ( BufferedReader fr = new BufferedReader(new FileReader("1.java")) ) {
//   try ( BufferedWriter fw = new BufferedWriter(new FileWriter("2.java")) ) {
//     while ((s = fr.readLine()) != null) {
//     s = "t.write(\"" + s + "\\n\"); \n" ;
//     fw.write(s);
//    }
//     fw.flush();
//   }
//  } catch ( IOException ex ) {
//   
//  }
//  JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//
//  StandardJavaFileManager fileManager = compiler.getStandardFileManager(
//          new DiagnosticCollector<>() , null , null);
//
//  JavaCompiler.CompilationTask task = compiler.getTask(null , fileManager ,
//                                                       new DiagnosticCollector<>() ,
//                                                       Arrays.
//                                                       asList("-d" ,
//                                                              main.Main.mdir + "tmp/mods/test1/") ,
//                                                       null , fileManager.
//                                                       getJavaFileObjectsFromStrings(
//                                                               Arrays.asList(
//                                                                       "test.java")));
//
//  boolean success = task.call();
//  System.out.println("Success: " + success);
//  JTextField modname = new JTextField();
//  modname.setText("test");
//
//  String srcdir = main.Main.mdir + "src/mods/" + modname.getText() + "/";
//  File d = new File(srcdir);
//  d.mkdirs();
  /*
   *
   * try ( FileWriter t = new FileWriter(new File(d , "TextMod.java")) ) {
   * t.write("package mods."+modname.getText()+";\n");
   * t.write("\n");
   * t.write("import java.io.File;\n");
   * t.write("import java.io.IOException;\n");
   * t.write("import java.net.URL;\n");
   * t.write("import java.net.URLClassLoader;\n");
   * t.write("import mods.basemod.IItem;\n");
   * t.write("import mods.basemod.LevBlock;\n");
   * t.write("import mods.basemod.containers.Mid;\n");
   * t.write("import mods.basemod.containers.ModsContainer;\n");
   * t.write("import mods.basemod.interfaces.BaseMod;\n");
   * t.write("import utils.Unzipper;\n");
   * t.write("import utils.json.JSONObject;\n");
   * t.write("\n");
   * t.write("public class TextMod implements BaseMod {\n");
   * t.write("\n");
   * t.write(" private final Mid id;\n");
   * t.write(" private final Boolean props = false;\n");
   * t.write(" private final JSONObject mod;\n");
   * t.write(" private final JSONObject ibc;\n");
   * t.write(" private final String cl;\n");
   * t.write(" private final boolean isEmpty;\n");
   * t.write("\n");
   * t.write(" public TextMod ( String file ) {\n");
   * t.write(" Unzipper.unzipmod(file);\n");
   * t.
   * write(" mod = new JSONObject(main.Main.mdir + \"tmp/\" + file.substring(file.\n");
   * t.
   * write(" lastIndexOf(\"/\") + 1 , file.lastIndexOf(\".mod\")) + \"/mod.json\");\n");
   * t.write(" \n");
   * t.write(" isEmpty = mod.getBoolean(\"isEmpty\");\n");
   * t.write(" id = new Mid(mod.getString(\"name\"));\n");
   * t.write(" // cl = mod.getString(\"class\");\n");
   * t.write(" cl = null;\n");
   * t.write(" if ( isEmpty ) {\n");
   * t.write(" ibc = null;\n");
   * t.write(" } else {\n");
   * t.
   * write(" ibc = new JSONObject(main.Main.mdir + \"tmp/\" + file.substring(file.\n");
   * t.
   * write(" lastIndexOf(\"/\") + 1 , file.lastIndexOf(\".mod\")) + \"/ibc.json\");\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" @Override\n");
   * t.write(" public boolean isClass () {\n");
   * t.write(" return false;\n");
   * t.write(" // return !cl.isEmpty();\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" @Override\n");
   * t.write(" public boolean isEmpty () {\n");
   * t.write(" return isEmpty;\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" @Override\n");
   * t.write(" public TextMod get ( File zip ) {\n");
   * t.write(" if(isClass())\n");
   * t.write(" try {\n");
   * t.write(" URLClassLoader classLoader = new URLClassLoader(\n");
   * t.write(" new URL[]{zip.toURI().toURL()});\n");
   * t.
   * write(" TextMod b = ( TextMod ) classLoader.loadClass(cl).newInstance();\n");
   * t.write(" return b;\n");
   * t.
   * write(" } catch ( IOException | IllegalArgumentException | ClassNotFoundException |\n");
   * t.
   * write(" InstantiationException | IllegalAccessException e ) {\n");
   * t.write(" main.Main.LOG.addE(\"Containers.loadDir()\" , e);\n");
   * t.write(" }\n");
   * t.write(" return this;\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" @Override\n");
   * t.write(" public void init ( ModsContainer c ) {\n");
   * t.write(" JSONObject t;\n");
   * t.write(" if ( !isEmpty ) {\n");
   * t.write(" for ( int i = 0 ; i < mod.getInt(\"Blocks\") ; i++ ) {\n");
   * t.write(" t = ibc.getJSONObject(\"Block\" + i);\n");
   * t.write(" c.put(new LevBlock(mod.getString(\"name\") , t));\n");
   * t.
   * write(" main.Main.LOG.addI(\"mods.containers.ModsContainer.loadDir\" , \"Loaded block\");\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" for ( int i = 0 ; i < mod.getInt(\"Items\") ; i++ ) {\n");
   * t.write(" t = ibc.getJSONObject(\"Item\" + i);\n");
   * t.write(" c.put(new IItem(mod.getString(\"name\") , t));\n");
   * t.
   * write(" main.Main.LOG.addI(\"mods.containers.ModsContainer.loadDir\" , \"Loaded item\");\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" for ( int i = 0 ; i < mod.getInt(\"Crafts\") ; i++ ) {\n");
   * t.write(" t = ibc.getJSONObject(\"Craft\" + i);\n");
   * t.write(" c.putCraft(t.getInt(\"Type\") ,\n");
   * t.write(" t.getString(\"Grid\") ,\n");
   * t.write(" t.getString(\"Elements\")\n");
   * t.write(" );\n");
   * t.
   * write(" main.Main.LOG.addI(\"mods.containers.ModsContainer.loadDir\" , \"Loaded craft\");\n");
   * t.write(" }\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" //put actions to modact.java, item.java, block.java \n");
   * t.write(" ModAct.putAll(c);\n");
   * t.write(" ItemAct.putAll(c);\n");
   * t.write(" BlockAct.putAll(c);\n");
   * t.
   * write(" //Ex. c.addAction(id of Block/Item (Mid) , id of action(String) , () -> { action } (Action)); () -> { } is a lambda expreesion\n");
   * t.
   * write(" //Or c.addAction(Mid , action (String) , ( int act , boolean shift ) -> { action } (Action)); for multiaction with mouse\n");
   * t.write(" \n");
   * t.write(" c.initF(id);\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" @Override\n");
   * t.write(" public void postinit ( ModsContainer c ) {\n");
   * t.write(" \n");
   * t.write(" c.postinitF(id);\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" @Override\n");
   * t.write(" public Mid getId () {\n");
   * t.write(" return this.id;\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" @Override\n");
   * t.write(" public boolean isProps () {\n");
   * t.write(" return false;\n");
   * t.write(" }\n");
   * t.write("\n");
   * t.write(" @Override\n");
   * t.write(" public void reinit ( ModsContainer c ) {\n");
   * t.write(" \n");
   * t.write(" \n");
   * t.write(" }\n");
   * t.write("}\n");
   * t.flush();
   * } catch ( IOException ex ) {
   *
   * }
   *
   * try ( FileWriter t = new FileWriter(new File(d , "ModAct.java")) ) {
   * t.write("package mods." + modname.getText() + ";\n");
   * t.write("import mods.basemod.containers.ModsContainer;\n");
   * t.write("public class ModAct {\n");
   * t.write("public static void putAll(ModsContainer c){\n");
   * t.write("//put actions\n");
   * t.write("}\n");
   * t.write("}\n");
   * } catch ( IOException ex ) {
   *
   * }
   * try ( FileWriter t = new FileWriter(new File(d , "ItemAct.java")) ) {
   * t.write("package mods." + modname.getText() + ";\n");
   * t.write("import mods.basemod.containers.ModsContainer;\n");
   * t.write("public class ItemAct {\n");
   * t.write("public static void putAll(ModsContainer c){\n");
   * t.write("//put actions\n");
   * t.write("}\n");
   * t.write("}\n");
   * } catch ( IOException ex ) {
   *
   * }
   * try ( FileWriter t = new FileWriter(new File(d , "BlockAct.java")) ) {
   * t.write("package mods." + modname.getText() + ";\n");
   * t.write("import mods.basemod.containers.ModsContainer;\n");
   * t.write("public class BlockAct {\n");
   * t.write("public static void putAll(ModsContainer c){\n");
   * t.write("//put actions\n");
   * t.write("}\n");
   * t.write("}\n");
   * } catch ( IOException ex ) {
   *
   * }
   */
//   ArrayList<String> src = new ArrayList<>();
//   src.add(srcdir + "TextMod.java");
//   src.add(srcdir + "ModAct.java");
//   src.add(srcdir + "ItemAct.java");
//   src.add(srcdir + "BlockAct.java");
//
//   ArrayList<String> arg = new ArrayList<>();
//   arg.add("-d");
//   arg.add(main.Main.mdir+"tmp/"+modname.getText()+"/");
//   arg.add("-classpath");
//   String t = main.Main.mdir + "game.jar";
////   for ( File e : new File(main.Main.mdir + "lib/").listFiles() ) {
////    t += ";" + e.getAbsolutePath();
////   }
//   arg.add(t);
//   
//   JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//   DiagnosticCollector<JavaFileObject> coll = new DiagnosticCollector<>();
//   
//   StandardJavaFileManager fileManager = compiler.getStandardFileManager(
//           coll , null , null);
//
//   JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager ,
//                                                        coll,
//                                                        arg ,
//                                                        null , fileManager.
//                                                        getJavaFileObjectsFromStrings(
//                                                                src));
//
//   boolean success = task.call();
//   System.out.println("Success: " + success);
//
//   coll.getDiagnostics().stream().
//          forEach(( e ) -> {
//           System.out.println(e.getMessage(Locale.ENGLISH));
//  });
 }
}
