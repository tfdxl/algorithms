package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/8.
 */
public class SafeDoubleCheckingLocking {

    private volatile static SafeDoubleCheckingLocking instance;

    private SafeDoubleCheckingLocking() {
    }

    public static SafeDoubleCheckingLocking getInstance() {

        if (instance == null) {
            synchronized (SafeDoubleCheckingLocking.class) {
                if (instance == null) {
                    instance = new SafeDoubleCheckingLocking();
                }

            }
        }
        return instance;
    }
}
