package com.learn.concurrency.example.blockingQueue.priorityBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: Katerina
 * @Date: 2018/8/22 4:00
 * @Description: 消费者
 **/
public class Consumer implements Runnable{

    private BlockingQueue<Goods> queue;

    public Consumer(BlockingQueue<Goods> queue){
        this.queue = queue;
    }

    @Override
    public void run() {


        for (int i = 0; i < 5;i++){

            try {
                //睡2秒，是为了方便看取出时候的顺序是否按优先级来的
                Thread.sleep(2000);
                Goods goods = queue.take();
                System.out.println("取走了一件队列里的商品！"+goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
