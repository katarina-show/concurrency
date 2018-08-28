package com.learn.concurrency.example.singleton;

import com.learn.concurrency.annoations.Recommend;
import com.learn.concurrency.annoations.ThreadSafe;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 22:44
 * @Description: 枚举实现单例
 * 最安全的！
 **/
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{

        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只被调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}
