package com.tfdxl.cache;

import java.util.*;

/**
 * Created by tianfeng on 2017/6/26.
 * 面试题经常考试
 * 双链表 + hashtable实现原理：
 * 将Cache的所有位置都用双连表连接起来，当一个位置被命中之后，就将通过调整链表的指向，
 * 将该位置调整到链表头的位置，新加入的Cache直接加到链表头中。这样，在多次进行Cache
 * 操作后，最近被命中的，就会被向链表头方向移动，而没有命中的，而想链表后面移动，链表
 * 尾则表示最近最少使用的Cache。当需要替换内容时候，链表的最后位置就是最少被命中的位
 * 置，我们只需要淘汰链表最后的部分即可。
 */
public class DoubleListLruCache<K, V> implements Cache<K, V> {

    //这个缓存能容纳的容量
    private int cacheSize;

    //缓存容器
    private Hashtable<K, Entry<K, V>> nodes;

    //当前容器所含的数据量
    private int currentSize;

    //链表头
    private Entry<K, V> first;

    //链表尾
    private Entry<K, V> last;

    /**
     * @param i
     */
    public DoubleListLruCache(int i) {
        currentSize = 0;
        cacheSize = i;
        //缓存容器
        nodes = new Hashtable<>(i);
    }

    /**
     * 获取缓存中的对象，并把它放在最前面
     *
     * @param k
     * @return
     */
    @Override
    public V get(K k) {
        Entry node = nodes.get(k);
        if (node != null) {
            moveToHead(node);
            return (V) node.value;
        } else {
            return null;
        }
    }

    /**
     * 添加k,v到hashtable.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        //首先查看hashtable是否存在这个entry，如果存在的话就只更新value
        Entry node = nodes.get(key);

        if (node == null) {
            //缓存容器是否超过大小
            if (currentSize >= cacheSize) {
                nodes.remove(last.key);
                removeLast();
            } else {
                currentSize++;
            }
            node = new Entry();
        }
        node.value = value;
        //将最新使用的节点放到链表头，表示最新使用的
        moveToHead(node);
        nodes.put(key, node);
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        currentSize = 0;
    }

    @Override
    public int usedEntries() {
        return 0;
    }

    @Override
    public Collection<Map.Entry<K, V>> getAll() {
        return null;
    }

    public Collection<Entry<K, V>> getList() {
        List<Entry<K, V>> list = new ArrayList<>();
        Entry entry = first;
        while (entry != null) {
            list.add(entry);
            entry = entry.next;
        }
        return list;
    }


    /**
     * 删除链表结尾节点，即使用最后使用的entry
     */
    private void removeLast() {

        /**
         * 链表尾部不为空，则将链表尾部指向null，删除链表尾
         */
        if (last != null) {
            if (last.prev != null) {
                last.prev.next = null;
            } else {
                first = null;
            }
            last = last.prev;
        }
    }

    public void remove(K key) {
        Entry node = nodes.get(key);
        //在链表中删除
        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (last == node)
                last = node.prev;
            if (first == node)
                first = node.next;
        }
        //hashtable中删除
        nodes.remove(key);
    }


    private void moveToHead(Entry node) {
        if (node == first)
            return;
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        if (last == node)
            last = node.prev;
        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        first = node;
        node.prev = null;
        if (last == null)
            last = first;
    }
}

class Entry<K, V> {
    Entry prev;
    Entry next;
    K key;
    V value;
}
