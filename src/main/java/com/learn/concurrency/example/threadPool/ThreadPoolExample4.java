package com.learn.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Katerina
 * @Date: 2018/8/23 21:03
 * @Description: newScheduledThreadPool 已经不是普通的线程池了，有了调度的概念
 **/
@Slf4j
public class ThreadPoolExample4 {

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);


    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        //1.普通使用
        //executorService.schedule(() -> log.warn("schedule run"),3, TimeUnit.SECONDS);
        //executorService.shutdown();

        //2.scheduleAtFixedRate 以指定速率运行任务 延迟1s后 每隔3秒执行 一直执行，不宜直接shutdown
        //executorService.scheduleAtFixedRate(() -> log.warn("schedule run"),1,3,TimeUnit.SECONDS);

        //3.scheduleWithFixedDelay 每次把任务执行完成后,再延迟固定时间后再执行下一次
        //和2的区别：2是不管3秒内有没有执行玩run方法里的内容，都会再执行一遍
        //executorService.scheduleWithFixedDelay(() -> log.warn("schedule run"),1,3,TimeUnit.SECONDS);



        //java中的timer和这个线程池很像
        Timer timer = new Timer();
        //指定第一次执行的时间是new Date，每间隔5s执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        },new Date(),5*1000);
    }
}
