package mods.basemod;

import mods.basemod.containers.Mid;
import mods.basemod.containers.ModsContainer;
import mods.basemod.interfaces.BaseMod;
import utils.json.JSONObject;

public class TextMod implements BaseMod{
 private final Mid id;
 private Boolean props = false;
 private final String file; 
 private final JSONObject json = new JSONObject();
 public TextMod (String file) {
  this.file = file;
  this.json.put("", "");
  
  id =  new Mid(9,0,0);
 }
 
 @Override
 public void init ( ModsContainer c ) {
  
 }

 @Override
 public void postinit ( ModsContainer c ) {}

 @Override
 public Mid getId () {return this.id;}

 @Override
 public boolean isProps () {return false;}

 @Override
 public void reinit ( ModsContainer c ) {
 
 }

}
