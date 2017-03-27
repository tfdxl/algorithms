package com.tfdxl.aqs;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by tianfeng on 2017/3/27.
 */
public class Mutex implements Lock,Serializable{

    //内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer{

        //是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //当状态为0的时候获取锁
        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;//otherwise unused

            if(compareAndSetState(0,1)){
                //获取锁成功，设置排它锁的所有者为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁

        @Override
        protected boolean tryRelease(int acquires) {
            assert acquires ==1;//otherwise unused
            if(getState() ==0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return  true;
        }

        //返回一个condition，每个condition都包含一个condition队列
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    //仅需要将操作代理到Sync上即可，代理模式
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
