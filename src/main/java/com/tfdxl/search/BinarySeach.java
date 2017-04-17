package com.tfdxl.search;

/**
 * Created by tianfeng on 2017/3/25.
 */
public class BinarySeach {

    public static final int NOT_FOUND = -1;

    public static <T extends Comparable<? super T>> int binarySeach(T[] a, T x) {

        int low = 0, high = a.length - 1;
        while (low <= high) {

            int mid = (low + high) / 2;
            if (a[mid].compareTo(x) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;//found
            }
        }
        return NOT_FOUND;
    }
}
