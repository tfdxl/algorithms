package com.tfdxl.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianfeng on 2017/4/6.
 */
public class LockInterruptTest {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock(true);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("中断锁，等待被中断");
                    //如果其它线程获取锁，该线程中断
                    reentrantLock.lock();
                    System.out.println("如果我出现，表示我没有响应中断");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("我被中断了");
                } finally {
                    reentrantLock.unlock();
                }
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println("我要先睡5秒");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("开始打断线程1");
                t1.interrupt();
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("已经成功打断线程1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            }
        });

        t2.start();
    }
}
