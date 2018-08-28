package com.learn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author: Katerina
 * @Date: 2018/8/20 3:44
 * @Description: FutureTask + ExecutorService
 **/
@Slf4j
public class FutureTaskExample2 {

    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        });

        ExecutorService executor = Executors.newCachedThreadPool();

        executor.submit(futureTask);

        Thread.sleep(2000);

        if(!futureTask.isDone()){
            log.info("Task is not finished");
        }

        String result = futureTask.get();
        log.info("result:{}",result);

        executor.shutdown();
    }
}
