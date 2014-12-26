package main;

import java.util.ArrayList;
import java.util.Arrays;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Test {

 public static void main ( String args[] ) {
//  String s;
//  try ( BufferedReader fr = new BufferedReader(new FileReader("1.txt")) ) {
//   try ( BufferedWriter fw = new BufferedWriter(new FileWriter("2.txt")) ) {
//     while ((s = fr.readLine()) != null) {
//     s = "t.write(\"" + s + "\"); \n" ;
//     fw.write(s);
//    }
//     fw.flush();
//   }
//  } catch ( IOException ex ) {
//   Logger.getLogger(Test.class.getName()).log(Level.SEVERE , null , ex);
//  }

  JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

  StandardJavaFileManager fileManager = compiler.getStandardFileManager(
          new DiagnosticCollector<>() , null , null);

  ArrayList<String> arg = new ArrayList<>();
  arg.add("-verbose");
  arg.add("-Xlint");
  arg.add("-d");
  arg.add(main.Main.mdir + "tmp/");

  CompilationTask task = compiler.getTask(null , 
                                          fileManager ,
                                          new DiagnosticCollector<>() ,
                                          arg ,
                                          null , 
                                          fileManager.getJavaFileObjectsFromStrings(Arrays.asList("test.java")));

  boolean success = task.call();
  System.out.println("Success: " + success);

 }
}
