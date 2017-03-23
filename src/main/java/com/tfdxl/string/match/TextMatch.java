package com.tfdxl.string.match;

/**
 * Created by tianfeng on 2017/3/19.
 * <p>
 * 字符串文本T[1,2,3,4... n],模式串P[1,2,3,4 ... m]
 * <p>
 * (1)
 */
public class TextMatch {

    /**
     * 朴素的字符串匹配方法
     *
     * @param text    字符串
     * @param pattern 模式串
     * @return 索引
     */
    public static int naiveStringMatch(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;

        if (n < m) {
            throw new RuntimeException("模式串不能比原字符串长!");
        }

        for (int s = 0; s <= n - m; s++) {
            for (int i = 0; i < m; i++) {
                if (text[i + s] != pattern[i]) {
                    break;
                }

                if (i == m - 1) {
                    System.out.println("pattern occurs with shift " + s);
                    return s;
                }
            }
        }
        return -1;
    }


    public static int rabinKarpMatcher(char[] text, char[] pattern, int d, int q) {

        return 0;
    }

    public static int calculateP(char[] str) {
        if (str == null || str.length == 0) {
            throw new RuntimeException("模式串不能为空串!");
        }
        int n = str.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + Character.getNumericValue(str[n - 1 - i]) * power(10, i);
        }
        return sum;
    }

    /**
     * 求指数
     *
     * @param a
     * @param n
     * @return
     */
    public static int power(int a, int n) {
        int result = a;
        for (int i = 1; i <= n; i++) {
            result *= a;
        }
        return result;
    }

    public static void main(String[] args) {
        TextMatch.naiveStringMatch("tianfeng".toCharArray(), "gfdg".toCharArray());
    }


}
