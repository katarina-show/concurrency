package com.learn.concurrency.example.aqs;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: Katerina
 * @Date: 2018/8/21 19:33
 * @Description:
 **/
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {

    public static final int threadhold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExample(int start,int end){
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小，就计算任务
        boolean canCompute = (end - start) <= threadhold;
        if(canCompute){
            for (int i = start; i <= end; i++){
                sum += i;
            }
        }else{
            System.out.println("任务分解");
            //如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) /2;
            //System.out.println("拆分成"+start+"一直加到"+middle+"和"+(middle+1)+"一直加到"+end);
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start,middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1,end);


            //执行子任务
            //直接使用fork方法执行时间比使用invokeAll执行时间要长
            //leftTask.fork();
            //rightTask.fork();

            //比如1个人一天可以打扫100个房间，一天需要打扫400个房间
            //使用fork：1号分配给2号和3号每人打扫200个，自己偷懒去了，2号分配给4号和5号一人100个，3号分配给6号和7号一人100个
            //         2号3号都偷懒去了，需要7个线程才能完成任务
            //使用invokeAll：1号自己打扫200个，给2号分配了200个，1号还是打扫不完，给3号分了100个，2号也给4号分了100个
            //         所有人都在干活，并且只要4个线程就可以完成任务
            invokeAll(leftTask,rightTask);

            //等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        //可以带参数表示启动的线程数，默认为CPU的线程数
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //生成一个计算任务，计算1+2+3+4
        //两个参数相当于从1一直加到100
        ForkJoinTaskExample task = new ForkJoinTaskExample(1,8);

        //执行一个任务
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);

        try {
            log.info("result:{}",result.get());
        } catch (Exception e) {
            log.error("exception",e);
        }
        System.out.println(123);

    }
}
