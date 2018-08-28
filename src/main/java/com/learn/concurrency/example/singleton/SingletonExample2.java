package com.learn.concurrency.example.singleton;

import com.learn.concurrency.annoations.ThreadSafe;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 21:49
 * @Description: 饿汉模式
 * 单例的实例在类装载进行创建
 * 使用时应注意两个问题：
 * 1.私有构造函数内没有过多的处理
 * 2.这个类肯定会被使用
 **/
@ThreadSafe
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2(){

    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
