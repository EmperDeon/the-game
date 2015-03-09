package mods.basemod.containers;
import java.awt.event.ActionEvent;
import level.*;
import level.player.Player;
import utils.MActionListener;
import utils.containers.ids.Mids;

public class Server {
 private final MActionListener actions = new MActionListener();
 private final Level level = new Level();
 private final LevelGen gen = new LevelGen();
 private final Mids mids = new Mids();
 private final ModsContainer mods = new ModsContainer();
 private final Player player = new Player();
 private final Resources RES = new Resources();
 // public final static Repository REP = new Repository();
 private boolean running = true;
 
 public Mids getMids () {
  return mids;
 }

 public void init () {
  main.Main.SERVER.getActions().addT("loggerExport", 200, ( ActionEvent e ) -> {
   main.Main.LOG.export("now.log");
  });

  mods.init();
 }

 public MActionListener getActions () {
  return actions;
 }

 public Level getLevel () {
  return level;
 }

 public ModsContainer getMods () {
  return mods;
 }

 public Player getPlayer () {
  return this.player;
 }
 
 public Resources getResources(){
  return this.RES;
 }
 
 public LevelGen getWorldGen () {
  return gen;
 }
 
 public boolean isRunning () {
  return running;
 }

 public void setRunning ( boolean b ) {
  this.running = b;
 }

}
