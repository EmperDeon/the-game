package mods.basemod.resources;

import utils.containers.id.Rid;
import java.io.Serializable;
import mods.basemod.*;
import utils.containers.ids.*;

public class Model extends Resource implements Serializable {

 @Deprecated
 public Model ( Rid id, Type type, String url ) {
  super(id, type, url);
 }
 
 @Deprecated
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
