package utils.cache;

public class MemoryCacheConfiguration {
 private final long capacity;
 private final long segmentSize;
 private final String imageFile;

 private static final long K = 1024;
 private static final long M = 1024 * K;
 private static final long G = 1024 * M;
 
 public MemoryCacheConfiguration () {
  this.capacity = 2 * G;
  this.segmentSize = 200 * K;
  this.imageFile = "/dev/shm/game-level";
 }

 public MemoryCacheConfiguration ( long capacity, long segmentSize, String imageFile ) {
  this.capacity = capacity;
  this.segmentSize = segmentSize;
  this.imageFile = imageFile;
 }

 public long getCapacity () {
  return capacity;
 }

 public long getSegmentSize () {
  return segmentSize;
 }

 public String getImageFile () {
  return imageFile;
 }
}
