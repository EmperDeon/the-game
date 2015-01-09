package mods.basemod;

import java.io.Serializable;

public class Resource implements Serializable {

 enum Type {

  Model, Sound
 }
 protected final String url;
 protected final Rid id;
 protected final Type type;

 public Resource ( Rid id , Type type , String url ) {
  this.id = id;
  this.url = url;
  this.type = type;
 }

 public String getUrl () {
  return url;
 }

 public Rid getId () {
  return id;
 }

 public Type getType () {
  return type;
 }

}
