package com.tfdxl.classloading;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tianfeng
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {






        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap();
        map.put("tianfeng",34);
        map.put("mengli",78);
        System.err.println(map.toString());
        map.forEach((s, integer) -> System.err.println(s+" ==>  "+integer));
        map.forEach(1, (s, integer) -> {

        });



        Class c = Class.forName("com.tfdxl.classloading.Parent");
        System.err.print(c);
    }
}
