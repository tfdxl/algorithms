package com.tfdxl.javafeatures;

/**
 * Created by tianfeng on 2017/3/26.
 */
public class Singleton {

    private volatile static  Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){

        if(null == instance){
            instance = new Singleton();
        }

        return instance;
    }
}
