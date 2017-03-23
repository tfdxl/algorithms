package com.tfdxl.multithread;

/**
 * Created by tianfeng on 2017/3/19.
 */
public class StringTest {

    public static void main(String[] args) {

        String s = new String("abc");
        String s1 = "abc";
        String s2 = new String("abc");

        System.out.println(s == s.intern());
        System.out.println(s1 == s1.intern());
        System.out.println(s1.intern() == s2.intern());
    }
}
