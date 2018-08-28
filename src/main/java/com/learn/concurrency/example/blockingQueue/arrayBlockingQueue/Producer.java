package com.learn.concurrency.example.blockingQueue.arrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: Katerina
 * @Date: 2018/8/22 4:00
 * @Description: 生产者
 **/
public class Producer implements Runnable{

    private BlockingQueue<Goods> queue;

    public Producer(BlockingQueue<Goods> queue){
        this.queue = queue;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5;i++){
            Goods goods = new Goods();
            try {
                queue.put(goods);
                System.out.println("商品做好了，放入了队列！"+goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
