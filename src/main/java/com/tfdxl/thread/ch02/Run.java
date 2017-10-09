package com.tfdxl.thread.ch02;

/**
 * Created by tianfeng on 2017/4/3.
 */
public class Run{

    public static void main(String[] args) throws InterruptedException {
       Thread t = new Thread("thread1"){
           @Override
           public void run() {
               super.run();
               System.out.println("the thread has executed");
               try {
                   Thread.sleep(100000000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       };
       t.setDaemon(true);

       t.start();
       Thread.sleep(100000000);

    }
}
