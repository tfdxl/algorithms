package com.tfdxl.unittest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tianfeng
 * @date 2017/10/26
 */
public class CrossReference {

    private static int unit = 20 * 1024;

    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        System.out.println("allocate start************");
        allocate();
        Thread.sleep(1000);
        System.out.println("allocate end************");
        System.in.read();
    }

    private static void allocate() throws Exception {
        int size = 1024 * 1024 * 400; // 400M
        int len = size / unit;
        List<BigObject> list = new ArrayList<>();

        for (int i = 1; i <= len; i++) {
            BigObject bigObject = new BigObject();
            list.add(bigObject);
            Thread.sleep(1);
            System.out.println(i);
        }
    }

    private static class BigObject {
        private byte[] foo;

        BigObject() {
            foo = new byte[unit];
        }
    }
}