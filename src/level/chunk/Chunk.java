package level.chunk;

import java.io.Serializable;
import java.util.ArrayList;
import level.LevBlock;
import render.RendTexCoord;
import utils.Id;
import utils.TId;
import utils.vec.Vec5i;

public class Chunk implements Serializable{
 public LevBlock[][][] blocks;
 public int idx;
 public int idy;
 public ArrayList<RendTexCoord> Texrend;
 
 public Chunk(int x,int y){
  this.idx=x;
  this.idy=y;
  //gen();
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
      Texrend.add(new RendTexCoord(new Vec5i(ix,iy,iz,idx,idy),0,new TId(mid,tid)));
     }
     if (blocks[ix][iy+1][iz] == null){// 1
      Texrend.add(new RendTexCoord(new Vec5i(ix,iy,iz,idx,idy),1,new TId(mid,tid)));
     }
     if (blocks[ix][iy][iz-1] == null){// 2
      Texrend.add(new RendTexCoord(new Vec5i(ix,iy,iz,idx,idy),2,new TId(mid,tid)));
     }
     if (blocks[ix][iy][iz+1] == null){// 3
      Texrend.add(new RendTexCoord(new Vec5i(ix,iy,iz,idx,idy),3,new TId(mid,tid)));
     }
     if (blocks[ix-1][iy][iz] == null){// 4
      Texrend.add(new RendTexCoord(new Vec5i(ix,iy,iz,idx,idy),4,new TId(mid,tid)));
     }
     if (blocks[ix+1][iy][iz] == null){// 5
      Texrend.add(new RendTexCoord(new Vec5i(ix,iy,iz,idx,idy),5,new TId(mid,tid)));
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
     blocks[x][y][z] = new LevBlock(new Id("1:1:1"),new TId(1,1),null);
    else
     blocks[x][y][z] = null;   
       }
 
 public void tick(){
  //
 }
}
