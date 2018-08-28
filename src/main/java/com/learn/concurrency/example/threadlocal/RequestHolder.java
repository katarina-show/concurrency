package com.learn.concurrency.example.threadlocal;

/**
 * @Author: Katerina
 * @Date: 2018/8/9 0:17
 * @Description:
 **/
public class RequestHolder {

    //Long对应线程的ID
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    //ThreadLocal会取出当前对的线程id放到内部Map的key上，我们set方法里的id是value
    //通常我们在过滤器里放进去
    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    //通常我们在拦截器里释放数据
    public static void remove(){
        requestHolder.remove();
    }
}
