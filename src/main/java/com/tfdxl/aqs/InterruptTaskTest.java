package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/5/31.
 */
public class InterruptTaskTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new TestThread3());
        thread.start();

        //运行一段时间后中断线程
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("$$$$$$$$$$$$$$$$$$$");
        System.out.println("Interrupted Thread ... ");
        System.out.println("###################");
        thread.interrupt();
        System.out.println("the test thread has been interrupted ... ");
        System.out.println(thread.isInterrupted());
    }
}

/**
 * Thread.interrupt()不会中断一个正在运行的线程，，他的作用是，在线程受到阻塞的时候，跑出来一个中断的信号
 * 这样线程就能退出阻塞的状态，更准确而言，线程被Object.wait(),Thread.join(),Thread.sleep()阻塞的时候就会抛出InterruptedException
 */
class TestThread1 implements Runnable {

    private double d = 0.0;

    @Override
    public void run() {

        try {

            while (true) {
                System.out.println("I am running ... ");
                for (int i = 0; i < 10000000; i++) {
                    d = d + (Math.PI + Math.E) / d;
                    //给线程调度器一个可以切换到其他进程的信号
                    //Thread.yield();
                }
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("TestThread is interrupted ... ");
        }
    }
}

class TestThread2 implements Runnable {

    @Override
    public void run() {

        //这样处理的时候，当外界调用中断的时候，才会中断
        while (!Thread.interrupted()) {
            System.out.println("I am running ... ");
            for (int i = 0; i < 100; i++) {
                System.out.println("The couter is " + i);
            }
        }

    }
}


class TestThread3 implements Runnable {

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                System.out.println("I am running ... ");
                Thread.sleep(20);
                //这样处理的时候，当外界调用中断的时候，才会中断
                System.out.println("calculating ... ");

                for (int i = 0; i < 100; i++) {
                    System.out.println("The couter is " + i);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exit by Exception .... ");
        }
        System.out.println("TestThread3 has been exited ... ");
    }
}