package com.tfdxl.thread.ch02;

import lombok.Data;

/**
 * Created by tianfeng on 2017/4/3.
 */
@Data
public class PrintString {

    private boolean isContinuePrint = true;

    public void printStringMethod() throws InterruptedException {

        while(isContinuePrint == true)
            System.out.println("run printStringMethod threadName="+Thread.currentThread().getName());
        Thread.sleep(1000);
    }

}
