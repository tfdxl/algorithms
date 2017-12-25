package com.tfdxl.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by tianfeng on 2017/7/20.
 */
public class Main {

    private static final int _1mb = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        int i = 0;
        while (true) {
            i++;
            unsafe.allocateMemory(_1mb);
            System.err.println(i);
        }
    }
}
