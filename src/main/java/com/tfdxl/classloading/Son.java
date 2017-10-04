package com.tfdxl.classloading;

public class Son extends Parent{

    static{
        System.err.println("Son static block ... ");
    }

    {
        System.err.println("Son not static block ... ");
    }
}
