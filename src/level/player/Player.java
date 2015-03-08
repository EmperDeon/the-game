package level.player;

import level.Level;
import utils.containers.pos.PlayerPos;
import utils.containers.vec.Vec3;

public class Player {

 private final String name = "";
 private final PlayerPos coord;
 private final Vec3<Double> cam = new Vec3<>();
 private final Level level;

 private Health health;

 public Player ( PlayerPos coord, Level level ) {
  this.level = level;
  this.coord = coord;
 }

 public void tick () {

 }
}
