package org.smart4j.framework.core;

import org.smart4j.framework.InstanceFactory;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 根据条件获取相关类的类
 */
public class ClassHelper {
    /**
     * 获取配置文件（smartframe.properties）中基础包名
     */
    private static final String basePackage=ConfigHelper.getString("smart.framework.app.base_package");

    private static final ClassScanner classScanner= InstanceFactory.getClassScanner();

    /**
     * 获取基础包名中的所有类
     * @return
     */
    public static List<Class<?>> getClassList(){
        return classScanner.getClassList(basePackage);
    }

    /**
     * 获取基础包中指定父类获取接口的类
     * @param superClass
     * @return
     */
    public static List<Class<?>> getClassListBySuper(Class<?> superClass){
        return classScanner.getClassListBySuper(basePackage,superClass);
    }

    /**
     * 获取基础包中指定注解的相关类
     * @param annotationClass
     * @return
     */
    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass){
        return classScanner.getClassListByAnnotation(basePackage,annotationClass);
    }

}
