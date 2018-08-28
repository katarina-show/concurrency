package com.learn.concurrency.example.blockingQueue.priorityBlockingQueue;


import lombok.Getter;
import lombok.ToString;

/**
 * @Author: Katerina
 * @Date: 2018/8/22 4:07
 * @Description: 商品类
 **/
@ToString
public class Goods implements Comparable<Goods>{


    //商品规格大小
    @Getter
    private int size;

    public Goods(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(Goods o) {
        return this.size > o.size ? 1 : ((this.size == o.size) ? 0 : -1);
    }
}
