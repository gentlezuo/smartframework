package org.smart4j.framework.ioc;

import org.smart4j.framework.aop.annotation.Aspect;
import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.core.fault.InitializationError;
import org.smart4j.framework.ioc.annotation.Bean;
import org.smart4j.framework.mvc.annotation.Action;
import org.smart4j.framework.tx.annotation.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化Bean类
 */
public class BeanHelper {

    private static final Map<Class<?>, Object> beanMap = new HashMap<>();

    static {

        try {
            //获取应用包路径下的所有类
            List<Class<?>> classList = ClassHelper.getClassList();
            for (Class<?> cls : classList) {
                //处理带有Bean/Service/Action/Aspect
                if (cls.isAnnotationPresent(Bean.class) ||
                        cls.isAnnotationPresent(Service.class) ||
                        cls.isAnnotationPresent(Action.class) ||
                        cls.isAnnotationPresent(Aspect.class)){
                    //创建bean实例并将其放入map中
                    Object beanInstance=cls.newInstance();
                    beanMap.put(cls,beanInstance);
                }
            }
        }catch (Exception e){
            throw new InitializationError("初始化BeanHelp出错",e);
        }

    }

    /**
     * 获取beanMap
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * 获取Bean实例
     *
     * @param cls
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("无法根据类名获取到实例" + cls);
        }
        return (T) beanMap.get(cls);
    }

    /**
     * 设置bean实例
     *
     * @param cls
     * @param obj
     */
    public static void setBean(Class<?> cls, Object obj) {
        beanMap.put(cls, obj);
    }
}
