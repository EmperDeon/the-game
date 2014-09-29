package level.player;

import utils.vec.Vec2;
import utils.vec.Vec3;

public class Player {

 private final String name = "";
 private final Vec3<Double> coord;
 private final Vec2<Double> cam = new Vec2<>();
 private final Invertory inv = new Invertory();
 private final Health health;

 public Player ( Vec3<Double> coord ) {
  this.coord = coord;
  this.health = new Health();
 }

 public void tick () {

 }

 public String getName () {
  return name;
 }

 public Vec3<Double> getCoord () {
  return coord;
 }

 public Invertory getInv () {
  return inv;
 }

 public Health getHealth () {
  return health;
 }
}
