package com.ywx.erp.service.jedis;

import redis.clients.jedis.JedisPool;

public class JedisService {

    private JedisPool jedisPool;
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void set(String key, String value) {
        jedisPool.getResource().set(key, value);
    }

    public String get(String key) {
        return jedisPool.getResource().get(key);
    }
}
