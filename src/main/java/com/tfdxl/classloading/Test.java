package com.tfdxl.classloading;

import java.lang.reflect.Proxy;

/**
 *
 * @author tianfeng
 * @date 2017/11/6
 */
public class Test {

    public static void main(String[] args) {
        Interface i = (Interface) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class<?>[]{Interface.class}, new TestProxy());
        i.say();
    }
}
