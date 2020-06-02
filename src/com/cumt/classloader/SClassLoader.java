package com.cumt.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 描述：自定义类加载器
 *
 * 1.属性：必须包含指定的类加载路径
 * 2.重写findClass方法
 *
 * @author liuxs_s@163.com
 * @create 2020-06-01 20:03
 **/
public class SClassLoader extends ClassLoader {

    /**
     * 指定路径
     */
    private String path;


    public SClassLoader(String classPath) {
        path = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class log = null;
        // 1.获取该class文件字节码数组
        byte[] classData = getData();

        if (classData != null) {
            // 2.将class的字节码数组转换成Class类的实例
            System.out.println("byte:"+classData);
            log = defineClass(name, classData, 0, classData.length);
        }
        return log;

    }


    private byte[] getData() {

        File file = new File(path);
        if (file.exists()) {
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            return out.toByteArray();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "SClassLoader{" +
                "path='" + path + '\'' +
                '}';
    }
}
