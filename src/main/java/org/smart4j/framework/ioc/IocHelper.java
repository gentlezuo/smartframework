package org.smart4j.framework.ioc;

import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.core.fault.InitializationError;
import org.smart4j.framework.ioc.annotation.Impl;
import org.smart4j.framework.ioc.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 初始化容器
 */
public class IocHelper {
    static {
        try {
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //获取bean类与bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取除了父类的所有字段
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    //判断当前bean是否带有Inject注解
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            //获取Bean字段对应的接口
                            Class<?> interfaceClass = beanField.getType();
                            //获取bean字段对应的实现类
                            Class<?> implementClass = findImplementClass(interfaceClass);
                            //若存在实现类,则执行以下
                            if (implementClass != null) {
                                // 从 Bean Map 中获取该实现类对应的实现类实例
                                Object implementInstance = beanMap.get(implementClass);
                                if (implementInstance != null) {
                                    beanField.setAccessible(true);
                                    beanField.set(beanInstance, implementInstance);
                                } else
                                    throw new InitializationError("依赖注入失败,类名" + beanClass.getName());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            throw new InitializationError("初始化IOCHelper出错",e);
        }
    }


    public static Class<?> findImplementClass(Class<?> interfaceClass){
        //先赋值，为自己
        Class<?> implementClass=interfaceClass;
        //判断接口上是否标注了Impl注解
        if (interfaceClass.isAnnotationPresent(Impl.class)){
            implementClass=interfaceClass.getAnnotation(Impl.class).value();

        }else {
            //获取该接口所有的实现类
            List<Class<?>> implementClassList= ClassHelper.getClassListBySuper(interfaceClass);
            if (CollectionUtil.isNotEmpty(implementClassList)){
                //获取第一个实现类
                implementClass=implementClassList.get(0);
            }
        }
        return implementClass;
    }


}
