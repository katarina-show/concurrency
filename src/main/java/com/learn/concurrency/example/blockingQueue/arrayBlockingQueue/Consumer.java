package com.learn.concurrency.example.blockingQueue.arrayBlockingQueue;

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


        for (int i = 0; i < 3;i++){
            try {
                Goods goods = queue.take();
                System.out.println("取走了一件队列里的商品！"+goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
