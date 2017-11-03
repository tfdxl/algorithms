package com.tfdxl.spring;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.core.env.PropertySource;

/**
 * Created by tianfeng on 2017/11/3.
 * <p>
 * 属性源的适配器，对于一个任意类型的属性源进行包装
 */
public class HttpClientPropertySource extends PropertySource<HttpClient> {

    public HttpClientPropertySource(String name, HttpClient source) {
        super(name, source);
    }

    /**
     * 外面可以从这里获取
     *
     * @param name
     * @return
     */
    @Override
    public Object getProperty(String name) {
        if (name.equals("baidu.content")) {
            HttpGet get = new HttpGet("http://www.baidu.com");
        }
        return null;
    }
}
