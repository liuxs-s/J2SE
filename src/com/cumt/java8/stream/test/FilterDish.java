package com.cumt.java8.stream.test;

import com.cumt.java8.stream.pojo.Dish;

import java.util.*;

/**
 * 描述：以菜引出stream流操作
 *
 * @author liuxs_s@163.com
 * @create 2020-06-07 11:52
 **/
public class FilterDish {
    //想从一个菜集合中拿到低于400卡路里的所有菜，并且以卡路里进行排序，而且最终只需要拿到满足条件的菜名，而非菜的实体

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
        //用传统的方式来实现
        List<String> collections = getDishNamesByCollections(menu);
        collections.forEach(System.out::println);

        //采用stream方式实现
        menu.stream()
                .filter(dish -> dish.getCalories()<400)
                .sorted((o1,o2)-> o1.getCalories()-o2.getCalories())
                .map(Dish::getName)   //这个？？
                .forEach(System.out::println);
    }

    /**
     * 用传统的方式来实现
     * @param menu
     * @return
     */
    private static List<String> getDishNamesByCollections(List<Dish> menu) {
        ArrayList<Dish> arrayList = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        for(Dish dish:menu){
            if(dish.getCalories() < 400){
                arrayList.add(dish);
            }
        }
        //Collections.sort(arrayList, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));
        arrayList.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {

                return o1.getCalories()-o2.getCalories();  //o1-02就是升序，o2-o1就是降序，排序这的代码
            }
        });
        for(Dish dish:arrayList){
           result.add(dish.getName());
        }
        return result;
    }
}
