package com.tfdxl.bst;

/**
 * Created by tianfeng on 2017/7/10.
 */
public class BinarySearchAlgorithm {

    /**
     * Binary search in no-recursive way
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
     * Binary search in recursive way
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

    public static int indexOf(int[] array, int num) {
        int n = array.length;
        int low = 0;
        int hi = n - 1;
        int mid = 0;
        while (low < hi) {
            mid = (low + hi) / 2;
            if (array[mid] < num) {
                low = mid + 1;
            } else {
                hi = mid;
            }
        }
        if (array[low] != num) {
            return -1;
        } else {
            return low;
        }
    }
}
