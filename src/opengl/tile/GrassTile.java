 package opengl.tile;
 
 import level.Level;
 import java.util.Random;
 
 public class GrassTile extends Tile
 {
protected GrassTile(int id)
{
  super(id);
  this.tex = 3;
}
 
protected int getTexture(int face)
{
  if (face == 1) return 0;
  if (face == 0) return 2;
  return 3;
}
 
public void tick(Level level, int x, int y, int z, Random random)
{
  if (!level.isLit(x, y, z))
  {
    level.setTile(x, y, z, Tile.dirt.id);
  }
  else
  {
    for (int i = 0; i < 4; i++)
    {
      int xt = x + random.nextInt(3) - 1;
      int yt = y + random.nextInt(5) - 3;
      int zt = z + random.nextInt(3) - 1;
      if ((level.getTile(xt, yt, zt) == Tile.dirt.id) && (level.isLit(xt, yt, zt)))
      {
        level.setTile(xt, yt, zt, Tile.grass.id);
      }
    }
  }
}
 }

/* Location:           C:\Users\Илья\AppData\Roaming\.minecraft\versions\c0.0.11a\c0.0.11a.jar
 * Qualified Name:     minecraft.level.tile.GrassTile
 * JD-Core Version:    0.6.2
 */