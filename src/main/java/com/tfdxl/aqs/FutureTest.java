package com.tfdxl.aqs;


import java.util.concurrent.*;

/**
 * Created by tianfeng on 2017/3/28.
 */
public class FutureTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                System.out.println("in the call function");
                return 0.1;
            }
        });

        System.out.println("in the main thread");

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println("The result is "+result);
        } catch (InterruptedException e) {
            //计算跑出的
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
