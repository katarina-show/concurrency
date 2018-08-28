package com.learn.concurrency.example.blockingQueue.delayQueue;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Katerina
 * @Date: 2018/8/23 6:29
 * @Description: 商品类
 **/
@Data
public class Goods implements Delayed {

    private String name;

    //延迟时间
    private long delayTime;

    //到期时间
    private long expireTime;

    public Goods(long delayTime) {
        this.delayTime = delayTime;
        this.expireTime = System.currentTimeMillis() + delayTime;
    }

    //该方法 用于计算当前时间到执行时间之间还有多长时间
    //延时为0时，此元素延时期满，可从take()取出
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    //判断队列中元素的顺序谁前谁后
    @Override
    public int compareTo(Delayed o) {
        if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
            return 1;
        }else if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
            return -1;
        }
        return 0;
    }
}
