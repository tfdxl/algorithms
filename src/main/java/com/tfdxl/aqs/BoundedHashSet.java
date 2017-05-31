package com.tfdxl.aqs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by tianfeng on 2017/4/24.
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore semaphore;

    /**
     * 初始话容器能容纳的最大数量
     *
     * @param bound
     */
    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        semaphore = new Semaphore(bound);
    }

    public boolean add(T o) {
        boolean wasAdded = false;
        try {
            semaphore.acquire();
            wasAdded = set.add(o);
            return wasAdded;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } finally {
            /**
             * 如果没有添加成功，那就释放信号量
             */
            if (!wasAdded) {
                semaphore.release();
            }
        }
    }

    public boolean remove(Object o) {

        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }

}
