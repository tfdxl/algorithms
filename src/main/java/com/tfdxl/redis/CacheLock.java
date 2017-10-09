package com.tfdxl.redis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tianfeng on 2017/10/9.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    String lockPrefix() default "";//redis lock prefix

    long timeOut() default 2000;//轮询锁的时间

    int expireTime() default 1000;//key在Redis中存活时间，1000s
}
