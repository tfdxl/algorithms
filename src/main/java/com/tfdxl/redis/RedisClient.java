package com.tfdxl.redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tianfeng on 2017/10/9.
 */
public final class RedisClient {

    private JedisPool jedisPool;

    public RedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Object getByKey(String key) {

        Jedis client = jedisPool.getResource();
        try {
            return client.get(key);
        } finally {
            client.close();
        }
    }

    /**
     * 存入的时hash结构的数据,并且去掉value中的引号
     *
     * @param key key
     * @param map map的key实质为field。
     * @return
     */
    public <T, S> boolean hmsetWithoutQuotationMarks(String key, Map<T, S> map) {
        Jedis client = jedisPool.getResource();
        try {
            Iterator<Map.Entry<T, S>> iterator = map.entrySet().iterator();
            Map<String, String> stringMap = new HashMap<>();
            String filed;
            String value;
            while (iterator.hasNext()) {
                Map.Entry<T, S> entry = iterator.next();
                filed = String.valueOf(entry.getKey());
                value = JSON.toJSONString(entry.getValue()).replace("\"", "");
                stringMap.put(filed, value);
            }
            client.hmset(key, stringMap);
            return true;
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    /**
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, Integer seconds) {
        final Jedis client = jedisPool.getResource();
        Long success = 1l;
        try {
            success = client.expire(key, seconds);
        } finally {
            client.close();
        }
        return success;
    }

    /**
     * 判断String类型key是否存在
     */
    public boolean isKeyExist(String key) {
        Jedis client = jedisPool.getResource();
        boolean o = false;
        try {
            o = client.exists(key);
        } finally {
            client.close();// 向连接池“归还”资源
        }
        return o;
    }

    /**
     * String类型的键值写入redis
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        Jedis client = jedisPool.getResource();
        String isSuccess;
        try {
            isSuccess = client.set(key, value);
            if ("OK".equals(isSuccess)) {
                return true;
            } else {
                return false;
            }
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public Long setnx(String key, String value) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.setnx(key, value);
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    /**
     * String类型的键值写入redis,并设置失效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setKeyWithExpireTime(String key, String value, int time) {

        if (time <= 0) {
            return false;
        }

        final Jedis client = jedisPool.getResource();
        String result;
        try {
            result = client.setex(key, time, value);
            if ("OK".equals(result)) {
                return true;
            } else {
                return false;
            }
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    /**
     * list<String>结构的数据写入redis
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lpush(String key, List<String> value) {
        final Jedis client = jedisPool.getResource();
        try {
            Transaction tx = client.multi();
            for (String one : value) {
                tx.lpush(key, one);
            }
            tx.exec();
            return true;
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    /**
     * 根据key获取list类型
     *
     * @param key
     * @return
     */
    public List<String> lrange(String key) {
        final Jedis client = jedisPool.getResource();
        List<String> returnList = null;
        try {
            returnList = client.lrange(key, 0, -1);
        } finally {
            client.close();// 向连接池“归还”资源
        }
        return returnList;
    }

    public List<String> lrange(String key, int start, int length) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.lrange(key, start, length);
        } finally {
            client.close();
        }
    }

