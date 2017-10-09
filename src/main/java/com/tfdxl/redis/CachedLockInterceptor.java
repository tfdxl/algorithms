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

        return null;
    }

    private Object getLockedObject(Annotation[][] annotations, Object[] args) {
        if (null == args || args.length == 0) {
            throw new CachedLockException("方法参数为空，没有被锁定的对象");
        }
        return null;
    }
}
