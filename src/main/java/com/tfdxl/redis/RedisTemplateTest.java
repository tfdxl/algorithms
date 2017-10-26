package com.tfdxl.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tianfeng
 * @date 2017/10/9
 */
public class RedisTemplateTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("spring-redis.xml");
        appCtx.close();
    }
}
