package com.learn.concurrency.example.syncContainer;

import com.learn.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @Author: Katerina
 * @Date: 2018/8/10 15:48
 * @Description: Vector 即使是同步容器也不是所有场合下都能做到线程安全
 **/
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    //thread2在运行下面这行时，比如size=9，而thread1此时刚好将9的remove了
                    //这时get（9）就会数组下标越界异常
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
