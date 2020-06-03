package com.cumt.thread.create;

import com.cumt.thread.create.way.MyCallable;
import com.cumt.thread.create.way.MyRunnable;
import com.cumt.thread.create.way.MyThread;

import java.util.concurrent.*;

/**
 * 描述：线程学习一：线程的创建方式
 * 实现 Runnable 接口(lambda表达式，内部类)；
 * 继承Thread类
 * 实现 Callable 接口；
 * 线程池
 *   使用线程池中线程对象的步骤：
 *       1.创建线程池对象
 *       2.创建Runnable接口子类对象
 *       3.提交Runnable接口子类对象
 *       4.关闭线程池
 *
 *
 * @author liuxs_s@163.com
 * @create 2020-06-03 20:59
 **/

@SuppressWarnings("ALL")
public class SThread {
    public static void main(String[] args) {
        //方式一：实现Runnable接口
        Thread t1 = new Thread(new MyRunnable());
        t1.setName("t1");
        t1.start();

        //方式一：实现Runnable接口
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running.....");
            }
        });
        t2.setName("t2");
        t2.start();

        //方式一：实现Runnable接口
        Thread t3 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " is running....."));
        t3.setName("t3");
        t3.start();

        //方式一：实现Runnable接口,方法引用
        Thread t4 = new Thread(SThread::runs);
        t4.setName("t4");
        t4.start();

        //方式二：继承Thread类
        MyThread t5 = new MyThread();
        t5.setName("t5");
        t5.start();

        //方式二：继承Thread类
        Thread t6 = new Thread("t6"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running.....");
            }
        };
        t6.start();

        //方式三：实现 Callable 接口
        MyCallable myCallable = new MyCallable();
        //需要一个有返回值的Runable接口的实现类
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        Thread t7 = new Thread(futureTask,"t7");
        t7.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        new Thread(new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " is running.....");
                return null;
            }
        }),"t8").start();

        new Thread(new FutureTask<Integer>(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.....");
            return null;
        }),"t9").start();

        new Thread(new FutureTask<Integer>(SThread::runs2),"t10").start();

        //方式4：线程池方式
        ExecutorService executorService0 = Executors.newFixedThreadPool(10);
        MyRunnable r = new MyRunnable();
        for(int i = 0;i < 10;i++){
            executorService0.submit(r);
        }
        executorService0.shutdown();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        MyRunnable myRunnable = new MyRunnable();
        for(int i = 0;i < 10;i++){
            executorService1.execute(myRunnable);
        }
        executorService1.shutdown();

        ExecutorService executorService2 = Executors.newCachedThreadPool();
        ExecutorService executorService3 = Executors.newScheduledThreadPool(10);
        ExecutorService executorService4 = Executors.newWorkStealingPool();


    }

    public static void runs(){
        System.out.println(Thread.currentThread().getName() + " is running.....");
    }

    public static Integer runs2(){
        System.out.println(Thread.currentThread().getName() + " is running.....");
        return 100;
    }
}
