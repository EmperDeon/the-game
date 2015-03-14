package level.player;

import utils.containers.pos.PlayerPos;
import utils.containers.vec.Vec3;

public class Player {
 private final PlayerPos coord;
 private final Vec3<Double> cam = new Vec3<>();

 private Health health;

 public Player () {
  this.coord = new PlayerPos();
 }

 public void init ( PlayerPos coord, Health health ) {
  this.coord.init(coord);
  this.health.init(health);
 }

 public Vec3<Double> getCam () {
  return cam;
 }

 public PlayerPos getCoord () {
  return coord;
 }

 public Health getHealth () {
  return health;
 }

 public void tick () {

 }

}
