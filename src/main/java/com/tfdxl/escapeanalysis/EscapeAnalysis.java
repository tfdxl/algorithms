package com.tfdxl.escapeanalysis;

/**
 * Created by tianfeng on 2017/2/20.
 */
public class EscapeAnalysis {

    private static class Foo{

        private int x;
        private static int counter;

        public Foo(){
            x = (++counter);
        }
    }

    public static void main(String[] args){
        long start = System.nanoTime();
        for(int i=0;i<1000;i++){
            Foo foo = new Foo();
        }
        long end = System.nanoTime();
        System.out.print("Time cost is "+(double)(end - start)/1000000000 +" seconds");
    }
}
