package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/8.
 */
public class DoubleCheckedLocking {

    private static Instance instance;

    public static Instance getInstance() {

        if (instance == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }
}
