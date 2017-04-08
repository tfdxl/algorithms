package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/8.
 * <p>
 * 基于类初始化的解决方案
 * <p>
 * Initialization on demand holder idiom
 */
public class InstanceFactory {

    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }

    public static Instance getInstance() {
        //首次调用导致InstanceHolder被初始化，初始化的时候只有一个线程可以访问
        return InstanceHolder.instance;
    }

}
