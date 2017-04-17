package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/3/28.
 */
public class FinalExample {

    int i;
    final int j;
    static FinalExample obj;

    public FinalExample() { //Construcor
        this.i = 1;         //写普通属性
        this.j = 2;         //写final属性
    }

    public static void writer() {
        obj = new FinalExample();
    }

    public static void read() {
        FinalExample finalExample = obj;
        int a = finalExample.i;
        int b = finalExample.j;
    }


    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (true) {

            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            while (true) {

            }
        });
        t2.start();

        while (true) {

        }
    }
}
