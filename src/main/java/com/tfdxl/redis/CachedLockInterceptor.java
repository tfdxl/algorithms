package com.tfdxl.redis;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by tianfeng on 2017/10/9.
 * <p>
 * http://blog.csdn.net/u010359884/article/details/50310387
 * https://github.com/lsfire/redisframework/blob/master/redislockframework/src/main/java/com/liushao/redislockframework/CacheLockInterceptor.java
 */
public class CachedLockInterceptor implements InvocationHandler {

    public static int FAILED_ACQUIRE_COUNT = 0;

    private Object proxied;

    public CachedLockInterceptor(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //获取注解
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);

        //没有注解直接执行
        if (null == cacheLock) {
            System.out.println("No cache lock annotation....");
            return method.invoke(this.proxied, args);
        }

        //获取方法中参数的注解
        Annotation[][] annotations = method.getParameterAnnotations();

        //根据获取到的参数注解和参数的列表获取枷锁的参数
        String objValue = getLockedObject(annotations, args).toString();

        //new lock
        RedisLock redisLock = new RedisLock(cacheLock.lockPrefix(), objValue);

        //加锁
        boolean result = redisLock.lock(cacheLock.timeOut(), cacheLock.expireTime());
        if (!result) {
            FAILED_ACQUIRE_COUNT++;
            throw new CachedLockException("get lock failed");
        }

        //加锁成功，执行方法
        try {
            return method.invoke(this.proxied, args);
        } finally {
            redisLock.unlock();
        }
    }

    private Object getLockedObject(Annotation[][] annotations, Object[] args) {

        if (null == args || args.length == 0) {
            throw new CachedLockException("方法参数为空，没有被锁定的对象");
        }

        if (null == annotations || annotations.length == 0) {
            throw new CachedLockException("没有被注解的参数");
        }

        int index = -1;

        //不支持多个参数加锁
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                if (annotations[i][j] instanceof LockedComplexObject) {//注解为LockedComplexObject
                    try {
                        return args[i].getClass().getField(((LockedComplexObject) annotations[i][j]).field());
                    } catch (NoSuchFieldException | SecurityException e) {
                        throw new CachedLockException("注解对象中没有该属性" + ((LockedComplexObject) annotations[i][j]).field());
                    }
                }

                if (annotations[i][j] instanceof LockedObject) {
                    index = i;
                    break;
                }
            }
            //找到第一个后直接break，不支持多参数加锁
            if (index != -1) {
                break;
            }
        }

        if (index == -1) {
            throw new CachedLockException("请指定被锁定参数");
        }
        return args[index];
    }
}
