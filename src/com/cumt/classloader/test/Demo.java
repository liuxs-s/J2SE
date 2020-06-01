package com.cumt.classloader.test;

/**
 * 描述：demo 被加载类
 *
 * @author liuxs_s@163.com
 * @create 2020-06-01 20:15
 **/
public class Demo extends DemoParent{
    private int age;
    private final Character sex = '0';
    static{
        String name = "liu";
        System.out.println("name:"+name);
    }

    public Demo(int age,String occupation, String sallay ) {
        super(occupation,sallay);
        this.age = age;
        System.out.println("age:"+age);
    }

    public Demo() {
        super();
    }

    public static void main(String[] args) {
        Demo demo = new Demo(15,"coder","10000");
        System.out.println("main is running....");
    }

    public void say(){
        System.out.println("demo say is running....");
    }
}
