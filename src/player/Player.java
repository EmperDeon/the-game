package player;

import level.Level;
import utils.vec.Vec2f;
import utils.vec.Vec3f;

public class Player {
private String name = "";    
public Vec3f coord ;
public Vec2f cam = new Vec2f();
private final Level level;

private Health health;

public Player (Vec3f coord,Level level){
 this.level=level;
 this.coord=coord;
}

public void tick(){
 
}
}
