package com.learn.concurrency.example.singleton;

import com.learn.concurrency.annoations.NotThreadSafe;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 21:49
 * @Description: 懒汉模式
 * 单例的实例在第一次使用时进行创建
 **/
@NotThreadSafe
public class SingletonExample1 {

    //私有构造函数
    private SingletonExample1(){

    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态工厂方法
    public static SingletonExample1 getInstance(){
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
