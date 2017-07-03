package com.tfdxl.sort;

/**
 * Created by tianfeng on 2017/7/3.
 */
public class MergeSort {

    public static void merge(int[] array, int low, int mid, int high) {
        int i = low; //i是第一段序列的下标
        int j = mid + 1;//j是第二段序列的下标
        int k = 0;//k是临时存放序列的下标
        int[] temp = new int[high - low + 1];

        //扫描第一段和第二段序列，直到有一个扫描结束
        while (i <= mid && j <= high) {
            //判断第一段和第二段取出来的数字更小，将其存入合并序列，并且继续向下扫描
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i++;
                k++;
            } else {
                temp[k] = array[j];
                j++;
                k++;
            }
        }

        //如果第一段还没有扫描完，将其全部复制到合并序列当中去
        while (i <= mid) {
            temp[k] = array[i];
            k++;
            i++;
        }

        //如果第二段还没有扫描完，将其全部复制到合并序列当中去
        while (j <= high) {
            temp[k] = array[j];
            j++;
            k++;
        }

        //将合并序列复制到原始的序列当中去
        for (k = 0, i = low; i <= high; i++, k++) {
            array[i] = temp[k];
        }
    }

    public static void mergePass(int[] array, int gap, int length) {
        int i;
        //归并两个gap长度的相邻子表
        for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {
            merge(array, i, i + gap - 1, i + 2 * gap - 1);
        }
        //余下两个子表，后者长度小于gap
        if (i + gap - 1 < length) {
            merge(array, i, i + gap - 1, length - 1);
        }
    }

    public static int[] sort(int[] list) {
        for (int gap = 1; gap < list.length; gap *= 2) {
            mergePass(list, gap, list.length);
            System.out.println("gap = " + gap + ":\t");
            printAll(list);
        }
        return list;
    }

    public static void printAll(int[] list) {
        for (int value : list) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {9, 1, 5, 3, 4, 2, 6, 8, 7};

        System.out.print("排序前:\t\t");
        printAll(array);
        sort(array);
        System.out.print("排序后:\t\t");
        printAll(array);
    }
}























