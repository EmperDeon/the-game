package mods.basemod.resources;

import mods.basemod.*;
import utils.containers.id.Rid;

public class Sound extends Resource {

 @Deprecated
 public Sound ( Rid id, Type type, String url ) {
  super(id, type, url);
 }

 @Deprecated
 public Sound ( Rid id, String url ) {
  super(id, Type.Sound, url);
 }

}
