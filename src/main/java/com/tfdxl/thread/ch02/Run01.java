package com.tfdxl.thread.ch02;

/**
 * Created by tianfeng on 2017/4/3.
 */
public class Run01 {

    public static void main(String[] args) {
        PrintString01 printString01 = new PrintString01();
        new Thread(printString01).start();
        System.out.println("我要结束他,stopThread = "+Thread.currentThread().getName());
        printString01.setContinuePrint(false);
    }
}
