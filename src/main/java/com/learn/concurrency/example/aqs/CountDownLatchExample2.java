package com.learn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Katerina
 * @Date: 2018/8/16 19:33
 * @Description: 给定时间的等待，超时就不再处理直接返回
 **/
@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i ++){
            final int threadNum = i;

            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //指定时间参数
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        exec.shutdown();
        log.info("finish");
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
