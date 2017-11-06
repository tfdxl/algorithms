package com.tfdxl.spring;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author tianfeng
 * @date 2017/11/3
 * <p>
 * 属性源的适配器，对于一个任意类型的属性源进行包装
 */
public class HttpClientPropertySource extends PropertySource<HttpClient> {

    public static final String PROPERTY = "baidu.content";

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
        if (PROPERTY.equals(name)) {
            //创建一个Get方法
            HttpGet get = new HttpGet("http://www.baidu.com");
            try {
                return IOUtils.toString(source.execute(get).getEntity().getContent(), Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ConfigurableEnvironment environment = new StandardEnvironment();
        CloseableHttpClient httpClient = HttpClients.custom().build();
        environment.getPropertySources().addFirst(new HttpClientPropertySource("httpPropertySource", httpClient));
        System.err.println(environment.getProperty(PROPERTY));
    }
}
