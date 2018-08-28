package com.learn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;

/**
 * @Author: Katerina
 * @Date: 2018/8/20 3:44
 * @Description: FutureTask + Thread
 **/
@Slf4j
public class FutureTaskExample1 {

    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        });

        Thread thread = new Thread(futureTask);
        thread.setName("Task Thread");
        thread.start();

        Thread.sleep(1000);

        //isDone返回true说明自己重写的run方法或者call方法已经执行完（出异常也算执行完）
        if(!futureTask.isDone()){
            log.info("Task is not finished");
        }

        String result = futureTask.get();
        log.info("result:{}",result);
    }
}
