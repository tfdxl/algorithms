package com.tfdxl.aqs;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by tianfeng on 2017/3/28.
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();
    private int count = 0;

}
