package com.learn.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.learn.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 23:00
 * @Description: final修饰一个引用类型的变量时，它只是不能指向另外一个对象，但是它里面的值是可以修改的
 **/
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        //a = 2;
        //b = "3";
        //map = Maps.newHashMap();
        map.put(1,3);
        log.info("{}",map.get(1));

    }

    private void test(final int a){
        //a = 1;
        log.info("a={}",a);
    }
}
