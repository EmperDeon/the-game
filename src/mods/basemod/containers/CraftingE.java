package mods.basemod.containers;

import java.util.ArrayList;
import java.util.Arrays;

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
