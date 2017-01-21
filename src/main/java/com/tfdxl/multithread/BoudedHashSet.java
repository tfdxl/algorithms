package com.tfdxl.multithread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by tianfeng on 2017/1/21.
 */
public class BoudedHashSet<T> {

    //使用Semaphore为容器设置边界

    private final Set<T> set;
    private final Semaphore sem;

    public BoudedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        this.sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            //如果没有添加成功，就不要浪费那个机会
            if (!wasAdded) {
                sem.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }
}
