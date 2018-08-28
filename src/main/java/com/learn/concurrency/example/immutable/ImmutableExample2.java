package com.learn.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.learn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 23:20
 * @Description: 使用Collections.unmodifiableXXX来构建不可变对象
 **/
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        //通过把已有的内容拷贝到UnmodifiableMap里，把对UnmodifiableMap的所有修改方法全部改为抛异常来实现
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,3);
        log.info("{}",map.get(1));
    }

}
