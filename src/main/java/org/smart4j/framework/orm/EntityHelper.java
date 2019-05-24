package org.smart4j.framework.orm;

import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.orm.annotation.Column;
import org.smart4j.framework.orm.annotation.Entity;
import org.smart4j.framework.orm.annotation.Table;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.MapUtil;
import org.smart4j.framework.util.StringUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化Entity
 */
public class EntityHelper {

    /**
     * 实体类->类名
     */
    private static final Map<Class<?>,String> entityClassTableNameMap=new HashMap<>();

    /**
     * 实体类->（字段名->列名）
     */
    private static final Map<Class<?>,Map<String,String>> entityClassFieldMapMap=new HashMap<>();
    static {
        //获取并遍历所有实体类
        List<Class<?>> entityClassList = ClassHelper.getClassListByAnnotation(Entity.class);
        for (Class<?> entityClass:entityClassList){
            initEntityNameMap(entityClass);
            initEntityFieldMapMap(entityClass);
        }

    }


    private static void initEntityNameMap(Class<?> entityClass){
        //判断该实体类上是否有Table注解
        String tableName;
        if (entityClass.isAnnotationPresent(Table.class)) {
            //若已存在，使用注解定义的列名
            tableName=entityClass.getAnnotation(Table.class).value();
        }else {
            //若不存在，将实体类名转化为下划线风格的表明
            tableName= StringUtil.camelToUnderline(entityClass.getSimpleName());
        }
        entityClassTableNameMap.put(entityClass,tableName);
    }



    private static void initEntityFieldMapMap(Class<?> entityClass){
        //获取并遍历该实体类的所有的字段（不包括父类的方法）
        Field[] fields=entityClass.getDeclaredFields();
        if (ArrayUtil.isNotEmpty(fields)){
            //创建一个fieldMap 存放列名与字段名的映射关系
            Map<String,String> fieldMap=new HashMap<>();

            for (Field field: fields){
                String fieldName=field.getName();
                String columnName;
                if (field.isAnnotationPresent(Column.class)){
                    //使用注解的列名
                    columnName=field.getAnnotation(Column.class).value();

                }else {
                    columnName=StringUtil.camelToUnderline(fieldName);
                }
                fieldMap.put(fieldName,columnName);
            }
            entityClassFieldMapMap.put(entityClass,fieldMap);

        }

    }

    /**
     * 根据类名获取列名
     */
    public static String getTableNmae(Class<?> entityClass){
        return entityClassTableNameMap.get(entityClass);
    }

    /**
     * 根据类名获取fieldMap field->col
     * @param entityClass
     * @return
     */
    public static Map<String,String> getFieldMap(Class<?> entityClass){
        return entityClassFieldMapMap.get(entityClass);
    }

    /**
     * col->field
     * @param entityClass
     * @return
     */
    public static Map<String,String> getColumnMap(Class<?> entityClass){
        return MapUtil.invert(getFieldMap(entityClass));
    }

    /**
     * 获取对应field的col名
     * @param entityClass
     * @param fieldName
     * @return
     */
    public static String getColumnName(Class<?> entityClass,String fieldName){
        String columnName=getFieldMap(entityClass).get(fieldName);
        return StringUtil.isNotEmpty(columnName) ? columnName:fieldName;
    }
}
