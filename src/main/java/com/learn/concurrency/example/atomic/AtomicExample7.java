package com.learn.concurrency.example.atomic;

import com.learn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;


/**
 * @Author: Katerina
 * @Date: 2018/8/8 19:12
 * @Description: 使用 AtomicStampedReference,解决ABA问题
 *
 **/
@Slf4j
@ThreadSafe
public class AtomicExample7 {

    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "bbb";
        AtomicStampedReference<String> reference = new AtomicStampedReference<>(str1,1);
        //getStamp方法获取版本号
        //compareAndSet四个参数分别是旧的对象，将要修改的新的对象，原始的版本号，新的版本号
        //这个操作如果成功就会将expectedReference修改为newReference，将版本号expectedStamp修改为newStamp
        reference.compareAndSet(str1,str2,reference.getStamp(),reference.getStamp()+1);
        //getReference 获取引用对象
        System.out.println("reference.getReference() = " + reference.getReference());

        //attemptStamp 如果引用对象为期望值，则重新设置新的版本号
        boolean b = reference.attemptStamp(str2, reference.getStamp() + 1);
        System.out.println("b: "+b);
        System.out.println("reference.getStamp() = "+reference.getStamp());
    }



}

