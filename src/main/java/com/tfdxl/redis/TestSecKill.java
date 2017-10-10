package com.tfdxl.redis;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tianfeng on 2017/10/9.
 */
public class TestSecKill {

    private Long commidityId1 = 10000001L;
    private Long commidityId2 = 10000002L;

    @Test
    public void testSecKill() {

        final int threadCount = 1000;
        final int splitPoint = 500;

        //开始点
        final CountDownLatch beginCount = new CountDownLatch(1);
        //结束点
        final CountDownLatch endCount = new CountDownLatch(threadCount);

        //proxied class
        final SecKillImpl testClass = new SecKillImpl();
        //threads
        final Thread[] threads = new Thread[threadCount];

        //起500个线程，秒杀第一个商品
        for (int i = 0; i < splitPoint; i++) {
            threads[i] = new Thread(() -> {
                try {
                    //等待在一个信号量上，挂起
                    beginCount.await();
                    //用动态代理的方式调用secKill方法
                    SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(SeckillInterface.class.getClassLoader(),
                            new Class[]{SeckillInterface.class}, new CachedLockInterceptor(testClass));
                    proxy.secKill("test1", commidityId1);
                    endCount.countDown();
                } catch (InterruptedException e) {

                }
            });
            threads[i].start();
        }

        //再起500个线程，秒杀第二件商品
        for (int i = splitPoint; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                try {
                    //等待在一个信号量上，挂起
                    beginCount.await();
                    //用动态代理的方式调用secKill方法
                    SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(SeckillInterface.class.getClassLoader(),
                            new Class[]{SeckillInterface.class}, new CachedLockInterceptor(testClass));
                    proxy.secKill("test2", commidityId2);
                    endCount.countDown();
                } catch (InterruptedException e) {

                }
            });
            threads[i].start();
        }

        long startTime = System.currentTimeMillis();
        //主线程释放开始信号量，并等待结束信号量，这样做保证1000个线程做到完全同时执行，保证测试的正确性
        beginCount.countDown();
        try {
            //主线程等待结束信号量
            endCount.await();
            //观察秒杀结果是否正确
            System.out.println("commidityId1 ===> " + SecKillImpl.inventory.get(commidityId1));
            System.out.println("commidityId2 ===> " + SecKillImpl.inventory.get(commidityId2));
            System.out.println("Error count:" + CachedLockInterceptor.FAILED_ACQUIRE_COUNT);
            System.out.println("Total cost: " + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {

        }
    }
}
