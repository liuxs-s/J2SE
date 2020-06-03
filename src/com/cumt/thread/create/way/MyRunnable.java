package com.cumt.thread.create.way;

/**
 * 描述：实现Runnable 接口
 *
 * @author liuxs_s@163.com
 * @create 2020-06-03 21:03
 **/
public class MyRunnable implements Runnable{
    int i =0;
    @Override
    public void run() {
        i++;
        System.out.println(Thread.currentThread().getName() + i + " is running.....");
    }
}
