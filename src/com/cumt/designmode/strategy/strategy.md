**策略模式**
策略模式的用意是针对一组算法，将每一个算法都封装到具有共同接口的独立类中，从而使得它们可以相互替换。


**策略模式的结构**
1、抽象策略的公共的接口或者抽象类
这是一个抽象角色，通常由一个接口或抽象类实现，此角色给出所有具体策略类所需的接口。
2、具体策略实现
包装了每个具体实现的相关算法。
3、策略的使用
持有一个策略Strategy接口或者抽象类的引用。


**策略模式实际应用场景-容错恢复机制**
容错恢复机制是应用程序开发中非常常见的功能。那么什么是容错恢复呢？简单点说就是：程序运行的时候，正常情况下应该按照某种方式来做，
出现错误后，不但能容忍程序运行出现错误，还提供出现错误后的备用方案，也就是恢复机制，来代替正常执行的功能，使程序继续向下运行。

比如在一个系统中，所有对系统的操作都要有日志记录，而且这个日志还需要有管理界面，这种情况下通常会把日志记录在数据库里面，方便后续的管理，
但是在记录日志到数据库的时候，可能会发生错误，比如暂时连不上数据库了，那就先记录在文件里面，然后在合适的时候把文件中的记录再转录到数据库中。

对于这样的功能的设计，就可以采用策略模式，把日志记录到数据库和日志记录到文件当作两种记录日志的策略，然后在运行期间根据需要进行动态的切换。

1.定义日志策略接口
public interface LogStrategy {
    public void log(String msg);
}
2.实现日志策略接口
public class DbLog implements LogStrategy{
    public void log(String msg) {
        System.out.println("现在把 '"+msg+"' 记录到数据库中");
    }
}
public class FileLog implements LogStrategy{
    public void log(String msg) {
        System.out.println("现在把 '"+msg+"' 记录到日志文件中");
    }
}
3.接下来定义使用这些策略的上下文，注意这次是在上下文里面实现具体策略算法的选择，所以不需要客户端来指定具体的策略算法了
public class LogContext {
    public void log(String msg) {
        LogStrategy strategy = new DbLog();
        try {
            strategy .log(msg);
        } catch(Exception e) {
             // 出错，记录到文件
             strategy = new FileLog();
             strategy.log(msg);
        }
    }
}
4.小结
通过上面的示例，会看到策略模式的一种简单应用，也顺便了解一下基本的容错恢复机制的设计和实现。在实际的应用中，需要设计容错恢复
的系统一般要求都比较高，应用也会比较复杂，但是基本的思路是差不多的。


**Java中的策略模式-Comparator接口**
Collections里面有一个sort方法，因为集合里面的元素有可能是复合对象，复合对象并不像基本数据类型，可以根据大小排序，复合对象怎么排序呢？基于
这个问题考虑，Java要求如果定义的复合对象要有排序的功能，就自行实现Comparable接口或Comparator接口，看一下sort带Comparator的重载方法：

public static <T> void sort(List<T> list, Comparator<? super T> c) {
    Object[] a = list.toArray();
    Arrays.sort(a, (Comparator)c);
    ListIterator i = list.listIterator();
    for (int j=0; j<a.length; j++) {
        i.next();
        i.set(a[j]);
    }
}

public static <T> void sort(T[] a, Comparator<? super T> c) {
    T[] aux = (T[])a.clone();
        if (c==null)
            mergeSort(aux, a, 0, a.length, 0);
        else
            mergeSort(aux, a, 0, a.length, 0, c);
}

private static void mergeSort(Object[] src,
                  Object[] dest,
                  int low, int high, int off,
                  Comparator c) {
    int length = high - low;

    // Insertion sort on smallest arrays
    if (length < INSERTIONSORT_THRESHOLD) {
        for (int i=low; i<high; i++)
        for (int j=i; j>low && c.compare(dest[j-1], dest[j])>0; j--)
            swap(dest, j, j-1);
        return;
    }

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        low  += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off, c);
        mergeSort(dest, src, mid, high, -off, c);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid-1], src[mid]) <= 0) {
           System.arraycopy(src, low, dest, destLow, length);
           return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
}
第10行，根据Comparator接口实现类的compare方法的返回结果决定是否要swap（交换）。
这就是策略模式，我们可以给Collections的sort方法传入不同的Comparator的实现类作为不同的比较策略。
不同的比较策略，对同一个集合，可能会产生不同的排序结果。

**策略模式优缺点**
优点
1、避免了多重条件if...else if...else语句，多重条件语句并不容易维护。
2、策略模式提供了管理相关算法簇的办法，恰当使用继承可以把公共代码移到父类，从而避免了代码重复。

缺点
1、客户端必须知道所有的策略类，并自行决定使用 哪一个策略，这意味着客户端必须理解这些算法的区别，以便选择恰当的算法。
2、如果备选策略很多，对象的数据会很多。
