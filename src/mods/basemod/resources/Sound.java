package mods.basemod.resources;

import static main.Main.SERVER;
import mods.basemod.*;
import utils.containers.ids.Rid;

public class Sound extends Resource {

 public static Sound Sound (Rid id, String url){
    if(SERVER.getResources().containsR(id, Type.Sound, url))
   return SERVER.getResources().getSound(id, Type.Sound, url);
  else
   return new Sound(id, url);
 }
 
 public Sound (Rid id, Type type, String url){
  super(id, type, url);
 }
 
 private Sound ( Rid id, String url ) {
  super(id, Type.Sound, url);
 }

}
