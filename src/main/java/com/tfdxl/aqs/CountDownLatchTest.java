package com.tfdxl.aqs;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tianfeng on 2017/4/6.
 */
public class CountDownLatchTest {

    public static class Worker extends Thread {

        public static void main(String[] args) throws InterruptedException {
            //two workers coordinate
            CountDownLatch countDownLatch = new CountDownLatch(2);
            Worker worker1 = new Worker("zhangsan", 5000, countDownLatch);
            Worker worker2 = new Worker("lisi", 8000, countDownLatch);

            worker1.start();
            worker2.start();

            countDownLatch.await();

            System.out.println("all work done at the time of " + new Timestamp(System.nanoTime()).toString());
        }


        private volatile String workerName;

        private volatile int workTime;

        private volatile CountDownLatch countDownLatch;

        public Worker(String name, int time, CountDownLatch countDownLatch) {
            this.workerName = name;
            this.workTime = time;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            System.out.println("Worker " + workerName + "do work begin at " + new Timestamp(System.nanoTime()).toString());
            try {
                doWork();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("Worker " + workerName + "do work comlete at " + new Timestamp(System.nanoTime()).toString());
        }

        private void doWork() throws InterruptedException {
            Thread.sleep(workTime);
        }
    }
}
