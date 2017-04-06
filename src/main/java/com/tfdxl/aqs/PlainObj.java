package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/6.
 */
public class PlainObj {

    public synchronized void functionA() throws InterruptedException{
        System.out.println("This is the function A");
        Thread.sleep(10000L);
        //call the function B
    }

    public synchronized void functionB() {
        System.out.println("This is the function B");
    }

    public static void main(String[] args) throws InterruptedException{
        PlainObj plainObj = new PlainObj();
        plainObj.functionA();
        new TestThread(plainObj).run();

    }

    public static class TestThread implements Runnable {

        private PlainObj plainObj;

        public TestThread(PlainObj plainObj) {
            this.plainObj = plainObj;
        }

        @Override
        public void run() {
            System.out.println("Thread call the function b");
            this.plainObj.functionB();
            System.out.println("the block is over");
        }
    }
}
