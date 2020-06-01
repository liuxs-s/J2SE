package com.cumt.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 描述：String、StringBuilder、StringBuffer
 *
 * @author liuxs_s@163.com
 * @create 2020-06-01 21:24
 **/
public class SString {
    /**
     1.在Java中，String是一个引用类型，它本身也是一个class。但是因为String太常用了，Java编译器对String
     有特殊处理，即可以直接用"..."来表示一个字符串。
     2.Java字符串的一个重要特点就是字符串不可变，因为其底层实现是private final char[] value，以及没有任何修改char[]的方法。
        final成员可以在构造函数里赋值,String 的final char[] 就是在构造函数里赋值的
     3.比较两个字符串是否相同时，因为是引用类型，必须使用equals()方法而不能用==
     4.Java编译器对String做了特殊处理，使得我们可以直接用+拼接字符串。
     5.小结
     Java字符串String是不可变对象；
     字符串操作不改变原字符串内容，而是返回新字符串；见concat（）方法，其实+连接也一样
     常用的字符串操作：提取子串、查找、替换、大小写转换等；
     Java使用Unicode编码表示String和char
     CharSequence（字符序列） 是String大类的一个父接口

     2.1 虽然可以直接拼接字符串，但是，在循环中，每次循环都会创建新的字符串对象，然后扔掉旧的字符串。这样，
     绝大部分字符串都是临时对象，不但浪费内存，还会影响GC效率。为了能高效拼接字符串，Java标准库提供了StringBuilder，
         它是一个可变对象，可以预分配缓冲区，这样，往StringBuilder中新增字符时，不会创建新的临时对象：
     2.2 StringBuilder还可以进行链式操作,进行链式操作的关键是，定义的append()方法会返回this，这样，就可以不断调用自身的其他方法。
     2.3 你可能还听说过StringBuffer，这是Java早期的一个StringBuilder的线程安全版本，它通过同步来保证多个线程操作StringBuffer
     也是安全的，但是同步会带来执行速度的下降。StringBuilder和StringBuffer接口完全相同，现在完全没有必要使用StringBuffer。
     2.4 小结
     StringBuilder是可变对象，用来高效拼接字符串；
     StringBuilder可以支持链式操作，实现链式操作的关键是返回实例本身；
     StringBuffer是StringBuilder的线程安全版本，现在很少使用。

     3.1 类似用分隔符拼接数组,可以采用StringJoiner
     3.2 StringJoiner内部实际上就是使用了StringBuilder，所以拼接效率和StringBuilder几乎是一模一样的。
     3.3 String还提供了一个静态方法join()，这个方法在内部使用了StringJoiner来拼接字符串，
     在不需要指定“开头”和“结尾”的时候，用String.join()更方便
     */
    @Test
    public void main(){
        String s = "Hello";
        System.out.println(s);
        s = s.toUpperCase();
        System.out.println(s);
        s.contains("el");
        s.charAt(0);
        System.out.println(s.codePointAt(2));
        StringBuilder sb = new StringBuilder(1024);
        sb.append("Mr ")
                .append("Bob")
                .append("!")
                .insert(0, "Hello, ");
        System.out.println(sb.toString());


        String[] names = {"Bob", "Alice", "Grace"};
        StringBuilder sbs = new StringBuilder();
        sbs.append("Hello ");
        for (String name : names) {
            sbs.append(name).append(", ");
        }
        // 注意去掉最后的", ":
        sbs.delete(sbs.length() - 2, sbs.length());
        sbs.append("!");
        System.out.println(sbs.toString());

        //使用StringJoiner 实现
        String[] namess = {"Bob", "Alice", "Grace"};
        StringJoiner sj = new StringJoiner(", ");
        new StringJoiner(", ", "Hello ", "!");//可以指定“开头”和“结尾”
        for (String name : namess) {
            sj.add(name);
        }
        System.out.println(sj.toString());
    }

/*    public String concat(String str) {
        int otherLen = str.length();
        if (otherLen == 0) {
            return this;
        }
        int len = value.length;
        char buf[] = Arrays.copyOf(value, len + otherLen);
        str.getChars(buf, len);
        return new String(buf, true);
    }*/
}
