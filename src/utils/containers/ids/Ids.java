package utils.containers.ids;

public class Ids {
 private final Mids mids = new Mids();
 private final BlockIds bids = new BlockIds();
 private final LevelBlockIds lids = new LevelBlockIds();

 public BlockIds getBids () {
  return bids;
 }

 public LevelBlockIds getLBI () {
  return lids;
 }

 public Mids getMids () {
  return mids;
 }

}
