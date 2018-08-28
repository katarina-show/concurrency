package com.learn.concurrency.example.lock;

import com.learn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: Katerina
 * @Date: 2018/8/20 2:43
 * @Description: 使用 synchronized
 **/
@Slf4j
@ThreadSafe
public class LockExample1 {

    //countDownLatch模拟请求总数
    public static int clientTotal = 5000;

    //Semaphore模拟并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0;i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    //acquire判断当前线程是否允许被执行，如果达到一定并发数，add方法可能会被临时阻塞掉
                    semaphore.acquire();
                    add();
                    //释放当前进程
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception",e);
                }
                //每执行完一个线程计数总数就减1
                countDownLatch.countDown();
            });
        }
        //await方法可以保证countDownLatch必须减为0了才会调用，而减为0的前提其实就是所有线程都已经执行完
        //我们可以通过这个方法获得后续的一些输出的最终值，比如这里的count
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);

    }

    private synchronized static void add(){
        count++;
    }
}

