package com.tfdxl.file;

/**
 * @author tianfeng
 */
public class ClassTest {

    static class God {

        public God() {}

        static {
            System.err.println("God class is inited ... ");
        }
    }

    public static void main(String[] args) {
        Class c = God.class;
        Package p = c.getPackage();
        System.err.println(p.getName());
    }
}
