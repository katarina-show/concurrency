package com.learn.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Katerina
 * @Date: 2018/8/23 21:02
 * @Description: newFixedThreadPool
 **/
@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++){
            final int index = i;
            executorService.execute(() -> log.info("task:{}",index));
        }

        executorService.shutdown();
    }
}
