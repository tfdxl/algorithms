package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/14.
 */
public class TestWait {

    public static class WaitThread extends Thread {

        private Class aClass;

        private int i;

        public WaitThread(Class a, int i) {
            this.aClass = a;
            this.i = i;
        }

        @Override
        public void run() {

            synchronized (aClass) {
                try {
                    System.out.println("Thread " + i + " get the moniter.");
                    aClass.wait();
                    System.out.println("Thread " + i + " releases the monitor.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class NotifyOneThread extends Thread {

        private Class aClass;

        public NotifyOneThread(Class aClass) {
            this.aClass = aClass;
        }

        @Override
        public void run() {
            synchronized (aClass) {
                aClass.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[100];
        for (int i = 1; i <= 100; i++) {
            threads[i - 1] = new WaitThread(TestWait.class, i);
        }

        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }

        Thread.sleep(1000L);

        new NotifyOneThread(TestWait.class).start();
    }
}
