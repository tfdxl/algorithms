package com.tfdxl.multithread;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by tianfeng on 2017/1/21.
 */
public class Indexer implements Runnable {
    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                indexFile(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void indexFile(File file) {

    }
}
