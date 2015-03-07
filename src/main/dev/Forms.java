package main.dev;
import static main.Main.OPTIONS;

public class Forms {
 public ModEditor modeditor;
 public ModelEditor modeleditor;
 public LevelEditor leveleditor;
 public OptionsEditor optionseditor;
 public DevForm devform;

 public Forms () {

 }

 public void init () {
  devform = new DevForm();

  modeditor = new ModEditor();
  //modeleditor = new ModelEditor();
  //leveleditor = new LevelEditor();
  optionseditor = new OptionsEditor();

  if ( OPTIONS.getBoolean("DevMode") ) {
   devform.show();
  }
 }
}
