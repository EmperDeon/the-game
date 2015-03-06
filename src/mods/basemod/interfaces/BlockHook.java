package mods.basemod.interfaces;

import utils.ids.Mid;

public interface BlockHook {

 public void blockClicked ( Mid item );

 public void blockDestroyed ( Mid item );

 public void blockPlaced ();

}
