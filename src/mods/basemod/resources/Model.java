package mods.basemod.resources;

import java.io.Serializable;
import mods.basemod.*;
import mods.basemod.containers.Mid;

public class Model extends Resource implements Serializable {

 public Model ( Mid id, String file ) {
  super(new Rid(id, Resource.Type.Model, "model"), Resource.Type.Model, file);
 }

 public Model ( Rid k, String url ) {
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
