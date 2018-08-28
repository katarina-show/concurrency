package com.learn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: Katerina
 * @Date: 2018/8/20 3:38
 * @Description: Future + ExecutorService
 **/
@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newCachedThreadPool();
        //Callable只能在ExecutorService的线程池中跑
        Future<String> future = executorService.submit(new MyCallable());
        Thread.sleep(1000);
        log.info("do something in main");
        //当未处理完成时，get方法会一直阻塞
        String result = future.get();
        log.info("result:{}",result);
        executorService.shutdown();
    }
}
