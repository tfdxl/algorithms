package com.tfdxl.file;

/**
 * @author tianfeng
 */
public class ThreadInt {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();
        thread.interrupt();
        boolean b = thread.isInterrupted();
        System.err.println(b);

        thread.interrupt();
        b = thread.isInterrupted();
        System.err.println(b);
        System.err.println(thread.isInterrupted());
        b = thread.isInterrupted();
        System.err.println(b);
    }
}
