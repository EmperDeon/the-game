package level.chunk;

import java.io.Serializable;
import java.util.ArrayList;
import level.LevBlock;
import main.Main;
import render.RendTileCoord;
import render.tile.Tile;

public class Chunk implements Serializable{
 public LevBlock[][][] blocks;
 public int idx;
 public int idy;
 private ArrayList<RendTileCoord> tilerend;
 
 public Chunk(int x,int y){
  this.idx=x;
  this.idy=y;
  blocks=new LevBlock[16][16][128];
  //[16][16][128]
 }
 public void heightmap(){
 int mid;int tid;int sid;
 for(int ix=0;ix<15;ix++)
  for(int iy=0;iy<15;iy++)
   for(int iz=0;iz<127;iz++)
    {
     if(blocks[ix][iy][iz] != null){   
     mid=blocks[ix][iy][iz].modid;  
     tid=blocks[ix][iy][iz].blockid;
     
     if (blocks[ix][iy-1][iz] == null){// 0
      tilerend.add(new RendTileCoord(ix,iy,iz,0,mid,tid));
     }
     if (blocks[ix][iy+1][iz] == null){// 1
      tilerend.add(new RendTileCoord(ix,iy,iz,1,mid,tid));
     }
     if (blocks[ix][iy][iz-1] == null){// 2
      tilerend.add(new RendTileCoord(ix,iy,iz,2,mid,tid));
     }
     if (blocks[ix][iy][iz+1] == null){// 3
      tilerend.add(new RendTileCoord(ix,iy,iz,3,mid,tid));
     }
     if (blocks[ix-1][iy][iz] == null){// 4
      tilerend.add(new RendTileCoord(ix,iy,iz,4,mid,tid));
     }
     if (blocks[ix+1][iy][iz] == null){// 5
      tilerend.add(new RendTileCoord(ix,iy,iz,5,mid,tid));
     }
    }
   }
 }
 
 public void updheightm(){
 
 }
 
 public void Render(){
  Tile tile[][];     

 }
 
 public void gen(){
 // 
 }
 
 public void tick(){
  //
 }
}
