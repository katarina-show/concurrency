package com.learn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: Katerina
 * @Date: 2018/8/17 15:42
 * @Description: Semaphore实现获取多个许可 每秒输出一个log
 **/
@Slf4j
public class SemaphoreExample2 {

    private final static int threadCount = 20;

    public static void main(String[] args){

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i ++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(3);//获取多个许可
                    test(threadNum);
                    semaphore.release(3);//释放多个许可
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
