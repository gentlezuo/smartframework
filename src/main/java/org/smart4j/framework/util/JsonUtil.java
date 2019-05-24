package org.smart4j.framework.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 操作json的工具类
 */
public class JsonUtil {
    private static final Logger logger= LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper=new ObjectMapper();

    /**
     * java对象转json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJSON(T obj){
        String jsonStr;
        try{
            jsonStr=objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            logger.error("java转json出错",e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * json转java对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
        public static <T> T fromJSON(String json,Class<T> type){
        T obj;
        try{
            obj=objectMapper.readValue(json,type);
        }catch (Exception e ){
            logger.error("json转java出错",e);
            throw new RuntimeException(e);
        }
        return obj;
    }
}
