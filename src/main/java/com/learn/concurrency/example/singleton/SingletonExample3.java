package com.learn.concurrency.example.singleton;

import com.learn.concurrency.annoations.NotRecommend;
import com.learn.concurrency.annoations.ThreadSafe;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 21:56
 * @Description: 线程安全的懒汉模式1
 * 单例的实例在第一次使用时进行创建
 **/
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3(){

    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态工厂方法
    //性能开销
    public static synchronized SingletonExample3 getInstance(){
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
