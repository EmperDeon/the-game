package level.chunk;
 import java.io.Serializable;
import java.util.ArrayList;

public class OctChunk  implements Serializable{
 private final String dir;
 private final Chunk[][] chs;
 private final String fl;
 private final int x;
 private final int y;
 
public OctChunk(String nm,int x,int y){
 this.dir=main.Main1.mdir + "saves/"+nm+"/rg/";
 fl="region"+x+""+y+".rg";
 
 this.x=x;
 this.y=y;

 chs=new Chunk[8][8];
 for(int ix = 0;ix<8;ix++)
  for(int iy = 0;iy<8;iy++) {  
   chs[ix][iy]=new Chunk(x*8+ix,y*8+iy);
  }
}

public OctChunk(String nm,int x,int y,Chunk[][] ch){
 this.dir=main.Main1.mdir + "saves/"+nm+"/rg/";
 fl="region"+x+""+y+".rg";
 
 this.x=x;
 this.y=y;
 this.chs=ch;
}

public void tick(){
for(Chunk[] ch1 : chs)    
 for(Chunk ch : ch1) {
  ch.tick();
 }
}

public String getD(){
 return dir+fl;
}

public Chunk getCh(int x,int y){
 return chs[x][y];
}

public ArrayList<Chunk> getAllCh(){
 ArrayList<Chunk> c = new ArrayList<>();
 for(int cx = 0;cx<8;cx++)   
  for(int cy = 0;cy<8;cy++)
   c.add(chs[cx][cy]);
 return c;
}

public OctChunkId getId(){
 ChunkId[] ids = new ChunkId[64];   
 int i = 0;
 for(Chunk[] chs1 : chs)
  for(Chunk ch : chs1){
   ids[i] = new ChunkId(ch.idx,ch.idy);
   i++;
  } 
 return new OctChunkId("region"+this.x+""+this.y+".rg",ids);
}

public void replaceChunk(int x,int y,Chunk ch){
 this.chs[x][y] = ch;
}
}
