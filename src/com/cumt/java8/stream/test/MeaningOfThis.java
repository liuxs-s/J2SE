package com.cumt.java8.stream.test;

/**
 * 描述：匿名内部类容易混淆的例子
 *
 * @author liuxs_s@163.com
 * @create 2020-06-06 8:47
 **/
public class MeaningOfThis {
    public final int value = 4;
    public void doIt()
    {
        int value = 6;
        Runnable r = new Runnable(){
            public final int value = 5;
            @Override
            public void run(){
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }
    public static void main(String...args)
    {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt(); // ???
    }
}
