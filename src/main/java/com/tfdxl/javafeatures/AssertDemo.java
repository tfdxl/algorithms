package com.tfdxl.javafeatures;

/**
 * Created by tianfeng on 2017/3/26.
 */
public class AssertDemo {

    public static void main(String[] args) {
        test1(-4);
    }

    public static void test1(int a){
        assert a>0:"Something goes wrong here,a cannot be less than 0";
    }
}
