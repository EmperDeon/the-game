package mods.basemod.interfaces;

import mods.basemod.containers.Mid;
import mods.basemod.containers.ModsContainer;

public interface CoreMod {

 public Boolean needparam = false;
 public Mid id = null;

 public void init ( ModsContainer c );

 public void postinit ( ModsContainer c );

}
