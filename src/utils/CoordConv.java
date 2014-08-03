package utils;

import utils.vec.Vec4;

public class CoordConv {
 private Options opt;
 
 public CoordConv(Options opt){}
 
 public Float convH(Integer i){
  switch(0){// opt.getI("gui_size")
   case 0 : { return i * 0.002777778f; }   // Small  640  ×  360 (1 /  360)
   case 1 : { return i * 0.001302083f; }   // Medium 1366 x  768 (1 /  768)
   case 2 : { return i * 0.000925926f; }   // Large  1920 × 1080 (1 / 1080)
  // case 3 : {}   // Auto
  }
  main.Main.ERR_LOG.addE("CoordConv.convH()",new Exception("Error with converting "+i));
  return null;
 }
 
 public Float convW(Integer i){
  switch(0){// opt.getI("gui_size")
   case 0 : { return i * 0.001562500f; }   // Small  640  ×  360 (i /  640)
   case 1 : { return i * 0.000732064f; }   // Medium 1366 x  768 (i / 1366)
   case 2 : { return i * 0.000520833f; }   // Large  1920 × 1080 (i / 1920)
  // case 3 : { return null; }   // Auto
  }
  main.Main.ERR_LOG.addE("CoordConv.convW()",new Exception("Error with converting "+i));
  return null;
 }
 
 public Vec4<Float> conv4(Vec4<Integer> x){
  Vec4<Float> c = new Vec4<>();
  c.sX1(convW(x.gX1()));
  c.sX2(convW(x.gX2()));
  c.sY1(convH(x.gY1()));
  c.sY2(convH(x.gY2()));
  return c;
 }
}
