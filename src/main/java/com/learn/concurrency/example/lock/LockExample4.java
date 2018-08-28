package com.learn.concurrency.example.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @Author: Katerina
 * @Date: 2018/8/20 3:06
 * @Description: 从源码中的例子中copy出来的 StampedLock
 * StampedLock则提供了一种乐观的读策略,这种乐观策略的锁非常类似于无锁的操作,使得乐观锁完全不会阻塞写线程
 **/
public class LockExample4 {
    class Point {
        private double x, y;//内部定义表示坐标点
        private final StampedLock sl = new StampedLock();

        void move(double deltaX, double deltaY) { // an exclusively locked method
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        //乐观读锁案例
        double distanceFromOrigin() { // 只读方法
            //尝试一次乐观读，返回一个类似于时间戳的邮戳整数stamp 这个stamp就可以作为这一个锁获取的凭证
            //使用这种锁的读不阻塞写
            long stamp = sl.tryOptimisticRead();
            double currentX = x, currentY = y;  //将两个字段读入本地局部变量
            //判断这个stamp是否在读过程发生期间被修改过,如果stamp没有被修改过,认为读取有效
            //true:无写锁，state与stamp匹配
            //反之，意味着在读取的过程中,可能被其他线程改写了数据
            //false:有写锁，state与stamp不匹配，或者stamp=0（调用tryOptimisticRead()时已经被其他线程持有写锁）
            //因此,有可能出现脏读,如果如果出现这种情况,我们可以像CAS操作那样在一个死循环中一直使用乐观锁,直到成功为止
            if (!sl.validate(stamp)) {
                stamp = sl.readLock();  //可以升级锁的级别,这里我们将乐观锁变为悲观锁, 如果当前对象正在被修改,则读锁的申请可能导致线程挂起.
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }

        //悲观读锁案例
        void moveIfAtOrigin(double newX, double newY) { // upgrade
            // Could instead start with optimistic, not read mode
            long stamp = sl.readLock();
            try {
                while (x == 0.0 && y == 0.0) { //循环，检查当前状态是否符合
                    long ws = sl.tryConvertToWriteLock(stamp); //将读锁转为写锁
                    if (ws != 0L) { //这是确认转为写锁是否成功
                        stamp = ws; //如果成功 替换票据
                        x = newX; //进行状态改变
                        y = newY; //进行状态改变
                        break;
                    } else { //如果不能成功转换为写锁
                        sl.unlockRead(stamp);  //我们显式释放读锁
                        stamp = sl.writeLock();  //显式直接进行写锁 然后再通过循环再试
                    }
                }
            } finally {
                sl.unlock(stamp); //释放读锁或写锁
            }
        }
    }
}

