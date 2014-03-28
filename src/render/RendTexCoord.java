package render;

import utils.TId;
import utils.vec.Vec5i;

public class RendTexCoord {
 private int face;
 private Vec5i coord;
 private TId tid;


public RendTexCoord (Vec5i coord,int face,TId tid){
 this.coord=coord;
 this.face=face;
 this.tid=tid;
}

public void set(TId id){
 this.tid=id;
}

public void set(Vec5i coord){
 this.coord=coord;
}

public void set(int face){
 this.face=face;
}

public TId getT(){
 return tid;
}

public Vec5i getC(){
 return coord;
}

public int getF(){
 return face;
}

}
