**lambda表达式**
list.sort(Comparator.comparing(String::length));
public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor);
里面的参数是一个函数式接口，所以可以用lanmbda表达式;

list.forEach(System.out::println);
default void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    for (T t : this) {
        action.accept(t);
    }
}
Consumer 也是一个函数式接口，所以foreach()可以用lambda表达式

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}

**方法引用**

所谓方法引用，是指如果某个方法签名和接口恰好一致，就可以直接传入方法引用。
方法签名一致也就是：除了方法名外，方法参数（个数，类型）一致，返回类型相同，我们说两者的方法签名一致
也就可以直接把方法名作为Lambda表达式传入：
方法签名只看参数类型和返回类型，不看方法名称，也不看类的继承关系。
因为lambda表达式说到底是为了代替函数式接口中的抽象方法，正好现在有一个和抽象方法一样的方法，就可以传进去
list.sort(String::compareTo);

default void sort(Comparator<? super E> c) {
    Object[] a = this.toArray();
    Arrays.sort(a, (Comparator) c);
    ListIterator<E> i = this.listIterator();
    for (Object e : a) {
        i.next();
        i.set((E) e);
    }
}

@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
}

String 中的方法
public int compareTo(String anotherString) {
        int len1 = value.length;
        int len2 = anotherString.value.length;
        int lim = Math.min(len1, len2);
        char v1[] = value;
        char v2[] = anotherString.value;
        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
}
使用方法：
    1.必须满足参数是函数式接口
    2.类名::方法名 是用于静态方法
    3.实例对象::实例方法
    4.特殊的方法签名和接口恰好一致情况：因为实例方法有一个隐含的this参数，String类的compareTo()方法在实际调用的时候，
     第一个隐含参数总是传入this，相当于静态方法：public static int compareTo(this, String o);
    
