package mods.basemod;

import java.io.Serializable;
import mods.basemod.containers.Mid;

public class Model extends Resource implements Serializable {

 public Model ( Mid id , String file ) {
  super(new Rid(id , "model") , Resource.Type.Model , file);
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
