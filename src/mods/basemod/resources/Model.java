package mods.basemod.resources;

import java.io.Serializable;
import static main.Main.SERVER;
import mods.basemod.*;
import utils.containers.ids.*;

public class Model extends Resource implements Serializable {

 public static Model Model( Rid id, String file){
  if(SERVER.getResources().containsR(id, Type.Model, file))
   return SERVER.getResources().getModel(id, Type.Model, file);
  else
   return new Model(id, file);
 }
 
 public Model (Rid id, Type type, String url){
  super(id, type, url);
 }

 protected Model ( Rid k, String url ) {
  super(k, Resource.Type.Model, url);
 }

 public void load () {

 }

 @Override
 public String toString () {
  return "Model: " + url;
 }

 public String getFile () {
  return url;
 }
}
