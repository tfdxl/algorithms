package com.tfdxl.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianfeng on 2017/3/28.
 */
public class ReentrantLockExample {

    int a = 0;

    Lock lock = new ReentrantLock();

    public void writer() {

        lock.lock();
        try {
            a++;
            System.out.println("writer a = " + a);
        } finally {
            lock.unlock();
        }
    }

    public void reader() {

        lock.lock();
        try {
            int i = a;
            System.out.println("reader a = " + i);
        } finally {
            lock.unlock();
        }
    }



}
