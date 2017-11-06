package com.tfdxl.classloading;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by tianfeng on 2017/11/6.
 */
public class TestProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("say")) {
            System.err.println("Hello ,TF");
        }
        return null;
    }
}
