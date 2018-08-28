package com.learn.concurrency.example.blockingQueue.delayQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: Katerina
 * @Date: 2018/8/23 6:30
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
            long j = 1000*(i+1);
            Goods goods = new Goods(j);
            goods.setName("商品"+i+"号");
            try {
                queue.put(goods);
                System.out.println("商品做好了，放入了队列！"+goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
