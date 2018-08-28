package com.learn.concurrency.example.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
/**
 * @Author: Katerina
 * @Date: 2018/8/24 22:20
 * @Description: 使用本地缓存GuavaCache
 **/
@Slf4j
public class GuavaCacheExample1 {

    public static void main(String[] args) {

        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10) // 最多存放10个数据
                .expireAfterWrite(10, TimeUnit.SECONDS) // 缓存10秒
                .recordStats() // 开启记录状态数据功能
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return -1;
                    }
                });//CacheLoader的作用是，调用get（key）方法时，若key不存在，则向缓存中加入此key和value（该方法的返回值）

        log.info("{}", cache.getIfPresent("key1")); // null
        cache.put("key1", 1);
        log.info("{}", cache.getIfPresent("key1")); // 1
        //清除缓存
        cache.invalidate("key1");
        log.info("{}", cache.getIfPresent("key1")); // null

        try {
            log.info("{}", cache.get("key2")); // -1
            cache.put("key2", 2);
            log.info("{}", cache.get("key2")); // 2

            log.info("{}", cache.size()); // 1

            for (int i = 3; i < 13; i++) {
                cache.put("key" + i, i);
            }
            log.info("{}", cache.size()); // 10

            log.info("{}", cache.getIfPresent("key2")); // null（因为最多只有10个，key2被干掉了）

            Thread.sleep(11000);

            log.info("{}", cache.get("key5")); // -1

            //命中成功次数和失败次数
            log.info("{},{}", cache.stats().hitCount(), cache.stats().missCount());

            //命中率2/7和未命中率5/7
            log.info("{},{}", cache.stats().hitRate(), cache.stats().missRate());
        } catch (Exception e) {
            log.error("cache exception", e);
        }
    }
}
