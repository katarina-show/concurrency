package com.learn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Katerina
 * @Date: 2018/8/17 19:29
 * @Description: 到达屏障优先执行的函数
 **/
@Slf4j
public class CyclicBarrierExample3 {

    //指定Runnable参数，所有线程到达屏障时会先执行这部分代码
    private static CyclicBarrier barrier = new CyclicBarrier(5,() -> {
        log.info("callback is running");
    });

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0;i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        barrier.await();
        log.info("{} continue",threadNum);
    }

}