    /**
     * @param key
     * @param o
     * @return
     */
    public boolean setAnObject(String key, Object o) {
        final Jedis client = jedisPool.getResource();
        try {
            String afterSerialize = JSON.toJSONString(o);
            client.set(key, afterSerialize);
            return true;
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public <T> T getSetT(String key, T newValue) {
        final Jedis client = jedisPool.getResource();
        T t;
        try {
            String afterSerialize = Util.beanToJson(newValue);
            String value = client.getSet(key, afterSerialize);
            t = (T) Util.jsonToBean(value, newValue.getClass());
            return t;
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    /**
     * use json
     *
     * @param key
     * @param zz
     * @param <T>
     * @return
     */
    public <T> T getAnObject(String key, Class<T> zz) {
        final Jedis client = jedisPool.getResource();
        T t;
        try {
            String s = client.get(key);
            if (s == null || s.length() == 0) {
                return null;
            }
            t = Util.jsonToBean(s, zz);
        } finally {
            client.close();
        }
        return t;
    }

    public List<String> getKeys(String pattern) {
        final Jedis client = jedisPool.getResource();
        final List<String> result = new ArrayList<>();
        try {
            Set<String> set = client.keys(pattern);
            result.addAll(set);
        } finally {
            client.close(); // 向连接池“归还”资源
        }
        return result;
    }

    public boolean delKey(String key) {
        final Jedis client = jedisPool.getResource();
        try {
            //System.out.println("del key=" + key);
            return client.del(key) > 0;
        } finally {
            client.close(); // 向连接池“归还”资源
        }
    }

    /**
     * 存入的时hash结构的数据
     *
     * @param key key
     * @param map map的key实质为field。
     * @return
     */
    public <T, S> boolean hmset(String key, Map<T, S> map) {
        final Jedis client = jedisPool.getResource();
        try {
            Iterator<Map.Entry<T, S>> iterator = map.entrySet().iterator();
            Map<String, String> stringMap = new HashMap<>();
            String filed;
            String value;
            while (iterator.hasNext()) {
                Map.Entry<T, S> entry = iterator.next();
                filed = String.valueOf(entry.getKey());
                value = Util.beanToJson(entry.getValue());
                stringMap.put(filed, value);
            }
            client.hmset(key, stringMap);
            return true;
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public <T> T hgetObject(String key, String field, Class<T> cls) {
        final Jedis client = jedisPool.getResource();
        try {
            String value = client.hget(key, field);
            return (T) Util.jsonToBean(value, cls);
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public String hgetString(String key, String field) {
        final Jedis client = jedisPool.getResource();
        try {
            String value = client.hget(key, field);
            return value;
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public Map<String, String> hGetAll(String key) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.hgetAll(key);
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public List<String> hkeys(String key) {
        final Jedis client = jedisPool.getResource();
        try {
            List<String> fields = new ArrayList<String>();
            Set<String> set = client.hkeys(key);
            fields.addAll(set);
            return fields;
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public List<String> hvals(String key) {
        final Jedis client = jedisPool.getResource();
        try {
            List<String> values = client.hvals(key);
            return values;
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public boolean hexists(String key, String field) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.hexists(key, field);
        } finally {
            client.close();// 向连接池“归还”资源
        }
    }

    public long incr(String key) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.incr(key);
        } finally {
            client.close();
        }
    }

    public void hdel(String key, String... fields) {
        final Jedis client = jedisPool.getResource();
        try {
            client.hdel(key, fields);
        } finally {
            client.close();
        }
    }

    /**
     * @param key
     * @param field
     */
    public void lpush(String key, String field) {
        final Jedis client = jedisPool.getResource();
        try {
            client.lpush(key, field);
        } finally {
            client.close();
        }
    }

    public void lpush(String key, Object obj) {
        final Jedis client = jedisPool.getResource();
        try {
            String field = Util.beanToJson(obj);
            client.lpush(key, field);
        } finally {
            client.close();
        }
    }

    /**
     * 该方法不适用于普通的调用，该方法只针对于错误日志记录
     *
     * @param key
     * @param field
     */
    public void lpushForErrorMsg(String key, String field) {
        final Jedis client = jedisPool.getResource();
        try {
            if (client.llen(key) > 1000) {
                return;
            }
            client.lpush(key, field);
        } finally {
            client.close();
        }
    }

    public long llen(String key) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.llen(key);
        } finally {
            client.close();
        }
    }

    public List<String> blPop(String key, int timeoutSeconds) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.blpop(timeoutSeconds, key);
        } finally {
            client.close();
        }
    }

    public <T> long sadd(String key, String... values) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.sadd(key, values);
        } finally {
            client.close();
        }
    }

    public <T> long sadd(String key, List<T> ts) {
        final Jedis client = jedisPool.getResource();
        try {
            if (ts == null || ts.size() == 0) {
                return 0L;
            }
            String[] values = new String[ts.size()];
            for (int i = 0; i < ts.size(); i++) {
                values[i] = ts.get(i).toString();
            }
            return client.sadd(key, values);
        } finally {
            client.close();
        }
    }

    public long srem(String key, String... values) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.srem(key, values);
        } finally {
            client.close();
        }
    }

    public <T> long srem(String key, List<T> ts) {
        final Jedis client = jedisPool.getResource();
        try {
            if (ts == null || ts.size() == 0) {
                return 0l;
            }
            String[] values = new String[ts.size()];
            for (int i = 0; i < ts.size(); i++) {
                values[i] = ts.get(i).toString();
            }
            return client.srem(key, values);
        } finally {
            client.close();
        }
    }

    public Set<String> getByRange(String key, double min, double max) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.zrangeByScore(key, min, max);
        } finally {
            client.close();
        }
    }

    public Long decr(String key) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.decr(key);
        } finally {
            client.close();
        }
    }

    public Long hlen(String key) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.hlen(key);
        } finally {
            client.close();
        }
    }

    public List<String> hmget(String key, String... fields) {
        final Jedis client = jedisPool.getResource();
        try {
            return client.hmget(key, fields);
        } finally {
            client.close();
        }
    }

    /**
     * 从redis里面得到以 某字符串开头的所有key
     *
     * @param str
     */
    public Set<String> getKeyByStr(String str) {
        final Jedis client = jedisPool.getResource();

        Set<String> keys = null;
        try {
            keys = client.keys(str);
        } finally {
            client.close();
        }
        return keys;
    }

    public void ltrim(String key, int start, int stop) {
        final Jedis client = jedisPool.getResource();
        try {
            client.ltrim(key, start, stop);
        } finally {
            client.close();
        }
    }
}
