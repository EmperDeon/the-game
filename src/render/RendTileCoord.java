package render;
public class RendTileCoord {
private int x,y,z,face,mid,tid;
public RendTileCoord (int x,int y,int z,int face,int mid,int tid){
this.x=x;
this.y=y;
this.z=z;
this.face=face;
this.mid=mid;
this.tid=tid;
}

public void set(int i,int ch){
    switch(ch){
        case 1:this.x=i; break;
        case 2:this.y=i; break;
        case 3:this.z=i; break;
        case 4:this.face=i; break;
        case 5:this.mid=i; break; 
        case 6:this.tid=i; break; 
        default: break;    
    }
}

public int get(int ch){
    switch(ch){
        case 1:return this.x; 
        case 2:return this.y;
        case 3:return this.z;
        case 4:return this.face;
        case 5:return this.mid;
        case 6:return this.tid;
       default:return -1;    
    }
}

}
