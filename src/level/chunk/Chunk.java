package level.chunk;

import java.io.Serializable;
import java.util.ArrayList;
import level.LevBlock;
import render.RendTileCoord;
import utils.Id;

public class Chunk implements Serializable{
 public LevBlock[][][] blocks;
 public int idx;
 public int idy;
 private ArrayList<RendTileCoord> tilerend;
 
 public Chunk(int x,int y){
  this.idx=x;
  this.idy=y;
  gen();
  blocks=new LevBlock[16][16][256];
  //[16][16][128]
 }
 public void heightmap(){
 int mid;int tid;int sid;
 for(int ix=0;ix<15;ix++)
  for(int iy=0;iy<15;iy++)
   for(int iz=0;iz<127;iz++)
    {
     if(blocks[ix][iy][iz] != null){   
     mid=Integer.parseInt(blocks[ix][iy][iz].id.getMid());  
     tid=Integer.parseInt(blocks[ix][iy][iz].id.getBid());
     
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
//
 }
 
 public void gen(){
 blocks=new LevBlock[16][16][256];    
 for(int x = 0;x<16;x++)
  for(int y = 0;y<16;y++)   
   for(int z = 0;z<255;z++)
    if(z<100) 
     blocks[x][y][z] = new LevBlock(new Id("1:1,1"),null);
    else
     blocks[x][y][z] = null;   
       }
 
 public void tick(){
  //
 }
}
