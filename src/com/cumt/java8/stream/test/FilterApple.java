package com.cumt.java8.stream.test;

import com.cumt.java8.stream.pojo.Apple;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 描述：以“在农厂工作，需要对水果进行过滤统计，并且怎么很轻松的应对农场主给出的各种不同的统计需求"为例，一步步
 *
 * @author liuxs_s@163.com
 * @create 2020-06-05 21:34
 **/
public class FilterApple {

    /**
     *需求一：找出所有苹果中绿色的苹果
     */
    public static List<Apple> filterGreenApple(List<Apple> list){
        ArrayList<Apple> result = new ArrayList<>();
        for(Apple apple:list){
            if(apple.getColor().equals("green")){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     *需求二：如果此时农场主又来了一个需求，找出所有颜色为红色的苹果，如果再让找出颜色为红色的呢
     */
    public static List<Apple> filterRedApple(List<Apple> list){
        ArrayList<Apple> result = new ArrayList<>();
        for(Apple apple:list){
            if(apple.getColor().equals("red")){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 但是显然这种方式应对万变的需求不是特别好，如果还要找其它条件的苹果那还得不断增加方法,
     * 比如说要查找重量大于20的
     * @param list
     * @param color
     * @return
     */
    public static List<Apple> filterAppleColor(List<Apple> list,@NotNull String color){
        ArrayList<Apple> result = new ArrayList<>();
        for(Apple apple:list){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 筛选出重一些的苹果
     * @param list
     * @param weight
     * @return
     */
    public static List<Apple> filterHeavyApple(List<Apple> list,Double weight){
        ArrayList<Apple> result = new ArrayList<>();
        for(Apple apple:list){
            if(apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 找出在20g以上的所有红色的苹果"，也就是条件既有重量，也有颜色
     * 不断的新需求其实代码是很不容易维护的
     * @param list
     * @param weight
     * @return
     */
    public static List<Apple> filterAppleByColorAndWeight(List<Apple> list,Double weight,String color){
        Collections collections;
        ArrayList<Apple> result = new ArrayList<>();
        for(Apple apple:list){
            if(apple.getWeight() > weight && apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    //我们可以定义一个策略模式,简化我们代码的书写
    /**
     * 策略模式接口
     * JAVA8的概念：就是在一个接口中如果只有一个方法，其实就是一个Functional,这时就可以用Annotation-@FunctionalInterface来标注它
     * 并非是只有存在一个方法的接口才能使用这个注解，default的方法和static方法和继承自Object类中的方法也可以定义在这个接口中。
     * 另外，对于一个具有Functional的接口如果不写这个Annotation也是可以的,只是代码可读性上没有加上好
     */
    @FunctionalInterface
    interface AppleFilter{
        boolean filter(Apple apple);
        default void test(){
            System.out.println("interface中的default");
        }
        static void test2(){

        }
    }

    /**
     * 具体策略的实现
     */
    public static class RedAnd20WeightAppleFilter implements AppleFilter{

        @Override
        public boolean filter(Apple apple) {
            if("red".equals(apple.getColor())&& 20 < apple.getWeight()){
                return true;
            }
            return false;
        }
    }

    /**
     * 如何使用策略模式呢？
     * 扩展：Java中的Predicate用的就是策略模式
     *
     *  @FunctionalInterface
     * public interface Predicate<T> {
     *      boolean test(T t);
     * }
     */
    public static List<Apple> filterApples(List<Apple> list,AppleFilter appleFilter){
        ArrayList<Apple> result = new ArrayList<>();
        for(Apple apple:list){
            //如果过滤为true的话，这样同样想到了Java中的策略模式Predicate(断言、断定)
            Predicate predicate;
            if(appleFilter.filter(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("red", 20.1), new Apple("green", 15.0),
                new Apple("yello", 12.0),new Apple("red", 10.1));

        //找出所有颜色为绿色的苹果
        List<Apple> greenAppleList = filterGreenApple(apples);
        greenAppleList.forEach(System.out::println);

        List<Apple> redAppleList = filterRedApple(apples);
        redAppleList.forEach(System.out::println);

        List<Apple> yelloAppleList = filterAppleColor(apples,"yello");
        yelloAppleList.forEach(System.out::println);

        List<Apple> heavyAppleList = filterHeavyApple(apples,20.0);
        heavyAppleList.forEach(System.out::println);

        //使用策略模式,如果是其它条件则新建子类去继承接口去定义就好了，但是！！这种方式也不太好，代码量大，而且不够简洁
        List<Apple> appleList = filterApples(apples, new RedAnd20WeightAppleFilter());
        appleList.forEach(System.out::println);

        //我们还可以使用匿名内部类方式实现策略模式的接口，但是匿名内部类容易混淆，比如MeaningOfThis 类
        List<Apple> appleList1 = filterApples(apples, new AppleFilter() {

            @Override
            public boolean filter(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        appleList1.forEach(System.out::println);

        //然后我们可以使用lambda表达式啊，而且使用Lambda表达式会比使用上面匿名内部类或子类继承的方式更节省空间
        List<Apple> appleList2 = filterApples(apples, (apple) -> "yello".equals(apple.getColor()));
        appleList2.forEach(System.out::println);
    }

}
