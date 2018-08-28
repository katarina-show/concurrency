package com.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Katerina
 * @Date: 2018/8/20 14:00
 * @Description: 此实例是为了模仿CyclicBarrier await方法源码前的Lock
 **/
public class MyLockTest {

     static ReentrantLock reentrantLock = new ReentrantLock();

    public static void haha() throws Exception{
        //如果在方法体内部new一个ReentrantLock就没有意义了（线程的堆栈封闭原则）
        //final ReentrantLock reentrantLock = new ReentrantLock()
        final ReentrantLock lock = reentrantLock;
        lock.lock();
        try {
            //先休眠一秒是为了getQueueLength能拿到正确的数值
            Thread.sleep(1000);
            System.out.println("我是"+Thread.currentThread().getName());
            if(lock.hasQueuedThreads()){
                System.out.println("有"+lock.getQueueLength()+"个线程正在等待获取锁");
            }
            System.out.println("我开始干活了");
            Thread.sleep(2000);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i =0;i < 10;i++){
            service.execute(() -> {
                try {
                    MyLockTest.haha();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();


//        Thread t1 = new Thread(() -> {
//            try {
//                MyLockTest.haha();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        t1.setName("线程1号");
//        Thread t2 = new Thread(() -> {
//            try {
//                MyLockTest.haha();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        t2.setName("线程2号");
//        t1.start();
//        t2.start();
    }

}
