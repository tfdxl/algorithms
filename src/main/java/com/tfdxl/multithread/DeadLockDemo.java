package com.tfdxl.multithread;

/**
 * Created by tianfeng on 2017/3/26.
 *
 * 避免死锁额的办法：
 * (1)避免一个线程获取多个锁
 * (2)保证每个锁只占用一个资源
 * (3)尝试使用定时锁
 * (4)对于数据库锁，加锁和解锁必须在一个数据库连接当中
 */
public class DeadLockDemo {

    private static String A = "A";

    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {

        Thread t1 = new Thread(new Runnable() {
            public void run() {

                synchronized (A) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1111");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {

                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2222");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
