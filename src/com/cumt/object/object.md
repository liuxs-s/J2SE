**Object类**
Java中的Object对象是所有对象的父类
        Object类中有几个native方法，native方法底层实现不是java，而是C。
        Object类中还有一些例如equals（）方法、toString（）方法
        notify()、notifyAll()、wait()都是与多线程相关的方法。
        Object类的描述是这样的：
        Object是所有类的root，每一个类都有一个superclass，所有的对象，包括数组都实现了Object类中的方法。
        `Class {@code Object} is the root of the class hierarchy.
         Every class has {@code Object} as a superclass. All objects,including arrays, 
         implement the methods of this class.`
        
        `public class Object {
             private static native void registerNatives();
             static {
                 registerNatives();
             }
             public final native Class<?> getClass();
             
             public native int hashCode();
             
             public boolean equals(Object obj) {
                 return (this == obj);
             }
             
             protected native Object clone() throws CloneNotSupportedException;
             
             public String toString() {
                 return getClass().getName() + "@" + Integer.toHexString(hashCode());
             }
             
             public final native void notify();
             
             public final native void notifyAll();
             
             public final native void wait(long timeout) throws InterruptedException;
              
             public final void wait(long timeout, int nanos) throws InterruptedException {
                 if (timeout < 0) {
                     throw new IllegalArgumentException("timeout value is negative");
                 }
                 if (nanos < 0 || nanos > 999999) {
                     throw new IllegalArgumentException(
                                         "nanosecond timeout value out of range");
                 }
                 if (nanos > 0) {
                     timeout++;
                 }
                 wait(timeout);
             }   
             public final void wait() throws InterruptedException {
                 wait(0);
             }
             protected void finalize() throws Throwable { }
         }`
**Java中的==与equals的区别**
==	
对于基本的数据类型来说，==比较的就是两个数据值是否相等
对于引用类型数据来说，==比较的是两个对象是否指向堆或方法区（非栈中）同一个对象实例。
equals：
equals（）方法属于Object对象中的方法，在Object对象中，equals（）的实现实际上就是==
`public boolean equals(Object obj) {
     return (this == obj);
 }`
对于引用类型的数据来说，要想让equals（）比较的是数据值是否相等，就必须重写Object中的方法equals（），String中就重写了
equals（）方法。String中的equals（）其实是先判断是否指向的是同一个对象。
`public boolean equals(Object anObject) {
     if (this == anObject) {
         return true;
     }
     if (anObject instanceof String) {
         String anotherString = (String)anObject;
         int n = value.length;
         if (n == anotherString.value.length) {
             char v1[] = value;
             char v2[] = anotherString.value;
             int i = 0;
             while (n-- != 0) {
                 if (v1[i] != v2[i])
                     return false;
                 i++;
             }
             return true;
         }
     }
     return false;
 }`
**Java中的hashcode（）与equals（）的关系**
从Integer的hashcode（）与equals（）和String的hashcode（）和equals（），可以看出，
equals相等，只有可能有两种情况，一种是指向的是同一个对象，指向同一对象时，hashcode值一定相等；另外一种情况是value值相等，
可以看出hashcode就是根据value计算出来的，所以value值相等，hashcode值也一定相同。
因此，equals（）相等，那么hashcode值一定相等，所以一般重写Object中的equals（）方法，通常需要重写hashCode方法从而
保持对象行为的一致性。
但是两个value值不同的字符串根据hashcode的计算规则，也是有可能相同的，但是此时equals（）一定不相等，因为是不同value的字符串。
因此，hashcode值相同，但是equals（）不一定相等。
Integer的hashcode（）和equals（）的计算
`public static int hashCode(int value) {
     return value;
 }
 @Override
 public int hashCode() {
     return Integer.hashCode(value);
 }
 public boolean equals(Object obj) {
     if (obj instanceof Integer) {
         return value == ((Integer)obj).intValue();
     }
     return false;
 }`
String的hashcode（）和equals（）的计算
`public boolean equals(Object anObject) {
     if (this == anObject) {
         return true;
     }
     if (anObject instanceof String) {
         String anotherString = (String)anObject;
         int n = value.length;
         if (n == anotherString.value.length) {
             char v1[] = value;
             char v2[] = anotherString.value;
             int i = 0;
             while (n-- != 0) {
                 if (v1[i] != v2[i])
                     return false;
                 i++;
             }
             return true;
         }
     }
     return false;
 }
 public int hashCode() {
     int h = hash;
     if (h == 0 && value.length > 0) {
         char val[] = value;
 
         for (int i = 0; i < value.length; i++) {
             h = 31 * h + val[i];
         }
         hash = h;
     }
     return h;
 }`
 
 

