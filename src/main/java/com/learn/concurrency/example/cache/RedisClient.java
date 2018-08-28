package com.learn.concurrency.example.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @Author: Katerina
 * @Date: 2018/8/24 21:32
 * @Description: http://redis.cn
 **/
@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    public void set(String key,String value) throws Exception{
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.auth("123456");
            jedis.set(key,value);
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }

    }

    public String get(String key) throws Exception {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.auth("123456");
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
