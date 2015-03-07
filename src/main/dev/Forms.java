package main.dev;
import static main.Main.OPTIONS;
import utils.Translator;

public class Forms {
 public ModEditor modeditor;
 public ModelEditor modeleditor;
 public LevelEditor leveleditor;
 public OptionsEditor optionseditor;
 public DevForm devform;
 public Translator TRANSLATE;

 public Forms () {
  TRANSLATE = new Translator();
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
