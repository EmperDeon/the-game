package main;

import java.util.TreeMap;
import utils.json.JSONObject;

public class Test {

 public static void main ( String args[] ) {
  JSONObject o = new JSONObject();
  TreeMap<String , String> t = new TreeMap<>();
  t.put("Test1" , "Test1");
  t.put("Test2" , "Test2");
  o.put("testobj" , t);
  o.save("obj.txt");
 }

 /**
  * Save this JSONObject to file.
  *
  * @param file
  *             Saving file .
  */
}
