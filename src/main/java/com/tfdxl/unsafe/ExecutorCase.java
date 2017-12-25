package com.tfdxl.unsafe;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author tianfeng
 * @date 2017/12/21
 */
public class ExecutorCase {

    private static int MAX_TASK = 20;

    public static void main(String[] args) {

        final Executor executor = Executors.newFixedThreadPool(10, r -> {
            final Thread thread = new Thread(r);
            thread.setName("TF_Thread");
            thread.setUncaughtExceptionHandler((t, e) -> System.err.print("This is e" + e.getStackTrace()));
            return thread;
        });

        for (int i = 0; i < MAX_TASK; i++) {
            executor.execute(new Task());
        }
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.err.println("Current thread id is " + Thread.currentThread().getId());
        }
    }
}
