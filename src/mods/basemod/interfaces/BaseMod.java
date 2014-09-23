package mods.basemod.interfaces;

import mods.basemod.containers.Mid;
import mods.basemod.containers.ModsContainer;

public interface BaseMod {

 public void init ( ModsContainer c );

 public void postinit ( ModsContainer c );

 public Mid getId ();

 public boolean isProps ();
}
