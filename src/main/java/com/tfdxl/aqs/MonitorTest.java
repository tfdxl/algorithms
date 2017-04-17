package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/6.
 * <p>
 * Sleep方法：导致程序暂停执行一段时间，让出cpu给其他线程，其监控状态不改变，依然保持，到指定时间又恢复到运行状态
 * <p>
 * wait：线程放弃对象锁，进入此对象的等待锁定池，只有针对此对象调用notify后本线程才会进入对象锁定池准备
 */
public class MonitorTest {

    private static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("enter thread1 ... ");
                    System.out.println("thread1 is waiting ... ");
                    synchronized (MonitorTest.class) {
                        //调用wait，线程会放弃对象锁，进入等待此对象的等待锁定池
                        MonitorTest.class.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ");
                System.out.println("thread1 is over !!!");
            }
        }).start();

        Thread.sleep(10000L);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("enter thread2 ... ");
                    System.out.println("thread2 is waiting ... ");
                    synchronized (MonitorTest.class) {
                        //调用sleep线程不会放弃锁
                        MonitorTest.class.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on ");
                System.out.println("thread2 is over !!!");
            }
        }).start();
    }

}
