package com.learn.concurrency.example.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/**
 * @Author: Katerina
 * @Date: 2018/8/24 22:26
 * @Description: 使用本地缓存GuavaCache
 **/
@Slf4j
public class GuavaCacheExample2 {

    public static void main(String[] args) {

        //build未指定参数时返回的是Cache
        Cache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10) // 最多存放10个数据
                .expireAfterWrite(10, TimeUnit.SECONDS) // 缓存10秒
                .recordStats() // 开启记录状态数据功能
                .build();

        log.info("{}", cache.getIfPresent("key1")); // null
        cache.put("key1", 1);
        log.info("{}", cache.getIfPresent("key1")); // 1
        cache.invalidate("key1");
        log.info("{}", cache.getIfPresent("key1")); // null


        //get(K, Callable)方法。这个方法返回缓存中相应的值，或者用给定的Callable运算并把结果加入到缓存中
        //这个方法简便地实现了模式--如果有缓存则返回；否则运算、缓存、然后返回
        try {
            log.info("{}", cache.get("key2", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            })); // -1
            cache.put("key2", 2);
            log.info("{}", cache.get("key2", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            })); // 2

            log.info("{}", cache.size()); // 1

            for (int i = 3; i < 13; i++) {
                cache.put("key" + i, i);
            }
            log.info("{}", cache.size()); // 10

            log.info("{}", cache.getIfPresent("key2")); // null

            Thread.sleep(11000);

            log.info("{}", cache.get("key5", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            })); // -1

            log.info("{},{}", cache.stats().hitCount(), cache.stats().missCount());

            log.info("{},{}", cache.stats().hitRate(), cache.stats().missRate());
        } catch (Exception e) {
            log.error("cache exception", e);
        }
    }
}
