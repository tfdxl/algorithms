package dynamic_programming;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by tianfeng on 2017/4/4.
 */
public class LCS {

    @AllArgsConstructor
    enum Orientation {


        LEFT(1, "向左"),
        UP(2, "向上"),
        UP_LEFT(3, "左上");

        @Getter
        private int type;
        @Getter
        private String desc;
    }


    public static int lcsLength(char[] x, char[] y) {

        int m = x.length;
        int n = y.length;

        //allocate tables
        int[][] b = new int[m + 1][n + 1];

        int[][] c = new int[m + 1][n + 1];

        //condition1:  c[i][j] = 0 while i==0 or j==0
        for (int i = 0; i <= m; i++)
            c[i][0] = 0;

        for (int j = 0; j <= n; j++)
            c[0][j] = 0;

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {
                //condition2: c[i][j] = c[i-1][j-1]+1 while i,j>0 and x[i] == y[j]
                if (x[i-1] == y[j-1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = Orientation.UP_LEFT.getType();
                    //condition3:c[i][j]=max(c[i][j-1],c[i-1][j]) while i,j>0 and x[i]!=y[j]
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = Orientation.UP.getType();
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = Orientation.LEFT.getType();
                }
            }
        }

        //print
        printLcs(b, x, x.length, y.length);
        return c[m][n];
    }

    /**
     * 打印lcs
     *
     * @param b
     * @param x
     * @param i
     * @param j
     */
    private static void printLcs(int[][] b, char[] x, int i, int j) {

        if (i == 0 || j == 0)
            return;
        if (b[i][j] == Orientation.UP_LEFT.getType()) {
            printLcs(b, x, i - 1, j - 1);
            System.out.print(x[i] + "\t");
        } else if (b[i][j] == Orientation.UP.getType()) {
            printLcs(b, x, i - 1, j);
        } else {
            printLcs(b, x, i, j - 1);
        }
    }

    public static void main(String[] args) {
        char[] X = {'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] Y = {'B', 'D', 'C', 'A', 'B', 'A'};

        int length = lcsLength(X, Y);
        System.out.println("\nThe LCS length is " + length+" .");
    }
}
