package mods.basemod.interfaces;

import mods.basemod.containers.ModsContainer;

public interface BaseMod extends Base {

 public void init ( ModsContainer c );

 public void postinit ( ModsContainer c );

 public boolean isProps ();

 public void reinit ( ModsContainer aThis );
}
