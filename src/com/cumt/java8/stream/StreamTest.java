package com.cumt.java8.stream;

import com.cumt.java8.stream.pojo.Student;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述：java8新特性之stream流操作
 * stream流操作依赖于lambda表达式，关于lambda表达式在thread操作中已经介绍，并且还介绍了java8的方法引用，这些都属于java8的新特性
 * 1.创建stream流的几种方式
 * 2.stream流的中间操作
 * 3.stream流的终止操作
 *
 *
 * @author liuxs_s@163.com
 * @create 2020-06-04 20:07
 **/
public class StreamTest {


    /**
     * Stream的入门使用：统计list集合中字符串长度大于2的元素个数
     */
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("足球");
        list.add("篮球");
        list.add("排球");
        list.add("羽毛球");
        list.add("乒乓球");
        long count = 0;
        for(String s :list){
            if(s.length() > 2){
                count++;
            }
        }
        System.out.println(count);
        System.out.println(list.stream().filter((s) -> s.length() > 2).count());
    }

    @Test
    /**
     * 介绍获取流的方式:掌握前两种和Stream.of()就可以，BufferReader里面的是IO的时候才可能会用到
     */
    public void fun1() throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        list.add("足球");
        list.add("篮球");
        list.add("排球");
        list.add("羽毛球");
        list.add("乒乓球");
        //获取流的方式一：使用Collection接口中stream（）方法
        //获取一个顺序流
        Stream<String> stream = list.stream();
        //获取一个并行流
        Stream<String> parallelStream = list.parallelStream();

        //获取流的方式二：使用Arrays类中stream（）方法，Arrays类中重载了很多有关于stream（）的操作
        Integer[] nums = new Integer[10];
        Stream<Integer> stream2 = Arrays.stream(nums);

        //获取流的方式三：使用Stream中的静态方法：of();    iterate()、generate()，后面这两个方法其实是stream流的使用
        Stream<List<String>> stream3 = Stream.of(list);
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2).limit(6);
        // 0 2 4 6 8 10
        stream4.forEach(System.out::print);
        Stream<Double> stream5 = Stream.generate(Math::random).limit(2);

        //获取流的方式四：通过BufferedReader 里面的lines方法
        BufferedReader reader = new BufferedReader(new FileReader("D:\\资料\\stream.txt"));
        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::print);
    }

    /**
     * 介绍流的中间操作一：切片、过滤
     */
    @Test
    public void fun2(){
        int[] array = {6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14};


        IntStream stream = Arrays.stream(array);
                    //6 6 7 9 8 10 12 14 14  filter：过滤流中的某些元素
        IntStream newStream = stream.filter(s -> s > 5)
                    //6 7 9 8 10 12 14    distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
                .distinct()
                    //9 8 10 12 14    skip(n)：跳过n元素，配合limit(n)可实现分页
                .skip(2)
                    //9 8   limit(n)：获取n个元素
                .limit(2);
        newStream.forEach(System.out::println);
    }

    /**
     * 介绍流的中间操作二：映射 map flatMap
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */
    @Test
    public void fun3(){
        List<String> list = Arrays.asList("a,b,c", "1,2,3");

        //将每个元素转成一个新的且不带逗号的元素，函数式接口定义每个元素如何映射
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
        // abc  123
        s1.forEach(System.out::println);

        Stream<String> s3 = list.stream().flatMap(s -> {
            //将每个元素转换成一个stream
            String[] split = s.split(",");
            //这一步不好理解，我以为是转换为String[]就完了
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        // a b c 1 2 3
        s3.forEach(System.out::print);
    }

    /**
     * 介绍流的中间操作三：排序
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：定制排序，自定义Comparator排序器
     */
    @Test
    public void fun4(){

        List<String> list = Arrays.asList("aa", "ff", "dd");
        //流中元素String,String类自身已经实现Compareable接口  aa dd ff
        list.stream().sorted().forEach(System.out::println);

        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        Student s3 = new Student("aa", 30);
        Student s4 = new Student("dd", 40);
        List<Student> studentList = Arrays.asList(s1, s2, s3, s4);

        //自定义排序：先按姓名升序，姓名相同则按年龄升序
        studentList.stream().sorted(
                (o1, o2) -> {
                    if (o1.getName().equals(o2.getName())) {
                        return o1.getAge() - o2.getAge();
                    } else {
                        return o1.getName().compareTo(o2.getName());
                    }
                }
        ).forEach(System.out::println);
    }

    /**
     * 介绍流的中间操作四
     * peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；
     * 而peek接收的是Consumer表达式，没有返回值。
     */
    @Test
    public void fun5(){
        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        List<Student> studentList = Arrays.asList(s1, s2);

        studentList.stream()
                .peek(o -> o.setAge(100))
                .forEach(System.out::println);
    }

    /**
     * 流的终止操作：匹配、聚合
     *  allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
     *         noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
     *         anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
     *         findFirst：返回流中第一个元素
     *         findAny：返回流中的任意元素
     *         count：返回流中元素的总个数
     *         max：返回流中元素最大值
     *         min：返回流中元素最小值
     */
    @Test
    public void fun6(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        boolean allMatch = list.stream().allMatch(e -> e > 10); //false
        boolean noneMatch = list.stream().noneMatch(e -> e > 10); //true
        boolean anyMatch = list.stream().anyMatch(e -> e > 4);  //true

        Integer findFirst = list.stream().findFirst().get(); //1
        Integer findAny = list.stream().findAny().get(); //1

        long count = list.stream().count(); //5
        Integer max = list.stream().max(Integer::compareTo).get(); //5
        Integer min = list.stream().min(Integer::compareTo).get(); //1
    }

}
