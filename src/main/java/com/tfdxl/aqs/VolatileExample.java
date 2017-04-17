package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/3/28.
 */
public class VolatileExample {
    int x = 0;
    volatile int b = 0;

    private void write(){
        x = 5;
        b = 1;
    }

    private void read(){
        int dummy = b;
        while(x!=5){
            System.out.println("x是不等于5的");
        }
        System.out.println("x是5的");
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileExample volatileExample = new VolatileExample();

        Thread t1 = new Thread(() -> volatileExample.write());
        Thread t2 = new Thread(() -> volatileExample.read());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
