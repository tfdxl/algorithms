package com.tfdxl.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tianfeng on 2017/1/21.
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {

                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        //do nothing
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        long end = System.nanoTime();
        return end - start;
    }
}
