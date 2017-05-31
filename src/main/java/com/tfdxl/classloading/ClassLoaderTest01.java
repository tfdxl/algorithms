package com.tfdxl.classloading;

/**
 * Created by tianfeng on 2017/4/22.
 */
public class ClassLoaderTest01 {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String str = classLoader.toString();
    }
}
