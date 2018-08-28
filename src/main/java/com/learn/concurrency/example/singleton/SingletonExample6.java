package com.learn.concurrency.example.singleton;

import com.learn.concurrency.annoations.ThreadSafe;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 21:49
 * @Description: 饿汉模式2
 * 单例的实例在类装载进行创建
 * 使用时应注意两个问题：
 * 1.私有构造函数内没有过多的处理
 * 2.这个类肯定会被使用
 **/
@ThreadSafe
public class SingletonExample6 {

    //私有构造函数
    private SingletonExample6(){

    }

    //单例对象,这行放在static代码块之前
    private static SingletonExample6 instance = null;

    //静态代码块是按顺序执行的
    static {
        instance = new SingletonExample6();
    }




    //静态工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
