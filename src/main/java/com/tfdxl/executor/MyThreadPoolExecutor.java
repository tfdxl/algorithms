package com.tfdxl.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianfeng on 2017/7/19.
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor{

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);



    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
}
