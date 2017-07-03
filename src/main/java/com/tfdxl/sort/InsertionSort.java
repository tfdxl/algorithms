package com.tfdxl.sort;

import java.util.Arrays;

/**
 * Created by tianfeng on 2017/7/3.
 * <p>
 * 插入排序就是每一步都将一个待排数据按其大小插入到已经排序的数据中的适当位置，直到全部插入完毕。
 * 插入排序方法分直接插入排序和折半插入排序两种，这里只介绍直接插入排序，折半插入排序留到“查找”内容中进行。
 */
public class InsertionSort {

    public static void insertionSort(int[] unsorted) {

        for (int i = 1; i < unsorted.length; i++) {

            if (unsorted[i - 1] > unsorted[i]) {

                //第i个数字比前面的要小，当然移动到前面咯
                int temp = unsorted[i];
                //记录当前的index
                int j = i;

                //若果还是存在当前的节点小于第i个数字，往后移动
                while (j > 0 && unsorted[j - 1] > temp) {
                    unsorted[j] = unsorted[j - 1];
                    j--;
                }
                unsorted[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] x = {6, 2, 4, 1, 5, 9};
        insertionSort(x);
        System.out.println(Arrays.toString(x));
    }
}
