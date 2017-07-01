package com.tfdxl.concurrent;

import lombok.Data;

import java.awt.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by tianfeng on 2017/6/15.
 */
public class Renderer {

    private final ExecutorService executorService;

    Renderer(ExecutorService executorService) {
        this.executorService = executorService;
    }

    void renderPage(CharSequence source) {
        List<ImageInfo> info = scanForImageInfo(source);
        //初始化一个CompletionService
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executorService);
        for (final ImageInfo imageInfo : info) {
            //提交下载图片的任务
            completionService.submit(() -> imageInfo.downloadImage());
        }
        renderText(source);

        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {

        }
    }

    //模拟浏览器渲染html文本的方法
    void renderText(CharSequence source) {

    }


    //模拟图片数据
    @Data
    public static class ImageData {

        private long id;
        private String type;
        private Image image;
    }

    public static class ImageInfo {
        public ImageData downloadImage() {
            return null;
        }
    }

    void renderImage(ImageData imageData) {

    }

    List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }

}
