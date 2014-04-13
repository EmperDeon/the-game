package basemod;

import java.util.ArrayList;
import utils.TId;
import render.Tex;

public class ModContainer {
 private final ArrayList< BaseMod > cont;
 private final String dir;
 
 public ModContainer(){
  this.cont=new ArrayList();
  this.dir = main.Main.mdir + "mods/";
 }
 
 public ModContainer(String dir,BaseMod b){
  this.cont=new ArrayList();
  this.cont.add(b);
  this.dir=dir;
 }
 
 public void add(BaseMod b){
  cont.add(b);
 }
 
 public Tex getTex(TId id){
  return cont.get(id.getMid()).getTex(id);
 }
 
 public void init(){
 
 }
 
 public void postinit(){
 
 }
 
 public void destroy(){
 
 }
 
//Fast Save, Load
 
 public void load(){
 
 }
 
 public void save(){
 
 }
}
