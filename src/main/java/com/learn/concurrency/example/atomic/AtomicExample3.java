package com.learn.concurrency.example.atomic;

import com.learn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: Katerina
 * @Date: 2018/8/7 2:08
 * @Description: 使用 LongAdder优化来实现线程安全
 **/
@Slf4j
@ThreadSafe
public class AtomicExample3 {

    //countDownLatch模拟请求总数
    public static int clientTotal = 5000;

    //Semaphore模拟并发执行的线程数
    public static int threadTotal = 200;

    //JDK8新增的LongAdder 在AtomicLong的基础上，将单点的更新压力分散到各个节点，低并发时可以拥有和AtomicLong一样的性能
    //LongAdder在高并发也表现较好，通过分散提高了性能
    //但是在统计的时候如果有并发更新，可能导致统计的数据有误差
    public static LongAdder count = new LongAdder();

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
        log.info("count:{}",count);

    }

    private static void add(){
        //注意这里调用的方法和之前不同
        count.increment();
    }
}

