package mods.basemod.containers;
import java.awt.event.ActionEvent;
import level.*;
import level.player.Player;
import main.Main;
import mods.basemod.Resource;
import mods.basemod.Resource.Type;
import utils.MActionListener;
import utils.containers.ids.*;
import utils.containers.pos.BlockPos;

public class Server {
 private final MActionListener actions = new MActionListener();
 private final Level level = new Level();
 private final LevelGen gen = new LevelGen();
 private final Mids mids = new Mids();
 private final BlockIds bids = new BlockIds();
 private final ModsContainer mods = new ModsContainer();
 private final Player player = new Player();
 private final Resources RES = new Resources();
 // public final static Repository REP = new Repository();
 private boolean running = true;

 public BlockIds getBids () {
  return bids;
 }

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

 public Resources getResources () {
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

 // static generation
 public static Mid instanceMid ( String m ) {
  if ( Main.SERVER.getMids().contains(m, "", "") ) {
   return Main.SERVER.getMids().get(m, "", "");
  } else {
   Mid id = new Mid(m);
   Main.SERVER.getMids().put(id);
   return id;
  }
 }
 public static Mid instanceMid ( String m, String i ) {
  if ( Main.SERVER.getMids().contains(m, i, "") ) {
   return Main.SERVER.getMids().get(m, i, "");
  } else {
   Mid id = new Mid(m, i);
   Main.SERVER.getMids().put(id);
   return id;
  }
 }
 public static Mid instanceMid ( String m, String i, String s ) {
  if ( Main.SERVER.getMids().contains(m, i, s) ) {
   return Main.SERVER.getMids().get(m, i, s);
  } else {
   Mid id = new Mid(m, i, s);
   Main.SERVER.getMids().put(id);
   return id;
  }
 }

 public static BlockPos instanceBlockPos ( int x, int y, int z ) {
  if ( Main.SERVER.getBids().contains(x, y, z) ) {
   return Main.SERVER.getBids().get(x, y, z);
  } else {
   BlockPos pos = new BlockPos(x, y, z);
   Main.SERVER.getBids().put(pos);
   return pos;
  }
 }

 public static Rid instanceRid ( String s ) {
  return Server.instanceRid(s.split(":"));
 }

 public static Rid instanceRid ( String[] s ) {
  return instanceRid(instanceMid(s[0], s[1], s[2]), Resource.getType(s[3]), s[4]);
 }

 public static Rid instanceRid ( Mid mid, Type type, String id ) {
  if ( Main.SERVER.getResources().containsI(mid, type, id) ) {
   return Main.SERVER.getResources().getRid(mid, type, id);
  } else {
   Rid id2 = new Rid(mid, type, id);
   Main.SERVER.getResources().putRid(id2);
   return id2;
  }
 }
 
 
}
