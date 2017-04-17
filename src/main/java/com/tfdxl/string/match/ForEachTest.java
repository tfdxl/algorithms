package com.tfdxl.string.match;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianfeng on 2017/3/26.
 */
public class ForEachTest {

    public static void main(String[] args) {

        Map<String,Integer> items = new HashMap<String,Integer>();
        items.put("A",90);
        items.put("B",80);
        items.put("C",70);
        items.put("D",60);
        items.put("E",50);
        items.put("F",40);
        items.forEach((k,v)->System.out.println("Item key -> "+k+" Value->"+v));

        items.forEach((k,v)->{
            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });
    }
}
