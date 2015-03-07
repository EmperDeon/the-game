package mods.basemod.containers;
import level.Level;
import utils.MActionListener;

public class Server {
 private final MActionListener actions = new MActionListener();
 private final Level level = new Level();
 private final ModsContainer mods = new ModsContainer();
 // public final static Resources RES = new Resources();
 // public final static Repository REP = new Repository();
 private boolean running = true;

 public MActionListener getActions () {
  return actions;
 }

 public Level getLevel () {
  return level;
 }

 public ModsContainer getMods () {
  return mods;
 }

 public boolean isRunning () {
  return running;
 }

}
