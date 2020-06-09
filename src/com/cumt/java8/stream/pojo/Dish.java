package com.cumt.java8.stream.pojo;

/**
 * 描述：菜实体
 *
 * @author liuxs_s@163.com
 * @create 2020-06-07 11:51
 **/
public class Dish {

    /** 菜名 */
    private final String name;
    /** 是否是素食 */
    private final boolean vegetarian;
    /** 卡路里 */
    private final int calories;
    /** 菜的类型 */
    private final Type type;

    public enum Type {MEAT, FISH, OTHER}

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}
