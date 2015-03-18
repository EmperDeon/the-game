package utils.cache;

import net.sf.ehcache.*;
import net.sf.ehcache.config.*;

public class Ehcache implements ICache {
 private Cache cache;

 public Ehcache ( long offHeap ) {
  CacheConfiguration config = new CacheConfiguration("sample-offheap-cache", 0).
     overflowToOffHeap(true).maxBytesLocalOffHeap(offHeap, MemoryUnit.BYTES);
  CacheManager manager = CacheManager.create();
  manager.addCache(new Cache(config));
  this.cache = manager.getCache("sample-offheap-cache");
 }

 @Override
 public byte[] get ( long key ) {
  Element element = cache.get(key);
  return element != null ? (byte[]) element.getValue() : null;
 }

 @Override
 public boolean put ( long key, byte[] value ) {
  cache.put(new Element(key, value));
  return true;
 }

 @Override
 public void close () {
  cache.dispose();
  CacheManager.create().removeCache("sample-offheap-cache");
 }
}
