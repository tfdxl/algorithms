package com.tfdxl.redis;

import com.alibaba.fastjson.JSON;

/**
 * Created by tianfeng on 2017/10/9.
 */
public class Util {

    public static String beanToJson(Object o) {
        return JSON.toJSONString(o);
    }

    public static <T> T jsonToBean(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }
}
