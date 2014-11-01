package render;

import utils.vec.Vec3;

public class RendTexCoord {
 private int face;
 private Vec3<Integer> coord;
 private TId tid;


public RendTexCoord (Vec3<Integer> coord,int face,TId tid){
 this.coord=coord;
 this.face=face;
 this.tid=tid;
}

public void set(TId id){
 this.tid=id;
}

public void set(Vec3<Integer> coord){
 this.coord=coord;
}

public void set(int face){
 this.face=face;
}

public TId getT(){
 return tid;
}

public Vec3<Integer> getC(){
 return coord;
}

public int getF(){
 return face;
}

}
