package com.tfdxl.java8;

/**
 * @author tianfeng
 */
public class Intern {

    // 测试 String.intern()的使用
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        String str3 = "a";
        String str4 = "bc";
        String str5 = str3 + str4;
        String str6 = new String(str1);

        print("------no intern------");
        printnb("str1 == str2 ? ");
        print(str1 == str2);
        printnb("str1 == str5 ? ");
        print(str1 == str5);
        printnb("str1 == str6 ? ");
        print(str1 == str6);
        print();

        print("------intern------");
        printnb("str1.intern() == str2.intern() ? ");
        print(str1.intern() == str2.intern());
        printnb("str1.intern() == str5.intern() ? ");
        print(str1.intern() == str5.intern());
        printnb("str1.intern() == str6.intern() ? ");
        print(str1.intern() == str6.intern());
        printnb("str1 == str6.intern() ? ");
        print(str1 == str6.intern());
    }

    private static void print() {
        System.err.println();
    }

    private static void print(String s) {
        System.err.println(s);
    }

    private static void print(boolean b) {
        System.err.println(b + " \n");
    }

    static void printnb(String s) {
        System.err.print(s);
    }

}
