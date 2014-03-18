package player;

import level.Level;
import utils.Vec2f;
import utils.Vec3f;

public class Player {
private String name;    
public Vec3f coord = new Vec3f();
public Vec2f cam = new Vec2f();
private final Level level;


public Player (int x,int y,int z,Level level){
 this.level=level;
 this.coord.x=x;
 this.coord.y=y;
 this.coord.z=z;
}

public void tick(){
 
}
}
