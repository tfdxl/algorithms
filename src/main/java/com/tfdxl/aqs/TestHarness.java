package com.tfdxl.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tianfeng on 2017/4/23.
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {

        //启用的闭锁只有一个
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endGate.countDown();
                }
            });

            t.start();
        }

        long start = System.nanoTime();

        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
