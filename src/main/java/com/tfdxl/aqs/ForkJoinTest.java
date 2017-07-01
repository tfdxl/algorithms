package com.tfdxl.aqs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by tianfeng on 2017/7/1.
 */
public class ForkJoinTest {

    private static class DemoTask extends RecursiveTask<Integer> {

        private int start;
        private int end;

        public DemoTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (end - end < 100) {
                for (int i = start; i < end; i++) {
                    sum += i;
                }
            } else {
                int middle = (start + end) / 2;
                DemoTask left = new DemoTask(start, middle);
                DemoTask right = new DemoTask(middle, end);
                left.fork();
                right.fork();
                sum = left.join() + right.join();
            }
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DemoTask demoTask = new DemoTask(1, 10000);

        //对线程池的扩展
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(demoTask);
        System.out.println("The result is " + result.get());
    }
}
