package com.learn.concurrency.example.atomic;

import com.learn.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 * @Author: Katerina
 * @Date: 2018/8/8 18:36
 * @Description: 使用 AtomicIntegerFieldUpdater,以原子性更新某个类的实例的指定某个字段
 **/
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    //字段必须被volatile修饰，且不能是static的
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if(updater.compareAndSet(example5,100,120)){
            log.info("update success 1,{}",example5.getCount());
        }

        if(updater.compareAndSet(example5,100,120)){
            log.info("update success 2,{}",example5.getCount());
        }else {
            log.info("update failed,{}",example5.getCount());
        }
    }

}

