package mods.basemod.interfaces;

import utils.ids.Mid;
import mods.basemod.containers.*;

public interface CoreMod extends Base {

 public Boolean needparam = false;
 public Mid id = null;

 public void init ( ModsContainer c );

 public void postinit ( ModsContainer c );

}
