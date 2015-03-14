package level;

import java.util.*;
import level.chunk.*;
import mods.basemod.containers.Server;
import utils.containers.pos.ChunkPos;

public class LevelGen {
 private final Random random;
 private boolean sseed;
 private Chunk[][] ch;

 public LevelGen ( long seed ) {
  this.random = new Random(seed);
  sseed = true;
 }

 public LevelGen () {
  this.random = new Random();
  sseed = false;
 }

// public void gen ( Chunk ch ) {
//  for ( int x = 0 ; x < 16 ; x++ ) {
//   for ( int y = 0 ; y < 16 ; y++ ) {
//    //for ( int z = 0 ; z < 255 ; z++ ) {
//    // if ( z < 100 ) {
//    ch.setBlock(Server.instanceBlockPos(x, y, 100), new LevBlock());
//    // }
//    //  }
//   }
//  }
// }
// public LevBlock[][][] gen ( ChunkPos ch ) {
//  LevBlock[][][] t = new LevBlock[16][16][256];
//  for ( int x = 0 ; x < 16 ; x++ ) {
//   for ( int y = 0 ; y < 16 ; y++ ) {
//    for ( int z = 0 ; z < 65 ; z++ ) {
//     t[x][y][z] = Server.instatnceLevBlock();
//    }
//   }
//  }
//  return t;
// }
 private void genFirst ( ChunkContainer cont, ChunkPos center ) {
  ch = new Chunk[17][17];

  for ( int x = -8 ; x < 8 ; x++ ) {
   for ( int y = -8 ; y < 8 ; y++ ) {
    ch[x][y] = cont.getChunk(new ChunkPos(center.gX() + x, center.gY(), 0));
   }
  }

  int x;
  for ( x = 1 ; x <= 2 ; x++ ) {
   gen4(x, 8);
  }
  for ( x = 1 ; x <= 4 ; x++ ) {
   gen4(x, 7);
  }
  for ( x = 1 ; x <= 5 ; x++ ) {
   gen4(x, 6);
  }
  for ( x = 1 ; x <= 6 ; x++ ) {
   gen4(x, 5);
  }
  for ( x = 1 ; x <= 7 ; x++ ) {
   gen4(x, 4);
  }
  for ( x = 1 ; x <= 7 ; x++ ) {
   gen4(x, 3);
  }
  for ( x = 1 ; x <= 8 ; x++ ) {
   gen4(x, 2);
  }
  for ( x = 1 ; x <= 8 ; x++ ) {
   gen4(x, 1);
  }

  for ( x = -8 ; x <= 8 ; x++ ) {
   genl(x, 0);
  }

  for ( x = -8 ; x < 0 ; x++ ) {
   genl(0, x);
  }
  for ( x = 1 ; x <= 8 ; x++ ) {
   genl(0, x);
  }
 }

 private void genl ( int x, int y ) {

 }

 private void gen4 ( int x, int y ) {
  for ( int x1 = 0 ; x1 < 16 ; x1++ ) {
   for ( int y1 = 0 ; y1 < 16 ; y1++ ) {
    ch[x][y].setBlock(Server.instanceBlockPos(x1, y1, 64), Server.instatnceLevBlock());
    ch[x][y].setBlock(Server.instanceBlockPos(x1, y1, 65), Server.instatnceLevBlock());
   }
  }
 }

 public void setSeed ( long seed ) {
  this.random.setSeed(seed);
  sseed = true;
 }

 public boolean isSeed () {
  return sseed;
 }

// public byte[] generateMap () {
//  int w = this.width;
//  int h = this.height;
//  int d = this.depth;
//  int[] heightmap1 = new NoiseMap(0).read(w, h);
//  int[] heightmap2 = new NoiseMap(0).read(w, h);
//  int[] cf = new NoiseMap(1).read(w, h);
//  int[] rockMap = new NoiseMap(1).read(w, h);
//  byte[] blocks = new byte[this.width * this.height * this.depth];
//
//  for ( int x = 0 ; x < w ; x++ ) {
//   for ( int y = 0 ; y < d ; y++ ) {
//    for ( int z = 0 ; z < h ; z++ ) {
//     int dh1 = heightmap1[(x + z * this.width)];
//     int dh2 = heightmap2[(x + z * this.width)];
//     int cfh = cf[(x + z * this.width)];
//
//     if ( cfh < 128 ) {
//      dh2 = dh1;
//     }
//
//     int dh = dh1;
//     if ( dh2 > dh ) {
//      dh = dh2;
//     } else {
//      dh2 = dh1;
//     }
//     dh = dh / 8 + d / 3;
//
//     int rh = rockMap[(x + z * this.width)] / 8 + d / 3;
//     if ( rh > dh - 2 ) {
//      rh = dh - 2;
//     }
//
//     int i = (y * this.height + z) * this.width + x;
//     int id = 0;
//     //     if (y == dh) id = Tex.grass.id;
//     //   if (y < dh) id = Tex.dirt.id;
//     //   if (y <= rh) id = Tex.rock.id;
//     blocks[i] = ((byte) id);
//    }
//   }
//  }
//  int count = w * h * d / 256 / 64;
//  for ( int i = 0 ; i < count ; i++ ) {
//   float x = this.random.nextFloat() * w;
//   float y = this.random.nextFloat() * d;
//   float z = this.random.nextFloat() * h;
//   int length = (int) (this.random.nextFloat() + this.random.nextFloat() * 150.0F);
//   float dir1 = (float) (this.random.nextFloat() * 3.141592653589793D * 2.0D);
//   float dira1 = 0.0F;
//   float dir2 = (float) (this.random.nextFloat() * 3.141592653589793D * 2.0D);
//   float dira2 = 0.0F;
//
//   for ( int l = 0 ; l < length ; l++ ) {
//    x = (float) (x + Math.sin(dir1) * Math.cos(dir2));
//    z = (float) (z + Math.cos(dir1) * Math.cos(dir2));
//    y = (float) (y + Math.sin(dir2));
//
//    dir1 += dira1 * 0.2F;
//    dira1 *= 0.9F;
//    dira1 += this.random.nextFloat() - this.random.nextFloat();
//
//    dir2 += dira2 * 0.5F;
//    dir2 *= 0.5F;
//    dira2 *= 0.9F;
//    dira2 += this.random.nextFloat() - this.random.nextFloat();
//
//    float size = (float) (Math.sin(l * 3.141592653589793D / length) * 2.5D + 1.0D);
//
//    for ( int xx = (int) (x - size) ; xx <= (int) (x + size) ; xx++ ) {
//     for ( int yy = (int) (y - size) ; yy <= (int) (y + size) ; yy++ ) {
//      for ( int zz = (int) (z - size) ; zz <= (int) (z + size) ; zz++ ) {
//       float xd = xx - x;
//       float yd = yy - y;
//       float zd = zz - z;
//       float dd = xd * xd + yd * yd * 2.0F + zd * zd;
//       if ( (dd < size * size) && (xx >= 1) && (yy >= 1) && (zz >= 1) && (xx < this.width - 1) && (yy < this.depth - 1) && (zz < this.height - 1) ) {
//        int ii = (yy * this.height + zz) * this.width + xx;
//        //        if (blocks[ii] == Tex.rock.id)
//        {
//         blocks[ii] = 0;
//        }
//       }
//      }
//     }
//    }
//   }
//  }
//  return blocks;
// }
}
