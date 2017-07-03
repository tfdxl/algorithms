package com.tfdxl.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianfeng on 2017/7/3.
 * -Xmx500M -Xms500M -Xmn200M -XX:+UseConcSweepGC
 * -XX:+UseCMSInitiatingOccupancyOnly
 * -XX:CMSInitiatingOccupancyFraction=90   老生代使用率达到90%的时候触发CMS GC
 */
public class Demo {

    public static void main(String[] args) {
        allocateMemeroy();
        try {
            Thread.sleep(1000000);
        } catch (Exception e) {

        }
    }

    public static void allocateMemeroy() {

        List<byte[]> list = new ArrayList<>();
        int size = 1024 * 1024 * 480;
        int len = size / (20 * 1024);
        for (int i = 0; i < len; i++) {
            try {
                byte[] bytes = new byte[20 * 1024];
                list.add(bytes);
            } catch (OutOfMemoryError e) {

            }
        }
    }
}
