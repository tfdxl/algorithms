package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/8.
 */
public class VolatileExample1 {
    int a = 0;

    volatile boolean flag = false;

    public void writer() {
        a = 1;
        flag = true;
    }

    public void reader() {
        if (flag) {
            int i = a;
            System.out.println("reader a " + i);
        }
    }


    public static void main(String[] args) {

        VolatileExample1 volatileExample1 = new VolatileExample1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileExample1.writer();
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileExample1.reader();
            }
        });

        t2.start();

    }
}
