package com.cumt.java8.optional;

import com.cumt.java8.stream.pojo.Apple;
import com.cumt.java8.stream.pojo.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 描述：Optional 语法糖
 *
 * @author liuxs_s@163.com
 * @create 2020-06-04 22:54
 **/
public class OptionalTest {
    /**
     * 既然空指针异常在实际项目中是大量存在的，所以说现在有很多编程语言对空指针异常做了一定程序的规避，而规避的办法都是
     * 采用这次要学的Optional方式
     *
     * 1.Optional代表了一个容器对象，它里面的值可能为空，也可能不为空
     * 2.如果里面的值存在的话，isPresent()方法就会返回true，并且get()就会返回该值
     * 3.
     *
     */
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        /*
        public static <T> Optional<T> ofNullable(T value) {
                return value == null ? empty() : of(value);
        }
        public static <T> Optional<T> of(T value) {
            return new Optional<>(value);
        }
          */

//        public static<T> Optional<T> empty() {
//            @SuppressWarnings("unchecked")
//            Optional<T> t = (Optional<T>) EMPTY;
//            return t;
//        }
//        private static final Optional<?> EMPTY = new Optional<>();

//        private Optional(T value) {
//            this.value = Objects.requireNonNull(value);
//        }
//        public static <T> T requireNonNull(T obj) {
//            if (obj == null)
//                throw new NullPointerException();
//            return obj;
//        }


        Optional<List<Dish>> dishList = Optional.of(menu);
        Optional<List<Dish>> dishList1 = Optional.ofNullable(menu);


//        public T get() {
//            if (value == null) {
//                throw new NoSuchElementException("No value present");
//            }
//            return value;
//        }
//        public boolean isPresent() {
//            return value != null;
//        }

        if(dishList.isPresent()){
            List<Dish> dishes = dishList.get();
        }
        List<Dish> dishes = dishList.get();


        Apple apple = null;
        Apple apple1 = new Apple();
        Optional.ofNullable(apple).orElse(apple1);
        Optional.ofNullable(apple).orElseGet(Apple::new);
        //Optional 还定义了 orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值：
        Optional.ofNullable(apple).orElseThrow(() ->new RuntimeException());


//        User user = new User("anna@gmail.com", "1234");
//        String email = Optional.ofNullable(user)
//                .map(u -> u.getEmail()).orElse("default@gmail.com");


    }

}
