package level;

import basemod.Block;

public class Chunk {
 public Block[][][] blocks;
 public int id;
 public Chunk(){
blocks=new Block[16][16][128];
//[16][16][128]
}
}