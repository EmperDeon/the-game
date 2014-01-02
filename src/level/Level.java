package level;
import basemod.Block;
import java.util.ArrayList;
import opengl.Phys;
public class Level {
 public Chunk[] chunk;  
 //public level lvl;
 public String i;
 public int daln=8;
 public int colch=((daln*2)+1)*((daln*2)+1);
 public int width;
 public int depth=128;
 public int height;
 public int[] spawn;
 public int upd;
 public Level(){
daln=8;// 361 chunk    
colch=((daln*2)+1)*((daln*2)+1);
depth=128;
width=((daln*2)+1)*16;
height=width;
load();
}
public final void load(){
chunk=new Chunk[colch];
spawn=new int[4];
int chx=0;spawn[0]=chx;
int chy=0;spawn[1]=chy;
int x=0;spawn[2]=x;
int y=0;spawn[3]=y;
int z=0;spawn[4]=z;
}
public void save(){
//
}
public void replace(int x,int y,int z,Block a,int ch){
chunk[ch].blocks[x][y][z]=a;
}

public boolean isTile(int ch,int x, int y, int z)
  {
    if ((x < 0) || (y < 0) || (z < 0) || (x >= this.width) || (y >= this.depth) || (z >= this.height)) return false;
  //  return this.chunk[ch].blocks[x][y][z] == 1;
    return true;
  }

public ArrayList<Phys> getCubes(Phys aABB)
  {
    ArrayList aABBs = new ArrayList();
    int x0 = (int)aABB.x0;
    int x1 = (int)(aABB.x1 + 1.0F);
    int y0 = (int)aABB.y0;
    int y1 = (int)(aABB.y1 + 1.0F);
    int z0 = (int)aABB.z0;
    int z1 = (int)(aABB.z1 + 1.0F);

    if (x0 < 0) x0 = 0;
    if (y0 < 0) y0 = 0;
    if (z0 < 0) z0 = 0;
    if (x1 > this.width) x1 = this.width;
    if (y1 > this.depth) y1 = this.depth;
    if (z1 > this.height) z1 = this.height;

   for(int ch=1;ch<colch;ch++) 
    for (int x = x0; x < x1; x++) {
      for (int y = y0; y < y1; y++)
        for (int z = z0; z < z1; z++)
        {
          if (isTile(ch,x, y, z))
          {
            aABBs.add(new Phys(x, y, z, x + 1, y + 1, z + 1));
          }
        }
    }
    return aABBs;
  }

    public int getTile(int x, int i, int z) {
        return 1;
    }

    public boolean isLit(int x, int y, int z) {
      return true;
    }

    public void setTile(int x, int y, int z, int i) {
    //
    }

    public boolean isSolidTile(int x, int y, int z) {
     return true;
    }
  public void tick()
  {
  /*this.unprocessed += this.width * this.height * this.depth;
  int ticks = this.unprocessed / 400;
  this.unprocessed -= ticks * 400;
  for (int i = 0; i < ticks; i++)
    {
    int x = this.random.nextInt(this.width);
    int y = this.random.nextInt(this.depth);
    int z = this.random.nextInt(this.height);
    Tile tile = Tile.tiles[getTile(x, y, z)];
    if (tile != null)
      {
      tile.tick(this, x, y, z, this.random);
      }
    }*/
  }
}
