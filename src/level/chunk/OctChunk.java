package level.chunk;
 import java.io.Serializable;
import java.util.ArrayList;
import utils.Options;

public class OctChunk  implements Serializable{
 private final String dir;
 private Chunk[][] chs;
 private final String fl;
 private final int x;
 private final int y;
 public  final int[][][] chids = new int[8][8][2];
 
public OctChunk(String nm,int x,int y,Options opt){
 this.dir="game/saves/"+nm+"/rg/";
 fl="region"+x+""+y+".rg";
 
 this.x=x;
 this.y=y;

 chs=new Chunk[8][8];
 for(int ix = 0;ix<8;ix++)
  for(int iy = 0;iy<8;iy++) {  
   chids[ix][iy][0]=(x*8+ix);
   chids[ix][iy][1]=(y*8+iy);
   chs[ix][iy]=new Chunk(x*8+ix,y*8+iy);
  }
}

public OctChunk(String nm,int x,int y,Chunk[][] ch){
 this.dir="game/saves/"+nm+"/rg/";
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

public int[][][] getNom(){
     return this.chids;
}

public ArrayList<Chunk> getAllCh(){
 ArrayList<Chunk> c = new ArrayList();
 for(int cx = 0;cx<8;cx++)   
  for(int cy = 0;cy<8;cy++)
   c.add(chs[cx][cy]);
 return c;
}
}
