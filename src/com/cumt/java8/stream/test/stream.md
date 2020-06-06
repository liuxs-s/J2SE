参考链接：https://blog.csdn.net/y_k_y/article/details/84633001
**stream介绍**
stream是java8中用来处理数组、集合的工具，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。
*惰性求值，流在中间处理过程中，只是对操作进行了记录，并不会立即执行，需要等到执行终止操作的时候才会进行实际的计算。
*不是数据结构，不会保存数据。

**stream的学习内容**
 * 1.创建stream流的几种方式
 * 2.stream流的中间操作
 * 3.stream流的终止操作

**stream流的创建方式**
1.使用Collection下的 stream() 和 parallelStream() 方法

**Comparator接口**
@FunctionalInterface
public interface Comparator<T> {
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
    int compare(T o1, T o2);
}
