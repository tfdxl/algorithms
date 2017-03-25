package com.tfdxl.maxsub;

/**
 * Created by tianfeng on 2017/3/25.
 */
public class MaxSub {

    /**
     * 立方级别的时间复杂度
     *
     * @param a
     * @return
     */
    public static int maxSubSum1(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += a[k];
                }

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    /**
     * 平方时间复杂度
     *
     * @param a
     * @return
     */
    public static int maxSubSum2(int a[]) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    /**
     * O(NlgN)
     *
     * @param a
     * @return
     */
    public static int maxSubSum3(int[] a) {
        return maxSumRec(a, 0, a.length - 1);
    }


    private static int maxSumRec(int[] a, int left, int right) {
        if (left == right)
            if (a[left] > 0)
                return a[left];
            else
                return 0;

        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxLeftBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return max(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    private static int max(int a, int b, int c) {
        return ((a > b) ? a : b) > c ? ((a > b) ? a : b) : c;
    }
}
