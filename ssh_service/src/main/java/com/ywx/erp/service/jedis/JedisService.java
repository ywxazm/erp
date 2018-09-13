package com.ywx.erp.service.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class JedisService {

    private JedisPool jedisPool;
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void set(String key, String json) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, json);
        jedis.close();
    }

    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get(key);
        jedis.close();
        return str;
    }

}
