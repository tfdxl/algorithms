package com.tfdxl.dynamic_programming;

/**
 * Created by tianfeng on 2017/4/4.
 */
public class MatrixOperation {

    /**
     * @param a
     * @param b
     * @return
     */
    public static Matrix matrixMultiply(Matrix a, Matrix b) {

        if (a.getColumns() != b.getRows()) {
            throw new RuntimeException("矩阵不满足相乘条件");
        } else {

            Matrix matrix = new Matrix();
            matrix.setRows(a.getRows());
            matrix.setColumns(b.getColumns());

            int[][] data = new int[a.getRows()][b.getColumns()];

            for (int i = 0; i < a.getRows(); i++) {
                for (int j = 0; j < b.getRows(); j++) {
                    int cell = 0;
                    for (int k = 0; k < a.getColumns(); k++) {
                        cell += cell + a.getCell(i, k) * b.getCell(k, j);
                    }
                    data[i][j] = cell;
                }
            }
            matrix.setData(data);
            return matrix;
        }
    }

    /**
     * 自底向上法求解
     *
     * @param p
     */
    public static void matrixChainOrder(int[] p) {

        int n = p.length - 1;

        //allocate new table to save cost of m[i,j]
        int[][] m = new int[n + 1][n + 1];
        //allocate new table to save best solution
        int[][] s = new int[n][n + 1];

        for (int i = 0; i < n + 1; i++) {
            m[i][i] = 0;
        }

        //l is the chain length
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {

                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.println("最小的代价： " + m[1][n]);
        printOptimalParens(s, 1, n);
    }

    private static void printOptimalParens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static int memoizedMatrixChain(int[] p) {

        int n = p.length - 1;

        /**
         * 计算出来的矩阵Ai,j的最小计算代价
         */
        int[][] m = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                /**
                 * 表示还没有存过值，只需要上三角
                 */
                m[i][j] = Integer.MAX_VALUE;
            }

        }
        return lookUpChain(m, p, 1, n);
    }

    private static int lookUpChain(int[][] m, int[] p, int i, int j) {
        if (m[i][j] < Integer.MAX_VALUE)
            return m[i][j];

        if (i == j)
            m[i][j] = 0;
        else {
            for (int k = i; k <= j - 1; k++) {
                int q = lookUpChain(m, p, i, k) + lookUpChain(m, p, k + 1, j) + p[i - 1] * p[k] * p[j];

                if (q < m[i][j])
                    m[i][j] = q;
            }
        }
        return m[i][j];
    }

    public static void main(String[] args) {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        matrixChainOrder(p);
    }

}
