package com.learn.concurrency.example.concurrent;

import com.learn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author: Katerina
 * @Date: 2018/8/10 17:18
 * @Description: 并发容器之 ConcurrentSkipListSet 对应TreeSet
 * 对于批量操作时，并不能保证线程安全
 **/
@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {

    //countDownLatch模拟请求总数
    public static int clientTotal = 5000;

    //Semaphore模拟并发执行的线程数
    public static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0;i < clientTotal; i++){
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",set.size());
    }

    private static void update(int i){
        set.add(i);
    }
}
