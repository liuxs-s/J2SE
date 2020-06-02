package com.cumt.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述：测试类
 *
 * @author liuxs_s@163.com
 * @create 2020-06-01 20:11
 **/
public class SClassLoaderTest {
    /**
     * 类加载器：
     *  1.指明加载哪个路径下的class文件，这是自定义类加载器必须的
     *  2.sClassLoader.loadClass(packageNamePath);这个方法用于加载确定的类
     * @param args
     */
    public static void main(String[] args) {
        //class的路径
        String path = "D:/lib/Demo.class";
        //全包名
        String packageNamePath = "com.cumt.classloader.test.Demo";


        try {
            //使用自定义类加载器加载class文件，并返回Class对象
            //自定义类加载器的加载路径
            SClassLoader sClassLoader=new SClassLoader(path);

            //加载Log这个class文件
            //Class<?> demo = sClassLoader.loadClass(packageNamePath);  //主要是这里用错了，没有用到自己写的加载
            //Class<?> demo = sClassLoader.findClass(packageNamePath);   //这个也行
            Class<?> demo = sClassLoader.loadData(packageNamePath);
            System.out.println("类加载器是:" + demo.getClassLoader());

            //利用反射获取main方法
            Method method = demo.getDeclaredMethod("main", String[].class);
            Object object = demo.newInstance();
            String[] arg = {"ad"};
            method.invoke(object, (Object) arg);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("----------------Book----------------");
        System.out.println(Book.class.getClassLoader());
        System.out.println(Book.class.getClassLoader().getParent());
        System.out.println(Book.class.getClassLoader().getParent().getParent());
    }
}
class Book{

}
