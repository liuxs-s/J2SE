package com.cumt.java8.stream.pojo;

/**
 * 描述：Apple类
 *
 * @author liuxs_s@163.com
 * @create 2020-06-05 23:19
 **/
public class Apple {

    private String color;

    private Double weight;

    public Apple(String color, Double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
