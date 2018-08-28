package com.learn.concurrency.example.singleton;


import com.learn.concurrency.annoations.NotThreadSafe;


/**
 * @Author: Katerina
 * @Date: 2018/8/8 21:56
 * @Description: 线程安全的懒汉模式2 --> 双重同步锁单例模式
 * 单例的实例在第一次使用时进行创建
 **/
@NotThreadSafe
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4(){

    }

    //当执行一个instance = new SingletonExample4()时，CPU会有如下3步
    //1.memory = allocate() 分配对象的内存空间
    //2.ctorInstance() 初始化对象
    //3.instance = memory 设置instance指向刚分配的内存
    //完成3步之后，instance指向了实际分配的内存地址，就是我们所说的引用

    //JVM和CPU优化，发生了指令重排132 正是由于指令重排才让 双重同步锁单例模式【线程不安全】

    //1.memory = allocate() 分配对象的内存空间
    //3.instance = memory 设置instance指向刚分配的内存
    //2.ctorInstance() 初始化对象

    //

    //单例对象
    private static SingletonExample4 instance = null;

    //静态工厂方法
    public static SingletonExample4 getInstance(){
        if(instance == null){ //双重检测机制    //线程B发现instance不为null，直接返回，结果调用时出错！因为线程A还未初始化对象
            synchronized (SingletonExample4.class){ //同步锁
                if(instance == null){
                    instance = new SingletonExample4();//线程A执行到重排序的第三步时，还未执行第二步时
                }
            }
        }
        return instance;
    }
}
