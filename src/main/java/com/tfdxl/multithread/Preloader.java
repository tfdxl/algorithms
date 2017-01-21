package com.tfdxl.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by tianfeng on 2017/1/21.
 */
public class Preloader {

    private final FutureTask<Integer> future = new FutureTask<Integer>(new Callable<Integer>() {
        public Integer call() throws Exception {
            return new Integer(0);
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public Integer get() throws ExecutionException, InterruptedException {
        return future.get();
    }
}
