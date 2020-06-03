package com.cumt.thread.create.way;

/**
 * 描述：实现Runnable 接口
 *
 * @author liuxs_s@163.com
 * @create 2020-06-03 21:03
 **/
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running.....");
    }
}
