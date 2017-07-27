package com.tfdxl.array;

/**
 * Created by tianfeng on 2017/7/27.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread("Quartz Shutdown-Hook ") {
            @Override
            public void run() {
                System.out.println("The jvm is shutting down ...");
            }
        };
        Runtime.getRuntime().addShutdownHook(t);
        synchronized (Main.class) {
            Main.class.wait();
        }
    }
}
