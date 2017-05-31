package com.tfdxl.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by tianfeng on 2017/4/23.
 */
public class Preloader {

    /**
     * FutureTsk将计算结果从执行计算的线程传递到获取这个结果的线程
     */
    private final FutureTask<ProductInfo> futureTask = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {

        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }

        private ProductInfo loadProductInfo(){

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new ProductInfo();
        }
    });

    private final Thread thread = new Thread(futureTask);

    public void start(){
        thread.start();
    }

    public ProductInfo get(){
        try {
            return futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
           return null;
        }
    }

    public static class ProductInfo{


    }
}
