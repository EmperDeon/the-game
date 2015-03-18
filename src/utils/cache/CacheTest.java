package utils.cache;

import java.util.Random;

public class CacheTest {

 private static final long MAGIC = 54331;

 private static final int WARMUP_COUNT = 100000;
 private static final int RUN_COUNT = 1000000;

 public static void testWrite ( UnsafeMemoryCache cache, int count ) {
  Random random = new Random(0);
  for ( int i = 0 ; i < count ; i++ ) {
   long key = random.nextInt(1 << 20) * MAGIC;
   cache.put(key, new byte[random.nextInt(8192)]);
  }
 }

 public static void testRead ( UnsafeMemoryCache cache, int count ) {
  Random random = new Random(1);
  for ( int i = 0 ; i < count ; i++ ) {
   long key = random.nextInt(1 << 20) * MAGIC;
   cache.get(key);
  }
 }

 public static void testRead9Write1 ( UnsafeMemoryCache cache, int count ) {
  Random random = new Random(2);
  for ( int i = 0 ; i < count ; i++ ) {
   long key = random.nextInt(1 << 20) * MAGIC;
   if ( random.nextInt(10) == 0 ) {
    cache.put(key, new byte[random.nextInt(8192)]);
   } else {
    cache.get(key);
   }
  }
 }

 public static void test () {
  UnsafeMemoryCache cache;
  try {
   cache = new UnsafeMemoryCache();

//   testWrite(cache, WARMUP_COUNT);
//   testRead(cache, WARMUP_COUNT);
//   testRead9Write1(cache, WARMUP_COUNT);

   String cacheClass = cache.getClass().getSimpleName();
   long start, end;

   start = System.currentTimeMillis();
   testWrite(cache, RUN_COUNT);
   end = System.currentTimeMillis();
   System.out.println(cacheClass + " write: " + (end - start));

   start = System.currentTimeMillis();
   testRead(cache, RUN_COUNT);
   end = System.currentTimeMillis();
   System.out.println(cacheClass + " read: " + (end - start));

   start = System.currentTimeMillis();
   testRead9Write1(cache, RUN_COUNT);
   end = System.currentTimeMillis();
   System.out.println(cacheClass + " read-write: " + (end - start));

   cache.close();
  } catch ( Exception ex ) {
   ex.printStackTrace();
  }
 }

 public static void main ( String[] args ) throws Exception {
  test();
 }
}
