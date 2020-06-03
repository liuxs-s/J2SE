package com.cumt.thread.create.way;

/**
 * 描述：线程创建方式，继承Thread类
 *
 * @author liuxs_s@163.com
 * @create 2020-06-03 21:18
 **/
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.....");
    }
}
