package com.tfdxl.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.Date;

/**
 * Created by tianfeng on 2017/4/6.
 */
public class SemaphoeTest {
    /**
     * 阻塞锁：同一个时刻只有一个线程运行，当一个线程执行结束的时候，再去释放下一个线程
     * 共享锁:同一个时刻可以有多个线程运行
     */

    public static void main(String[] args) {
        //定义3个信号量
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "is running at the time of " + new Date());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            }).start();
        }
    }
}
