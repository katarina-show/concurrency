package com.learn.concurrency.example.blockingQueue.delayQueue;

import java.util.concurrent.*;

/**
 * @Author: Katerina
 * @Date: 2018/8/23 6:36
 * @Description: 可以改变Consumer和 Producer中for循环的次数，也可使用while（true）以及更改消费者或生产者线程数来看到不同的结果
 *
 **/
public class Client {

    public static void main(String[] args) {

        final BlockingQueue<Goods> queue = new DelayQueue<>();

        ExecutorService producers = Executors.newCachedThreadPool();
        ExecutorService consumers = Executors.newCachedThreadPool();

        producers.execute(new Producer(queue));
        consumers.execute(new Consumer(queue));


        //测试CompareTo方法
/*        Goods goods1 = new Goods(1000);
        Goods goods2 = new Goods(2000);
        int i = goods1.compareTo(goods2);
        if(i > 0){
            System.out.println("goods1保质期比goods2长");
        }else if(i < 0){
            System.out.println("goods1保质期比goods2短");
        }else {
            System.out.println("goods1保质期和goods2一样长");
        }*/

    }
}
