package com.cumt.string;

/**
 * 描述：枚举类
 * 1.多个枚举类之间用,隔开
 * 2.Ali编码要求：
 * 常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
 * 正例：MAX_STOCK_COUNT / CACHE_EXPIRED_TIME
 * 反例：MAX_COUNT / EXPIRED_TIME
 * 枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。
 * 说明：枚举其实就是特殊的类，域成员均为常量，且构造方法被默认强制是私有。
 * 正例：枚举名字为 ProcessStatusEnum 的成员名称：SUCCESS / UNKNOWN_REASON。
 *
 * @author liuxs_s@163.com
 * @create 2020-06-01 21:50
 **/
public enum WeekDayEnum {
    SUNDAY(0,"星期天"),MONDAY(1,"星期一");

    private int index;
    private String cName;


    WeekDayEnum(int index, String cName) {
        this.index = index;
        this.cName = cName;
    }

/*    SUCCESS(200,"成功"),
    UNKNOW_ERROR(201,"未知异常");

    private Integer code;
    private String msg;

    WeekDayEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }*/
}
