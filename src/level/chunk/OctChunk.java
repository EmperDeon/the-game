package level.chunk;
 import java.io.Serializable;

public class OctChunk  implements Serializable{
 private final String dir;
 private final Chunk[] chs;
 private final String fl;
 
public OctChunk(String wr,String s){
 this.dir="game/"+wr+"/rg";
 fl=s+".rg";
 chs=new Chunk[64];
 for(int i = 0;i<64;i++)
  chs[i]=new Chunk(i,i);
 
}

public void tick(){
 for(Chunk ch : chs) {
  ch.tick();
 }
}

public String getF(){
 return fl;
}

public String getD(){
 return dir;
}
}
