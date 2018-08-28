package com.learn.concurrency.example.syncContainer;

import com.learn.concurrency.annoations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * @Author: Katerina
 * @Date: 2018/8/10 15:48
 * @Description: Vector 错误案例
 * 不要在遍历时进行一些类似remove的更新操作，单线程下已经抛出异常，何况多线程
 * 如果一定要执行，可以做好标记，for循环之后再删除
 * 同时推荐普通for进行遍历
 * Vector不行 ArrayList更是不行的
 * 可以用并发容器来代替
 **/
@NotThreadSafe
public class VectorExample3 {

    //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1){ // foreach
        for (Integer i : v1) {
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1){ // 迭代器
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //success
    private static void test3(Vector<Integer> v1){ // 普通for
        for (int i = 0; i < v1.size(); i++) {
            if(v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        test1(vector);
    }
}
