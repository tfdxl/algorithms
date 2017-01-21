package com.tfdxl.multithread;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by tianfeng on 2017/1/21.
 */
public class FileCrawler implements Runnable {

    private final BlockingQueue<File> fileBlockingQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileBlockingQueue, FileFilter fileFilter, File root) {
        this.fileBlockingQueue = fileBlockingQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    public void run() {

        try {
            crawl(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void crawl(File rootDir) throws InterruptedException {

        File[] entries = rootDir.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else {
                    if (!alreadyIndexed(entry)) {
                        fileBlockingQueue.put(entry);
                    }
                }
            }
        }
    }

    private boolean alreadyIndexed(File root) throws InterruptedException {
        return fileBlockingQueue.contains(root);
    }
}
