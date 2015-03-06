package level.player;

import utils.containers.vec.Vec3;
import utils.containers.vec.Vec2;
import level.Level;

public class Player {

 private final String name = "";
 public Vec3<Double> coord;
 public Vec2<Double> cam = new Vec2<>();
 private final Level level;

 private Health health;

 public Player ( Vec3<Double> coord, Level level ) {
  this.level = level;
  this.coord = coord;
 }

 public void tick () {

 }
}
