package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayUtil {

    /**
     * 判断是否非空
     * @param array
     * @return
     */
    public static boolean isNotEmpty(Object[] array){
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 连接数组
     * @param array1
     * @param array2
     * @return
     */
    public static Object[] concat(Object[] array1,Object[] array2){
        return ArrayUtils.addAll(array1,array2);
    }

    /**
     * 判断对象是否在数组中
     * @param array
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> boolean contains(T[] array,T obj){
        return ArrayUtils.contains(array,obj);
    }


}
