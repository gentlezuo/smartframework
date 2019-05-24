package org.smart4j.framework.mvc;

import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.mvc.annotation.Action;
import org.smart4j.framework.mvc.annotation.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.StringUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化Action配置
 */
public class ActionHelper {

    /**
     * Action Map（Http 请求与Action方法映射）
     */
    private static final Map<Requester,Handler> actionMap =new LinkedHashMap<>();
    static {
        //获取所有Action类
        List<Class<?>> actionClassList= ClassHelper.getClassListByAnnotation(Action.class);
        if (CollectionUtil.isNotEmpty(actionClassList)){
            //定义两个Action Map
            //存放普通的Action
            Map<Requester,Handler> commonActionMap=new HashMap<>();
            //存放带有正则表达式的Action
            Map<Requester,Handler> regexpActionMap=new HashMap<>();
            //遍历Action类
            for (Class<?> actionClass:actionClassList){
                //获取并遍历该Action类中所有的方法
                Method[] actionMethods=actionClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(actionMethods)){
                    for (Method actionMethod:actionMethods){
                        //处理Action方法
                        handleActionMethod(actionClass,actionMethod,commonActionMap,regexpActionMap);
                    }
                }
            }
            //初始化最终的Action Map（将 Common 放在 Regexp 前面）
            actionMap.putAll(commonActionMap);
            actionMap.putAll(regexpActionMap);
        }
    }


    private static void handleActionMethod(Class<?> actionClass, Method actionMethod, Map<Requester, Handler> commonActionMap, Map<Requester, Handler> regexpActionMap) {
        // 判断当前 Action 方法是否带有 Request 注解
        if (actionMethod.isAnnotationPresent(Request.Get.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Get.class).value();
            putActionMap("GET", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Post.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Post.class).value();
            putActionMap("POST", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Put.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Put.class).value();
            putActionMap("PUT", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Delete.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Delete.class).value();
            putActionMap("DELETE", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        }
    }

    private static void putActionMap(String requestMethod,String requestPath,Class<?> actionClass,Method actionMethod,Map<Requester,Handler> commonActionMap,Map<Requester,Handler> regexpActionMap ){
        // 判断 Request Path 中是否带有占位符
        if (requestPath.matches(".+\\{\\w+\\}.*")) {
            // 将请求路径中的占位符 {\w+} 转换为正则表达式 (\\w+)
            requestPath = StringUtil.replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
            // 将 Requester 与 Handler 放入 Regexp Action Map 中
            regexpActionMap.put(new Requester(requestMethod, requestPath), new Handler(actionClass, actionMethod));
        } else {
            // 将 Requester 与 Handler 放入 Common Action Map 中
            commonActionMap.put(new Requester(requestMethod, requestPath), new Handler(actionClass, actionMethod));
        }
    }
    /**
     * 获取Action Map
     */
    public static Map<Requester,Handler> getActionMap(){
        return actionMap;
    }

}
