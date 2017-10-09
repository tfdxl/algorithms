package com.tfdxl.redis;


/**
 * Created by tianfeng on 2017/10/9.
 */
public class CachedLockException extends RuntimeException{

    public CachedLockException(String msg){
        super(msg);
    }
}
