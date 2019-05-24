package org.smart4j.framework.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * 集合操作工具类
 */
public class CollectionUtil {

    /**
     * 判断集合是否非空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return CollectionUtils.isNotEmpty(collection);
    }

    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

}
