package com.cumt.classloader.test;

import com.cumt.classloader.SClassLoader;

import java.lang.reflect.Array;
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
        String path = "D:/classlib";
        //全包名
        String packageNamePath = "com.cumt.classloader.test.Demo";


        try {
            //使用自定义类加载器加载class文件，并返回Class对象
            //自定义类加载器的加载路径
            SClassLoader sClassLoader=new SClassLoader(path);
            //包名+类名
            Class c = sClassLoader.loadClass(packageNamePath);

            if(c!=null){
                Object obj=c.newInstance();
                Method method=c.getMethod("say", null);
                method.invoke(obj, null);
                System.out.println(c.getClassLoader().toString());
            }
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
        } finally {
        }



    }
}
