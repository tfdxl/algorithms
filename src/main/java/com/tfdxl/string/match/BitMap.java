package com.tfdxl.string.match;

import java.util.Scanner;

/**
 * Created by tianfeng on 2017/3/19.
 */
public class BitMap {

    public static final int SHIFT = 5;
    public static final int MASK = 0x1F;

    private static void set(int n, int[] arr) {
        int indexLocation, bitLocation;
        indexLocation = n >> SHIFT;
        bitLocation = n & MASK;
        //set 1
        arr[indexLocation] |= 1 << bitLocation;
    }

    private static void init(int n, int[] arr) {
        int indexLocation;
        indexLocation = n >> SHIFT;
        arr[indexLocation] &= 0;
    }

    /**
     * 检验是否为1
     */
    public static boolean test(int n, int[] arr) {

        int i = 1 << (n & MASK);
        boolean flag = ((arr[n >> SHIFT] & i) == 1);
        return flag;
    }

    public static void main(String[] args) {
        int i, num, space, arr[];

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            num = in.nextInt();
            space = num / 32 + 1;
            arr = new int[space];

            //初始化bit位为0
            for (i = 0; i < num; i++) {
                init(i, arr);
            }

            //设置num的bit位位1
            set(num,arr);
            //测试
            if(test(num,arr)){
                System.out.println("测试成功");
            }else{
                System.out.println("测试失败");
            }

        }
        in.close();
    }

}
