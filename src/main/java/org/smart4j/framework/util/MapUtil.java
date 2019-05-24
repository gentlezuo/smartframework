package org.smart4j.framework.util;


import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * map操作类
 */
public class MapUtil {
    private static final Logger logger= LoggerFactory.getLogger(MapUtil.class);



    /**
     * 判断是否为空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?,?> map ){
        return MapUtils.isNotEmpty(map);
    }

    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    /**
     * 转置Map
     * @param source
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map<V,K> invert(Map<K,V> source){
        Map<V,K> target=null;
        if (isNotEmpty(source)){
            target=new LinkedHashMap<V, K>(source.size());
            for (Map.Entry<K,V> entry:source.entrySet()){
                target.put(entry.getValue(),entry.getKey());
            }
        }
        return target;
    }
}
