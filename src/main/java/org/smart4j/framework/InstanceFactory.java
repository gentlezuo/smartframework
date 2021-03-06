package org.smart4j.framework;

import org.smart4j.framework.core.ClassScanner;
import org.smart4j.framework.core.ConfigHelper;
import org.smart4j.framework.core.impl.DefaultClassScanner;
import org.smart4j.framework.dao.DataAccessor;
import org.smart4j.framework.dao.impl.DefaultDataAccessor;
import org.smart4j.framework.ds.DataSourceFactory;
import org.smart4j.framework.ds.impl.DefaultDataSourceFactory;
import org.smart4j.framework.mvc.HandlerExceptionResolver;
import org.smart4j.framework.mvc.HandlerInvoker;
import org.smart4j.framework.mvc.HandlerMapping;
import org.smart4j.framework.mvc.ViewResolver;
import org.smart4j.framework.mvc.impl.DefaultHandlerExceptionResolver;
import org.smart4j.framework.mvc.impl.DefaultHandlerInvoker;
import org.smart4j.framework.mvc.impl.DefaultHandlerMapping;
import org.smart4j.framework.mvc.impl.DefaultViewResolver;
import org.smart4j.framework.util.ObjectUtil;
import org.smart4j.framework.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实例工厂
 */
public class InstanceFactory {

    /**
     * 用于缓存对应的实例
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    /**
     * ClassScanner
     */
    private static final String CLASS_SCANNER = "smart.framework.custom.class_scanner";

    /**
     * DataSourceFactory
     */
    private static final String DS_FACTORY = "smart.framework.custom.ds_factory";

    /**
     * DataAccessor
     */
    private static final String DATA_ACCESSOR = "smart.framework.custom.data_accessor";

    /**
     * HandlerMapping
     */
    private static final String HANDLER_MAPPING = "smart.framework.custom.handler_mapping";

    /**
     * HandlerInvoker
     */
    private static final String HANDLER_INVOKER = "smart.framework.custom.handler_invoker";

    /**
     * HandlerExceptionResolver
     */
    private static final String HANDLER_EXCEPTION_RESOLVER = "smart.framework.custom.handler_exception_resolver";

    /**
     * ViewResolver
     */
    private static final String VIEW_RESOLVER = "smart.framework.custom.view_resolver";






    /**
     * get ClassScanner
     * @return
     */
    public static ClassScanner getClassScanner(){
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    /**
     * get DataSourceFactory
     * @return
     */
    public static DataSourceFactory getDataSourceFactory(){
        return getInstance(DS_FACTORY, DefaultDataSourceFactory.class);
    }

    /**
     * 获取 DataAccessor
     */
    public static DataAccessor getDataAccessor() {
        return getInstance(DATA_ACCESSOR, DefaultDataAccessor.class);
    }


    /**
     * 获取 HandlerMapping
     */
    public static HandlerMapping getHandlerMapping() {
        return getInstance(HANDLER_MAPPING, DefaultHandlerMapping.class);
    }

    /**
     * 获取 HandlerInvoker
     */
    public static HandlerInvoker getHandlerInvoker() {
        return getInstance(HANDLER_INVOKER, DefaultHandlerInvoker.class);
    }

    /**
     * 获取 HandlerExceptionResolver
     */
    public static HandlerExceptionResolver getHandlerExceptionResolver() {
        return getInstance(HANDLER_EXCEPTION_RESOLVER, DefaultHandlerExceptionResolver.class);
    }

    /**
     * 获取 ViewResolver
     */
    public static ViewResolver getViewResolver() {
        return getInstance(VIEW_RESOLVER, DefaultViewResolver.class);
    }




    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String cacheKey,Class<T> defaultImplClass){
        //若缓存中有对应的实例
        if (cache.containsKey(cacheKey)){
            return (T) cache.get(cacheKey);
        }
        //从配置文件中获取形影的接口实现类配置
        String implClassName=ConfigHelper.getString(cacheKey);
        //若配置中不存在，使用默认实现类
        if (StringUtil.isEmpty(implClassName)){
            implClassName=defaultImplClass.getName();
        }
        //通过反射创建该类的实例
        T instance=ObjectUtil.newInstance(implClassName);
        if (instance!=null){
            cache.put(cacheKey,instance);
        }
        return instance;
    }





















}
