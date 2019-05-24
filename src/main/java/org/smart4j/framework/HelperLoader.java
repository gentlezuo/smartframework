package org.smart4j.framework;

import org.smart4j.framework.aop.AopHelper;
import org.smart4j.framework.dao.DatabaseHelper;
import org.smart4j.framework.ioc.BeanHelper;
import org.smart4j.framework.ioc.IocHelper;
import org.smart4j.framework.mvc.ActionHelper;
import org.smart4j.framework.orm.EntityHelper;
import org.smart4j.framework.util.ClassUtil;

public final class HelperLoader {
    public static void init(){

        //定义需要加载的Help类
        Class<?>[] classList = {
                DatabaseHelper.class,
                EntityHelper.class,
                ActionHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class
        };
        //按照顺序加载
        for (Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
