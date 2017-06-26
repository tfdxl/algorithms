package com.tfdxl.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tianfeng on 2017/6/26.
 * <p>
 * This cache has a fixed maximum number of elements cacheSize. If the cache
 * is full and another entry is added,the Lru(least recently used) entry is dropped
 * based on LinkedHashMap
 */
public class LruCache<K, V> implements Cache<K, V> {

    private static final float HASH_TABLE_LOAD_FACTOR = 0.75f;

    private LinkedHashMap<K, V> map;

    private int cacheSize;

    public LruCache(int cacheSize) {
        if (cacheSize <= 0)
            throw new IllegalArgumentException("The cache size cannot be below 0");
        this.cacheSize = cacheSize;
        int hashTableCapacity = (int) (Math.ceil(cacheSize / HASH_TABLE_LOAD_FACTOR) + 1);
        map = new LinkedHashMap<K, V>(hashTableCapacity, HASH_TABLE_LOAD_FACTOR, true) {

            //删除最年老的节点
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LruCache.this.cacheSize;
            }
        };
    }

    @Override
    public V get(K k) {
        return map.get(k);
    }

    @Override
    public void put(K k, V v) {
        map.put(k, v);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int usedEntries() {
        return map.size();
    }

    @Override
    public Collection<Map.Entry<K, V>> getAll() {
        return new ArrayList<>(map.entrySet());
    }
}
