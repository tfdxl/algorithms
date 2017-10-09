package com.tfdxl.classloading;

public class Parent {

    static {
        System.err.println("Parent static block...");
    }

    {
        System.err.println("Parent not static block ...");
    }
}
