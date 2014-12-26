package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextField;

public class Test {

 public static void main ( String args[] ) {
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
//                                                              main.Main.mdir + "tmp/") ,
//                                                       null , fileManager.
//                                                       getJavaFileObjectsFromStrings(
//                                                               Arrays.asList(
//                                                                       "test.java")));
//
//  boolean success = task.call();
//  System.out.println("Success: " + success);
  JTextField modname = new JTextField();
  modname.setText("test");
  File d = new File(main.Main.mdir + "src/mods/" + modname.getText() + "/");
  d.mkdirs();

  try ( FileWriter t = new FileWriter(new File(d , "main.java")) ) {
   t.write("package mods.basemod;\n");
   t.write("\n");
   t.write("import java.io.File;\n");
   t.write("import java.io.IOException;\n");
   t.write("import java.net.URL;\n");
   t.write("import java.net.URLClassLoader;\n");
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
   t.write("  //put actions\n");
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

  try ( FileWriter t = new FileWriter(new File(d , "modact.java")) ) {
   t.write("package mods." + modname.getText() + ";\n");
   t.write("public static class ModAct {\n");
   t.write("public static void putAll(ModsContainer c){\n");
   t.write("//put actions\n");
   t.write("}\n");
   t.write("}\n");
  } catch ( IOException ex ) {

  }
  try ( FileWriter t = new FileWriter(new File(d , "itemact.java")) ) {
   t.write("package mods." + modname.getText() + ";\n");
   t.write("public static class ItemAct {\n");
   t.write("public static void putAll(ModsContainer c){\n");
   t.write("//put actions\n");
   t.write("}\n");
   t.write("}\n");
  } catch ( IOException ex ) {

  }
  try ( FileWriter t = new FileWriter(new File(d , "blockact.java")) ) {
   t.write("package mods." + modname.getText() + ";\n");
   t.write("public static class BlockAct {\n");
   t.write("public static void putAll(ModsContainer c){\n");
   t.write("//put actions\n");
   t.write("}\n");
   t.write("}\n");
  } catch ( IOException ex ) {

  }
//  
 }
}
