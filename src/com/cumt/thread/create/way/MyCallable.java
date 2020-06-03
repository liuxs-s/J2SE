package com.cumt.thread.create.way;

import java.util.concurrent.Callable;

/**
 * 描述：实现Callable接口
 *
 * @author liuxs_s@163.com
 * @create 2020-06-03 21:26
 **/
public class MyCallable implements Callable<Integer>{
    int i = 0;
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() +" is running.....");
        return ++i;
    }
}
