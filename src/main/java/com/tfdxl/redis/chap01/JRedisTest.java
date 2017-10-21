package com.tfdxl.redis.chap01;

import com.alibaba.fastjson.JSON;
import com.tfdxl.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Created by tianfeng on 2017/10/13.
 */
@Slf4j
public class JRedisTest {

    private Jedis jedis;

    @Before
    public void setup() {
        this.jedis = new Jedis("192.168.2.180", 6379);
    }

    @Test
    public void testString() {

        jedis.set("name", "xl");

        String result = jedis.ping();
        System.out.print(result);
        try {
            Thread.sleep(1000L);
            List<String> time = jedis.time();
            jedis.select(3);
            Long currentDb = jedis.getDB();
            System.err.println("Current DB : " + currentDb);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JedisMonitor jedisMonitor = new JedisMonitor() {
            @Override
            public void onCommand(String command) {
                System.err.println("I got the command: " + command);
            }
        };
        jedis.monitor(jedisMonitor);
    }

    @Test
    public void testJedis() {
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            System.err.println(JSON.toJSONString(jedis));
        } finally {
            RedisUtil.close(jedis);
        }
    }

    @Test
    public void testSubscribe() {

        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.err.println("User is " + JSON.parseObject(message, User.class).toString());
            }

            @Override
            public void onPMessage(String pattern, String channel, String message) {
                super.onPMessage(pattern, channel, message);
            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                super.onSubscribe(channel, subscribedChannels);
            }

            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {
                super.onUnsubscribe(channel, subscribedChannels);
            }

            @Override
            public void onPSubscribe(String pattern, int subscribedChannels) {
                super.onPSubscribe(pattern, subscribedChannels);
            }

            @Override
            public void onPong(String pattern) {
                super.onPong(pattern);
            }

            @Override
            public void unsubscribe() {
                super.unsubscribe();
            }
        };
        jedis.subscribe(jedisPubSub, "news.it");
    }

    @Test
    public void testHash() throws InterruptedException {

        this.jedis.hset("config", "name", "tianfeng");
        String name = jedis.hget("config", "name");
        System.err.println(name);

        jedis.set("msg", "hello world");
        final String encoding = jedis.objectEncoding("msg");
        System.err.println("encoding is " + encoding);
    }

    @Test
    public void testWatch() {
        this.jedis.set("goal", "get better");
        this.jedis.watch("name");

        final Transaction transaction = this.jedis.multi();
        transaction.set("goal", "be happy");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Object> result = transaction.exec();
        for (Object o : result) {
            System.err.println("Result is : " + o.toString());
        }


    }
}
