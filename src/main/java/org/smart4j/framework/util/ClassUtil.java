package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * 类操作工具类
 */
public class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取class路径
     * @return
     */
    public static String getClassPath(){
        String classpath="";
        URL resource=getClassLoader().getResource("");
        if(resource!=null){
            classpath=resource.getPath();
        }
        return classpath;
    }

    /**
     * 加载类，并自动初始化
     * @param className
     * @return
     */
    public static Class<?> loadClass(String className){
        return loadClass(className,true);
    }

    public static Class<?> loadClass(String className,boolean isInitialized){
        Class<?> cls;
        try {
            //System.out.println(className);
            cls=Class.forName(className,isInitialized,getClassLoader());
        }catch (ClassNotFoundException e){
            logger.error("类加载出错",e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 判断是否为int或Integer
     * @param type
     * @return
     */
    public static boolean isInt(Class<?> type){
        return type.equals(int.class)||type.equals(Integer.class);
    }

    public static boolean isLong(Class<?> type) {
        return type.equals(long.class) || type.equals(Long.class);
    }

    public static boolean isDouble(Class<?> type) {
        return type.equals(double.class) || type.equals(Double.class);
    }

    public static boolean isString(Class<?> type) {
        return type.equals(String.class);
    }

    public static void main(String[] args) {
        System.out.println(ClassUtil.getClassLoader().getResource(""));//file:/home/zzx/IdeaProjects/smartframework/target/classes/
        System.out.println(ClassUtil.getClassPath());///home/zzx/IdeaProjects/smartframework/target/classes/
        System.out.println(ClassUtil.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(getClassPath());///home/zzx/IdeaProjects/smartframework/target/classes/
    }

}
