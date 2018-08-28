package com.learn.concurrency.example.blockingQueue.synchronousQueue;

import java.util.Random;
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

        while (true){
            Goods goods = new Goods();
            int time = new Random().nextInt(3000);

            try {
                Thread.sleep(time);
                System.out.println("花了"+time+"ms做好了一件商品");
                queue.put(goods);
                System.out.println("商品做好了，放入了队列！"+goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
