package com.tfdxl.multithread;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by tianfeng on 2017/3/26.
 */
public class MapTest {

    private int i = 0;

    public static void main(String[] args) throws NoSuchFieldException {

        final HashMap<String,String> map = new HashMap<String, String>();

        for(int i=0;i<10000;i++){
            new Thread(new Runnable() {
                public void run() {
                    map.put(UUID.randomUUID().toString(),"");
                }
            }).start();
        }
    }
}
