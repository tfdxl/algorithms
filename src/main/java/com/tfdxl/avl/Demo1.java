package com.tfdxl.avl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by tianfeng on 2017/3/22.
 */
public class Demo1 {

    public static void main(String[] args) {
        System.out.println("Main start ... ");
        FutureTask<String> task = new FutureTask(new Runnable() {
            public void run() {
                System.out.println("This is sub task");
            }
        }, "Hello");
        new Thread(task).start();
        try {
            System.out.println("返回执行结果："+task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main thread exits");
    }
}
