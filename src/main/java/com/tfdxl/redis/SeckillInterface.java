package com.tfdxl.redis;

/**
 * Created by tianfeng on 2017/10/9.
 */
public interface SeckillInterface {

    @CacheLock(lockPrefix = "test_prefix")
        //最简单的秒杀方法，参数是用户ID和商品ID。可能有多个线程争抢一个商品，所以商品ID加上LockedObject注解
    void secKill(String userId, @LockedObject Long commidityID);
}
