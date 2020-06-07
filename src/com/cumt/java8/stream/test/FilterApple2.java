package com.cumt.java8.stream.test;

import com.cumt.java8.stream.pojo.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * 描述：继续以农场主为例，介绍lambda表达式
 * 主要是介绍Predicate（断言）、Runnable（运行）、Consumer（消费者）、Function（函数）、Supplier(提供者)
 *
 * Predicate： boolean test(T t)  也就是传入一个值，最终返回的是一个boolean类型
 * BiPredicate<T, U>：boolean test(T t, U u)  也就是传入两个值，最终返回的是一个boolean类型
 * DoublePredicate： boolean test(double value)  传入一个double类型的值，返回的是一个boolean类型
 *
 * Consumer: void accept(T t) 也就是传入一个值，最终返回的是void,比如在这个里面用的最多的 filterApples.forEach(System.out::println);
 *
 * Function<T, R>:  R apply(T t) 也就是传入一个值，最终返回的是另外一个类型的值
 *
 * Supplier: T get()
 * @author liuxs_s@163.com
 * @create 2020-06-06 10:34
 **/
public class FilterApple2 {


    public static List<Apple> filterApples(List<Apple> appleList, Predicate<Apple> predicate){
        ArrayList result = new ArrayList();
        for(Apple apple:appleList){
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples2(List<Apple> appleList, DoublePredicate doublePredicate){
        ArrayList result = new ArrayList();
        for(Apple apple:appleList){
            if(doublePredicate.test(apple.getWeight())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples3(List<Apple> appleList, BiPredicate<String,Double> biPredicate){
        ArrayList result = new ArrayList();
        for(Apple apple:appleList){
            if(biPredicate.test(apple.getColor(),apple.getWeight())){
                result.add(apple);
            }
        }
        return result;
    }

    public static void filterApples4(List<Apple> appleList, Consumer<Apple> consumer){
        for(Apple apple:appleList){
            consumer.accept(apple);
        }
    }

    public static String testFunction(List<Apple> appleList, Function<List<Apple>,String> function){
        return function.apply(appleList);
    }

    /**
     * Predicate这几个接口在使用他们时，其实是用到了Java中的策略模式
     * 不用lambda表达式的写法
     */
    static class ApplePredicate implements Predicate<Apple>{
        @Override
        public boolean test(Apple apple) {
            if("red".equals(apple.getColor())){
                return true;
            }
            return false;
        }
    }

    public static boolean isRedApple(Apple apple){
        return "red".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Double weight){
        return weight > 200.0;
    }

    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("red", 300.2),
                new Apple("red", 201.5),
                new Apple("yello", 350.9)
        );
        List<Apple> filterApples = filterApples(appleList, new ApplePredicate());
        System.out.println("----------------不使用lambda表达式,对Predicate接口中方法通过实现类实现-----------------");
        filterApples.forEach(System.out::println);


        //使用lambda表达式
        //所以说用到策略模式的时候可以考虑使用这几个现成的接口，当然也可以自定义接口
        //Predicate 中 boolean test(T t);就是接受一个参数（apple），返回一个布尔值（true/false）
        List<Apple> filterApples1 = filterApples(appleList, apple -> "red".equals(apple.getColor()));
        System.out.println("----------------使用lambda表达式对Predicate接口的中方法实现-----------------");
        filterApples1.forEach(System.out::println);

        List<Apple> filterApples2 = filterApples(appleList, FilterApple2::isRedApple);
        System.out.println("--------------------使用方法引用的方式------------------------");
        filterApples2.forEach(System.out::println);

        List<Apple> filterApples3 = filterApples2(appleList, w -> w > 200);
        List<Apple> filterApples4 = filterApples2(appleList, FilterApple2::isHeavyApple);
        System.out.println("----------------------filterApples3----------------------");
        filterApples3.forEach(System.out::println);
        System.out.println("---------------------filterApples4-----------------------");
        filterApples4.forEach(System.out::println);

        List<Apple> filterApples5 = filterApples3(appleList, (c,w) -> "red".equals(c)&& w > 250.0);
        System.out.println("-----------------------filterApples5---------------------");
        filterApples5.forEach(System.out::println);

        System.out.println("-----------------------Consumer---------------------");
        filterApples.forEach(o -> {});
        filterApples.forEach(o -> {int i;});
        filterApples4(appleList,apple -> System.out.println(apple));

        System.out.println("---------------------testFunction-----------------------");
        String s = testFunction(appleList, apples -> apples.toString());
        System.out.println(s);

        System.out.println("---------------------Supplier-----------------------");
        Supplier<String> stringSupplier = String::new;
        Supplier<Apple> appleSupplier = Apple::new;
        System.out.println(stringSupplier.get().getClass());
    }
}
