package mods.basemod.interfaces;

import java.io.File;
import mods.basemod.containers.ModsContainer;

public interface BaseMod extends Base {

 public void init ( ModsContainer c );

 public void postinit ( ModsContainer c );

 public boolean isProps ();

 public boolean isClass ();

 public boolean isEmpty ();

 public BaseMod get ( File zip );

 public void reinit ( ModsContainer aThis );
}
