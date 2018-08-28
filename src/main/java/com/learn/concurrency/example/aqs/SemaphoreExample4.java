package com.learn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Katerina
 * @Date: 2018/8/17 15:50
 * @Description: Semaphore实现 可以等待获取许可
 **/
@Slf4j
public class SemaphoreExample4 {

    private final static int threadCount = 20;

    public static void main(String[] args){

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i ++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    //执行了15个log
                    if(semaphore.tryAcquire(5000,TimeUnit.MILLISECONDS)){//尝试获取一个许可
                        test(threadNum);
                        semaphore.release();
                    }

                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }

        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
