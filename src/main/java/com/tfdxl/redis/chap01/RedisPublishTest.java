package com.tfdxl.redis.chap01;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by tianfeng on 2017/10/13.
 */
public class RedisPublishTest {

    private Jedis jedis;

    @Before
    public void setup() {
        this.jedis = new Jedis("127.0.0.1", 6379);
    }

    @Test
    public void publish() {
        int count = 0;
        while (true) {
            count++;
            jedis.publish("news.it", JSON.toJSONString(new User("username" + count, count, "password" + count)));
        }
    }

    @Test
    public void sync() {
        Long count = jedis.pubsubNumPat();
        jedis.clientSetname("tianfeng");
        System.err.println(count);
        List<String> list = jedis.time();
        for (String s : list) {
            System.err.println("Time : " + s);
        }

        String[] arr = jedis.clientList().split("\n");
        for (String str : arr) {
            System.err.println(str);
        }
    }
}
