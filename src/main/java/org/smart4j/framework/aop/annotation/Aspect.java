package org.smart4j.framework.aop.annotation;

import java.lang.annotation.*;

/**
 * 定义切面类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 包名
     */
    String pkg() default "";

    /**
     * 类名
     */
    String cls() default "";

    Class<? extends Annotation> annotation() default Aspect.class;
}
