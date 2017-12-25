package com.tfdxl.java8;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author tianfeng
 * @date 2017/12/20
 */
public class BenchmarkTest {

    static int COUNT = 1000000;

    public static void main(String[] args) {

        testHashMap();
//        Hashtable<String, String> hashTable = new Hashtable<>();
//        long current = System.currentTimeMillis();
//        for (int i = 0; i < COUNT; i++) {
//            hashTable.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
//        }
//        System.err.println("time cost ---> " + (System.currentTimeMillis() - current));
    }

    static void testHashMap() {
        HashMap<String, String> hashTable = new HashMap<>();
        long current = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            hashTable.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        System.err.println("time cost ---> " + (System.currentTimeMillis() - current));
    }
}
