package com.tfdxl.cache;

import java.util.Collection;
import java.util.Map;

/**
 * Created by tianfeng on 2017/6/26.
 */
public interface Cache<K, V> {

    /**
     * returns an value
     *
     * @param k
     * @return
     */
    V get(K k);

    /**
     * @param k
     * @param v
     */
    void put(K k, V v);

    /**
     *
     */
    void clear();

    /**
     * return the number of the used entries
     *
     * @return
     */
    int usedEntries();

    /**
     * return the while collection of the entries
     *
     * @return
     */
    Collection<Map.Entry<K, V>> getAll();
}
