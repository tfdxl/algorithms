package com.tfdxl.callback;

/**
 * Created by tianfeng on 2017/7/4.
 */
public class Xiaoli {

    public synchronized void executeMessage(CallBack callBack, String question) throws InterruptedException {

        System.out.println("The question from xiaowang is " + question);
        //assume we need a long time to solve the question
        for (int i = 0; i < 1000; i++) {
            //no-op
        }
        Thread.sleep(1000L);
        String result = "the answer is 2";
        callBack.solve(result);
    }
}
