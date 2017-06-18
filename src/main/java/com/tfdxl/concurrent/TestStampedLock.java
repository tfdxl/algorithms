package com.tfdxl.concurrent;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by tianfeng on 2017/6/18.
 */
public class TestStampedLock {

    public static void main(String[] args) throws InterruptedException {
        final StampedLock lock = new StampedLock();
        new Thread() {
            @Override
            public void run() {

                long readLong = lock.writeLock();
                LockSupport.parkNanos(6100000000L);
                lock.unlockWrite(readLong);
            }
        }.start();
        Thread.sleep(100);
        for (int i = 0; i < 3; ++i) {
            new Thread(new OccupiedCPUReadThread(lock)).start();
        }
    }

    private static class OccupiedCPUReadThread implements Runnable {

        private StampedLock lock;

        public OccupiedCPUReadThread(StampedLock stampedLock) {
            this.lock = stampedLock;
        }

        @Override
        public void run() {
            Thread.currentThread().interrupt();
            long lockr = lock.readLock();
            System.out.println(Thread.currentThread().getName() + " get read lock");
            lock.unlockRead(lockr);

        }
    }
}
