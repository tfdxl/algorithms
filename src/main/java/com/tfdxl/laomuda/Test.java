package com.tfdxl.laomuda;

import java.io.FileFilter;
import java.lang.reflect.Field;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author tianfeng
 * @date 2017/12/14
 */
public class Test {

    public static void printString(String s, Print<String> print) {
        print.print(s);
    }

//    /**
//     * 编译的时候没问题
//     * @param s
//     */
//    private static void lambda$main$0(java.lang.String s) {
//
//    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        /**
         * 编译器他妈的竟然生成可一个私有的静态函数，这里说是生成的而不是等价
         */
        printString("test", s -> System.err.println(s));

        Comparator<String> comparator = (o1, o2) -> o1.compareTo(o2);

        List<Integer> list = new ArrayList<>();

        list.add(34);
        list.add(3434);

        int sum = 0;

        long s = list.stream().mapToInt(value -> value).reduce(0, (left, right) -> left + right);

        String name = void.class.getName();
        System.err.println(name);

        FileFilter fileFilter = pathname -> pathname.getName().equals(".java");

        Callable<String> callable = () -> "done";

        PrivilegedAction<String> privilegedAction = () -> "done";

        String s1 = Void.class.getName();
        System.err.println(s1);

        Field field = MyClass.class.getField("strings");

        MyClass m = new MyClass();

        List<String> strings = (List<String>) field.get(MyClass.class);

        s1.intern();

    }

    public static class MyClass {
        public static List<String> strings = new ArrayList<>();

        static {
            strings.add("tianfeg");
        }
    }
}
