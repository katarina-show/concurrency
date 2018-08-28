package com.learn.concurrency.example.publish;

import com.learn.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 21:20
 * @Description: 多线程下都可以通过公有的getStates方法来到数组并进行修改
 * 这是一种线程不安全的对象发布
 **/
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
