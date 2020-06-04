package com.cumt.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 描述：Arrays对象中asList()方法
 *
 * @author liuxs_s@163.com
 * @create 2020-06-04 22:20
 **/
public class aslist {

    public static void main(String[] args) {
        int[] array = {6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14};

        //注意这种写法 asList方法很容易采坑
        List<int[]> list = Arrays.asList(new int[]{6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14});
        List<int[]> list1 = Arrays.asList(array);
    }
}
