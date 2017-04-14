package com.tfdxl.aqs;

/**
 * Created by tianfeng on 2017/4/8.
 */
public class FinalRefernceExample {

    final int[] intArray;

    static FinalRefernceExample obj;

    public FinalRefernceExample() {

        intArray = new int[1];
        intArray[0] = 1;
    }

    public static void writeOne() {
        obj = new FinalRefernceExample();
    }

    public static void writeTwo() {
        obj.intArray[0] = 2;
    }

    public static void reader() {
        if (obj != null) {
            int temp = obj.intArray[0];
            System.out.println("the temp is " + temp);
        }
    }


}
