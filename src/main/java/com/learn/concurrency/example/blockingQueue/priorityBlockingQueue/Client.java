package com.learn.concurrency.example.blockingQueue.priorityBlockingQueue;

import java.util.concurrent.*;

/**
 * @Author: Katerina
 * @Date: 2018/8/22 4:00
 * @Description: 可以改变Consumer和 Producer中for循环的次数，也可使用while（true） 以及更改消费者或生产者线程数来看到不同的结果
 **/
public class Client {

    public static void main(String[] args) {

        final BlockingQueue<Goods> queue = new PriorityBlockingQueue<>();

        ExecutorService producers = Executors.newCachedThreadPool();
        ExecutorService consumers = Executors.newCachedThreadPool();

        producers.execute(new Producer(queue));
        consumers.execute(new Consumer(queue));
        //如果少一个消费者线程，队列里就会一直有2个Goods
        //consumers.execute(new Consumer(queue));
    }
}
