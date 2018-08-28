package com.learn.concurrency.example.blockingQueue.priorityBlockingQueue;

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
            //1-100的随机整数
            int size = (int)(Math.random()*100+1);
            Goods goods = new Goods(size);
            try {
                queue.put(goods);
                System.out.println("商品做好了，放入了队列！"+goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
