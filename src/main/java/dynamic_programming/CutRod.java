package dynamic_programming;

/**
 * Created by tianfeng on 2017/4/4.
 */
public class CutRod {

    /**
     * 朴素递归求解方法（指数时间）
     *
     * @param weight
     * @param n
     * @return
     */
    public static int cutRod(int[] weight, int n) {
        if (n == 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            q = Math.max(q, weight[i] + cutRod(weight, n - i));
        }
        return q;
    }

    /**
     * 带备忘录的自顶向下，过程会保存每个子问题的解
     *
     * @param weight
     * @param n
     * @return
     */
    public static int memoizedCutRod(int[] weight, int n) {

        if (n <= 0) {
            return 0;
        }

        /**
         * 已知的收益总是非负值，忽略temp[0]
         */
        int[] temp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            temp[i] = Integer.MIN_VALUE;
        }

        return memoizedRodAux(weight, n, temp);
    }

    public static int memoizedRodAux(int[] weight, int n, int[] r) {

        int q = Integer.MIN_VALUE;

        /**
         * 首先检查所需要的值是不是存在
         */
        if (r[n] >= 0)
            return r[n];

        /**
         * 没有长度收益为0
         */
        if (n == 0)
            q = 0;
        else
            for (int i = 0; i < n; i++)
                q = Math.max(q, (weight[i] + memoizedRodAux(weight, n - i, r)));

        r[n] = q;
        return q;
    }

    /**
     * 自底向上
     *
     * @param weight
     * @param n
     * @return
     */
    public static int bottomUpCutRod(int[] weight, int n) {

        if (n <= 0) {
            return 0;
        }

        int[] temp = new int[n + 1];

        temp[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                q = Math.max(q, weight[i] + temp[j - i]);
            }
            temp[j] = q;
        }
        return temp[n];
    }

    public static void main(String[] args) {

        int[] weight = new int[40];
        for (int i = 0; i < 40; i++) {
            weight[i] = i + 1;
        }

        int q = cutRod(weight, 40);

        System.out.println(q);
    }
}
