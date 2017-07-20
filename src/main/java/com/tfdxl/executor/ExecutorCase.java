package com.tfdxl.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianfeng on 2017/7/19.
 */
public class ExecutorCase {

    private static ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            executor.execute(new Task());
        }
        executor.shutdown();
    }

    final static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
