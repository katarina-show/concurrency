package com.learn.concurrency.example.lock;

import com.learn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Katerina
 * @Date: 2018/8/20 2:51
 * @Description: 使用 ReentrantReadWriteLock 实现悲观读取
 * 使用时担心出现并发问题，就可以分别加上读锁和写锁
 **/
@Slf4j
public class LockExample3 {

    private final Map<String,Object> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //定义读锁
    private final Lock readLock = lock.readLock();

    //定义写锁
    private final Lock writeLock = lock.writeLock();

    public Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    //在没有任何读锁的时候才可以进行写入操作（readLock要先unlock，writeLock才能lock）
    //如果有大量的读线程,有可能引起写线程的饥饿（拿不到锁）
    public Object put(String key,Object value){
        writeLock.lock();
        try {
            return map.put(key,value);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }
}

