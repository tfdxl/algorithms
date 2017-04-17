package com.tfdxl.aqs;


import java.io.File;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Created by tianfeng on 2017/3/28.
 */
public class FutureTest {

    public static void main(String[] args) {

        //lambda
        File[] hiddenFiles = new File(".").listFiles(
                pathname -> pathname.isHidden()
        );

        //function reference
        File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(() -> {
            System.out.println("in the call function");
            return 0.1;
        });


        System.out.println("in the main thread");

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println("The result is " + result);
        } catch (InterruptedException e) {
            //计算跑出的
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void test1() {

        Function<Integer, Integer> f = x -> x * x;
        Function<Integer, Integer> g = x -> x + 1;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(34);
        System.out.println("result is " + result);
    }
}
