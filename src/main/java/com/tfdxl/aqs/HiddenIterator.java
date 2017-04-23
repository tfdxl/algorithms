package com.tfdxl.aqs;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by tianfeng on 2017/4/23.
 */
public class HiddenIterator {

    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i){
        set.add(i);
    }

    public synchronized void remove(Integer i){
        set.remove(i);
    }

    public void addTenThings(){
        Random r = new Random();
        for(int i =0;i<10;i++){
            add(r.nextInt());
        }

        System.out.println("The ten elements is "+set);
    }
}
