package com.cumt.classloader.test;

/**
 * 描述：DemoParent
 *
 * @author liuxs_s@163.com
 * @create 2020-06-01 20:24
 **/
public class DemoParent {
    static{
        final Character relation = '1';
        System.out.println("relation"+ relation);
    }
    private String occupation;

    private String Sallay;

    public DemoParent(String occupation, String sallay) {
        this.occupation = occupation;
        Sallay = sallay;
    }

    public DemoParent() {
    }
}
