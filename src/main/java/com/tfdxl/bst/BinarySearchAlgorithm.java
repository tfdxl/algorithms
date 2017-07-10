package com.tfdxl.bst;

/**
 * Created by tianfeng on 2017/7/10.
 */
public class BinarySearchAlgorithm {

    /**
     * binary search in no-recursive way
     *
     * @param array
     * @param num
     * @return
     */
    public static int binSearch(int[] array, int num) {

        int lo = 0;
        int hi = array.length - 1;
        int mid;
        while (lo < hi) {

            mid = (lo + hi) / 2;
            if (array[mid] == num) {
                return mid + 1;
            } else if (array[mid] < num) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * binary search in recursive way
     *
     * @param array
     * @param num
     * @param lo
     * @param hi
     * @return
     */
    public static int recurBinSearch(int[] array, int num, int lo, int hi) {

        if (lo <= hi) {

            int mid = (lo + hi) / 2;
            if (num == array[mid]) {
                return mid + 1;
            } else if (num > array[mid]) {
                return recurBinSearch(array, num, mid + 1, hi);
            } else {
                return recurBinSearch(array, num, lo, mid - 1);
            }
        }
        return -1;
    }
}
