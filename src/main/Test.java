package main;

import java.util.Arrays;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

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

  JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

  StandardJavaFileManager fileManager = compiler.getStandardFileManager(
          new DiagnosticCollector<>() , null , null);

  JavaCompiler.CompilationTask task = compiler.getTask(null , fileManager ,
                                                       new DiagnosticCollector<>() ,
                                                       Arrays.
                                                       asList("-d" ,
                                                              main.Main.mdir + "tmp/mods/test1/") ,
                                                       null , fileManager.
                                                       getJavaFileObjectsFromStrings(
                                                               Arrays.asList(
                                                                       "test.java")));

  boolean success = task.call();
  System.out.println("Success: " + success);

 }
}
