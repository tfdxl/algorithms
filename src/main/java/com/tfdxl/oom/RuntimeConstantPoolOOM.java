package com.tfdxl.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianfeng on 2017/5/13.
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
            System.out.println(i);
        }
    }
}
