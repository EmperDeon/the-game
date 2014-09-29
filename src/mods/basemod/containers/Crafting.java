package mods.basemod.containers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Crafting implements Serializable {

 public static Integer CRAFT1_1 = 1;
 public static Integer CRAFT1_2 = 2;
 public static Integer CRAFT2_2 = 3;
 public static Integer CRAFT3_3 = 4;
 public static Integer CRAFTCUS = -1;
 
 private final TreeMap<Mid , CraftingE> ccont = new TreeMap<>();
 private final TreeMap<Mid , MultiC> mcont = new TreeMap<>();

 public void addAll ( Crafting c ) {

 }

 public void addC11 ( Mid id ) {

 }

 public class CraftingE {

  private final Integer id;
  private final ArrayList<CraftE> cont = new ArrayList<>();

  public CraftingE ( Integer id , CraftE... e ) {
   this.id = id;
   this.cont.addAll(Arrays.asList(e));
  }

  public Integer getId () {
   return id;
  }

  public ArrayList<CraftE> getCont () {
   return cont;
  }

 }

 private class MultiC {

 }

 public class CraftE {

  private final Mid e;
  private final Integer id;

  public CraftE ( Integer id , Mid e ) {
   this.e = e;
   this.id = id;
  }

  public Mid getE () {
   return e;
  }

  public Integer getId () {
   return id;
  }

 }
}
