package com.cumt.string;

/**
 * 描述：枚举类
 * 1.多个枚举类之间用,隔开
 * 2.Ali编码要求：
 *
 * @author liuxs_s@163.com
 * @create 2020-06-01 21:50
 **/
public enum WeekDayEnum {
//    SUNDAY(0,"星期天");
//
//    private int index;
//    private String cName;
//
//
//    WeekDayEnum(int index, String cName) {
//        this.index = index;
//        this.cName = cName;
//    }

    SUCCESS(200,"成功"),
    UNKNOW_ERROR(201,"未知异常");

    private Integer code;
    private String msg;

    WeekDayEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
