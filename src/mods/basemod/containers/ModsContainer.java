package mods.basemod.containers;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import static main.Main.LOG;
import mods.basemod.*;
import mods.basemod.interfaces.*;
import utils.containers.id.Mid;

public final class ModsContainer implements Serializable {

 transient final TreeMap<Mid, CoreMod> cmods;
 transient final TreeMap<Mid, BaseMod> mods;
 transient final TreeMap<Mid, BaseMod> dism;
 transient final TreeMap<Mid, CoreMod> disc;
 private final TreeMap<Mid, LevBlock> bcont;
 private final TreeMap<Mid, IItem> icont;
 private final Crafting ccont;
 private final ActMap actmap;
 private final ArrayList<Mid> init = new ArrayList<>();
 private boolean loaded = false;

 private final File file = new File(main.Main.DIR + "mods/container.all");

 public ModsContainer () {
  cmods = new TreeMap<>();
  mods = new TreeMap<>();
  bcont = new TreeMap<>();
  icont = new TreeMap<>();
  ccont = new Crafting();
  actmap = new ActMap();
  dism = new TreeMap<>();
  disc = new TreeMap<>();
 }

 public void add ( Mid id, BaseMod b ) {
  mods.put(id, b);
 }

 public void addAction ( Mid id, String s, Action act ) {
  actmap.add(id, s, act);
 }

 public void addActionU ( Mid id, String s, ActionU act ) {
  actmap.add(id, s, act);
 }
//
// public Tex getITex ( Mid id ) {
//  return icont.getTex(id);
// }
//
// public Tex getBTex ( Mid id ) {
//  return bcont.getTex(id);
// }

 public void put ( Base v ) {
  if ( v instanceof BaseMod ) {
   mods.put(v.getId(), (BaseMod) v);
  } else if ( v instanceof CoreMod ) {
   cmods.put(v.getId(), (CoreMod) v);
  } else if ( v instanceof LevBlock ) {
   bcont.put(v.getId(), (LevBlock) v);
  } else if ( v instanceof IItem ) {
   icont.put(v.getId(), (IItem) v);
  } else {
   LOG.addE("v is not a Base");
  }
 }

 public void disableMod ( String mid ) {
  dism.put(Server.instanceMid(mid), mods.remove(Server.instanceMid(mid)));
  LOG.addI("Disabled mod " + mid);
 }

 public void deleteMod ( String mid ) {
  mods.remove(Server.instanceMid(mid));
  LOG.addI("Deleted mod " + mid);
 }

 public void disableCMod ( String mid ) {
  disc.put(Server.instanceMid(mid), cmods.remove(Server.instanceMid(mid)));
  LOG.addI("Disabled coremod " + mid);
 }

 public void deleteCMod ( String mid ) {
  cmods.remove(Server.instanceMid(mid));
  LOG.addI("Deleted coremod " + mid);
 }

 public void putCraft ( Integer type, String grid, String elements ) {
  ccont.add(type, grid, elements);
 }

 // -----------------------
 public void test () {
  mods.keySet().stream().
     forEach(( m ) -> {
      //      Main.mainform.mmod.add(m.getMid());
     });
 }

 public void init () {
  LOG.addI("Init Started");
  mods.values().stream().forEach(( BaseMod m ) -> {
   m.init(ModsContainer.this);
   init.add(m.getId());
  });
  LOG.addI("Init Ended");
  test();
 }

 public void reinit () {
  LOG.addI("Reinit Started");
  mods.values().stream().forEach(m -> {
   m.reinit(this);
   init.add(m.getId());
  });
  LOG.addI("Reinit Ended");
  test();
 }

 public void postinit () {
  LOG.addI("Postinit Started");
  init.clear();
  mods.values().stream().forEach(( m ) -> {
   m.postinit(this);
   init.add(m.getId());
  });
  LOG.addI("Postinit Ended");
 }

 public void destroy () {

 }

 public void load () {
//  if ( file.exists() ) {
//   fload();
//  } else {
  loadDir(true);
//  }
 }

 public void loadDir ( boolean isI ) {
  TextMod t;
  File e = new File(main.Main.DIR + "mods/");
  File[] s = e.listFiles(pathname -> {
   try {
    if ( pathname.isFile() && pathname.getCanonicalPath().lastIndexOf(".mod") != -1 ) {
     return true;
    }
   } catch ( IOException ex ) {
    LOG.addE(ex);
   }
   return false;
  });
  if ( e.exists() ) {
   for ( File f : s ) {
    t = new TextMod(f.getAbsolutePath());
    if ( t.isClass() ) {
     put(t.get(f));
    } else {
     put(t);
    }
   }
  }

  if ( isI ) {
   init();
  }
 }

//Fast Save, Load
 public void fload () {
  loadDir(false);
  try ( ObjectInputStream o = new ObjectInputStream(new GZIPInputStream(
     new FileInputStream(file))) ) {
   ModsContainer t = (ModsContainer) o.readObject();
   this.bcont.putAll(t.bcont);
   this.ccont.addAll(t.ccont);
   this.icont.putAll(t.icont);
   this.actmap.addAll(t.getActmap());
  } catch ( Exception e ) {
   LOG.addE(e);
   System.out.println(e.toString());
  }
  reinit();
  System.out.println("Loaded " + bcont.size() + " blocks");
 }

 public void fsave () {
  try ( ObjectOutputStream o = new ObjectOutputStream(new GZIPOutputStream(
     new FileOutputStream(file))) ) {
   o.writeObject(this);
   o.flush();
  } catch ( Exception e ) {
   LOG.addE(e);
  }
 }

 public synchronized void initF ( Mid id ) {
  this.init.add(id);
  if ( init.size() == mods.size() ) {
   postinit();
  }
 }

 public synchronized void postinitF ( Mid id ) {
  this.init.add(id);
  if ( init.size() == mods.size() ) {
   loaded = true;
  }
 }

 public boolean isLoaded () {
  return loaded;
 }

 public Crafting getCcont () {
  return ccont;
 }

 public TreeMap<Mid, CoreMod> getCmods () {
  return cmods;
 }

 public TreeMap<Mid, BaseMod> getMods () {
  return mods;
 }

 public ArrayList<Mid> getInit () {
  return init;
 }

 public File getFile () {
  return file;
 }

 public ActMap getActmap () {
  return actmap;
 }

 public TreeMap<Mid, LevBlock> getBcont () {
  return bcont;
 }

 public TreeMap<Mid, IItem> getIcont () {
  return icont;
 }

}
