package com.learn.concurrency.example.count;

import com.learn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Katerina
 * @Date: 2018/8/7 2:08
 * @Description: 使用AtomicInteger优化来实现线程安全
 **/
@Slf4j
@ThreadSafe
public class CountExample2 {

    //countDownLatch模拟请求总数
    public static int clientTotal = 5000;

    //Semaphore模拟并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0;i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        //get方法获取当前值
        log.info("count:{}",count.get());

    }

    private static void add(){

        //先自增1，在拿到自增后的值
        count.incrementAndGet();

        //先获取当前值，然后自增1，我们不用返回值，所以这两个效果是一样的
        //count.getAndIncrement();
    }
}

