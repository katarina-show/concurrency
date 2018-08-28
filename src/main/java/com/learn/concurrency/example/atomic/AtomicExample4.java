package com.learn.concurrency.example.atomic;

import com.learn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 18:31
 * @Description: 使用 AtomicReference 对"对象"进行原子操作。
 **/
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);// 2
        count.compareAndSet(0,1);// no
        count.compareAndSet(1,3);// no
        count.compareAndSet(2,4);// 4
        count.compareAndSet(3,5);// no
        log.info("count:{}",count.get());
    }

}

