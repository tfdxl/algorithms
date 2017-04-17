package com.tfdxl.aqs;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianfeng on 2017/4/6.
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(true);

        for (int i = 0; i < 5; i++)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        reentrantLock.lock();
                        System.out.println(new Date() + "我获取到锁");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }).start();
    }
}
