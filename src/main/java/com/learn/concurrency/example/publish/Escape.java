package com.learn.concurrency.example.publish;

import com.learn.concurrency.annoations.NotRecommend;
import com.learn.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Katerina
 * @Date: 2018/8/8 21:24
 * @Description: 对象逸出：当某个不该被发布的对象被发布时，这种情况称为逸出
 * 在对象未完成构造前不可以将其发布
 * 通常是Object.this **/
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
