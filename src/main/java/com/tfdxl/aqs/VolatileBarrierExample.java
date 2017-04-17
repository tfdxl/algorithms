package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/8.
 */
public class VolatileBarrierExample {

    int a;

    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite() {
        //第一个volatile  read
        int i = v1;
        int j = v2;
        a = i + j;

        v1 = i + 1;
        v2 = j * 2;
    }

    public static void main(String[] args) {

    }
}
