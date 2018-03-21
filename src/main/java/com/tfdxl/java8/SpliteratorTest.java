package com.tfdxl.java8;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author tianfeng
 * @date 2017/12/11
 */
public class SpliteratorTest {

    public static void main(String[] args) {

        String s = "update used_car set deliver_shop_code = '%s',sale_shop_full_name = '  B   ' where order_no  = '  C  ';";



    }

    static class ArrayListSpliterator<E> implements Spliterator<E> {

        /**
         * underlyind data
         */
        private final ArrayList<E> list;

        /**
         * 起始位置，advance,split
         */
        private int index;

        /**
         * 结束位置
         */
        private int fence;

        //存放list的expectedModCount

        ArrayListSpliterator(ArrayList<E> list) {this.list = list;}

        //起始位置

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            return false;
        }

        @Override
        public Spliterator<E> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }
    }
}
