package mods.basemod.containers;

import java.util.TreeMap;
import mods.basemod.Block;

public class BlocksContainer {
 private final TreeMap<Mid, Block> cont = new TreeMap<>();
 
 public BlocksContainer(){}
 public void addBlock(Mid k, Block v){
  cont.put(k , v);
 }
 public Block getBlock(Mid k){return cont.get(k);}
}
