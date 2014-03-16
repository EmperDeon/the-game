package level.chunk;
 import java.io.Serializable;
import utils.Options;

public class OctChunk  implements Serializable{
 private final String dir;
 private final Chunk[][] chs;
 private final String fl;
 
public OctChunk(String wr,int x,int y,Options opt){
 this.dir="game/saves/"+wr+"/rg/";
 fl="region"+x+""+y+".rg";
 
 String s="OctChunk"+x+y+":";

 chs=new Chunk[8][8];
 for(int ix = 0;ix<8;ix++)
  for(int iy = 0;iy<8;iy++) {  
   if(ix==8&&iy==8) 
    s+=(x*8+ix)+"."+(y*8+iy);
   else 
    s+=(x*8+ix)+"."+(y*8+iy)+",";
   chs[ix][iy]=new Chunk(x*8+ix,y*8+iy);
  }
 opt.add(s);
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
}
