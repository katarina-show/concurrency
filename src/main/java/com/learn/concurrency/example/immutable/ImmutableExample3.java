package com.learn.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.learn.concurrency.annoations.ThreadSafe;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 23:27
 * @Description: 使用guava的ImmutableXXX方法来创建不可变对象
 **/
@ThreadSafe
public class ImmutableExample3 {

    //ImmutableList换成List也会抛异常
    private final static ImmutableList list = ImmutableList.of(1,2,3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    //两种方法初始化map
    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);

    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).put(5,6).build();

    public static void main(String[] args) {
        //list.add(4);
        //set.add(4);
        //map.put(1,4);
        //map2.put(1,4);

        System.out.println(map2.get(3));
    }
}
