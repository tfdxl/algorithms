package com.tfdxl.thread.ch02;

/**
 * Created by tianfeng on 2017/4/3.
 */
public class Run01 {

    public static void main(String[] args) throws InterruptedException {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("我要停止他");
        printString.setContinuePrint(false);
    }
}
