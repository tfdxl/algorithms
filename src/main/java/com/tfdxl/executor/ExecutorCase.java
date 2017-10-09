package com.tfdxl.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianfeng on 2017/7/19.
 */
public class ExecutorCase extends Base{

    private static ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {
        System.out.println(ExecutorCase.class.getSimpleName());
        System.out.println(ExecutorCase.class.getSimpleName());
    }

    final static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class Base{}
