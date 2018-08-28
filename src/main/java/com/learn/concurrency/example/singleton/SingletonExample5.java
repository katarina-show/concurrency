package com.learn.concurrency.example.singleton;

import com.learn.concurrency.annoations.ThreadSafe;


/**
 * @Author: Katerina
 * @Date: 2018/8/8 21:56
 * @Description: 线程安全的懒汉模式3 --> 双重同步锁单例模式之volatile优化
 * 单例的实例在第一次使用时进行创建
 **/
@ThreadSafe
public class SingletonExample5 {

    //私有构造函数
    private SingletonExample5(){

    }

    //当执行一个instance = new SingletonExample4()时，CPU会有如下3步
    //1.memory = allocate() 分配对象的内存空间
    //2.ctorInstance() 初始化对象
    //3.instance = memory 设置instance指向刚分配的内存
    //完成3步之后，instance指向了实际分配的内存地址，就是我们所说的引用



    //单例对象，volatile+双重检测机制 -> 禁止指令重排
    private volatile static SingletonExample5 instance = null;

    //静态工厂方法
    //性能开销
    public static SingletonExample5 getInstance(){
        if(instance == null){ //双重检测机制    //线程B发现instance不为null，直接返回，结果调用时出错！因为线程A还未初始化对象
            synchronized (SingletonExample5.class){ //同步锁
                if(instance == null){
                    instance = new SingletonExample5();//线程A执行到重排序的第三步时，还未执行第二步时
                }
            }
        }
        return instance;
    }
}
