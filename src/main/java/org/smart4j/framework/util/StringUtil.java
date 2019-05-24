package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR=String.valueOf((char)29);

    /**
     * 判断字符串是否非空
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s){
        return StringUtils.isNotEmpty(s);
    }

    /**
     * 判断字符串是否为空
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        return StringUtils.isEmpty(s);
    }

    /**
     * 如果字符串为空就转为默认字符串
     * @param s
     * @param defaultValue
     * @return
     */
    public static String defaultIfEmpty(String s,String defaultValue){
        return StringUtils.defaultString(s,defaultValue);
    }

    /**
     * 替换字符串，待更改
     * @param s
     * @param regex
     * @param replacement
     * @return
     */
    public static String replaceAll(String s,String regex,String replacement){
        Pattern p= Pattern.compile(regex);
        Matcher m=p.matcher(s);
        StringBuffer sb=new StringBuffer();
        while (m.find()){
            m.appendReplacement(sb,replacement);
        }
        m.appendTail(sb);


        return sb.toString();
    }

    public static boolean isNumber(String s){
        return NumberUtils.isNumber(s);
    }

    /**
     * 是否为十进制（整数）
     * @param s
     * @return
     */
    public static boolean isDigits(String s){
        return NumberUtils.isDigits(s);
    }

    /**
     * 将驼峰命名转化为下划线风格
     * @param s
     * @return
     */
    public static String camelToUnderline(String s){
        Matcher matcher=Pattern.compile("[A-Z]").matcher(s);
        StringBuffer sb=new StringBuffer(s);
        for (int i = 0; matcher.find() ; i++) {
            sb.replace(matcher.start()+i,matcher.end()+i,"_"+matcher.group().toLowerCase());
        }
        //处理第一个字符
        if (sb.charAt(0)=='_'){
            sb.deleteCharAt(0);
        }
        return sb.toString();

    }

    /**
     * 将下划线转化为驼峰风格
     * @param s
     * @return
     */
    public static String underlineToCamel(String s){
        Matcher matcher=Pattern.compile("_[a-z]").matcher(s);
        StringBuilder sb=new StringBuilder(s);
        for (int i=0;matcher.find();i++){
            sb.replace(matcher.start()-i,matcher.end()-i,matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(sb.charAt(0))){
            sb.replace(0,1,String.valueOf(Character.toLowerCase(sb.charAt(0))));
        }
        return sb.toString();
    }

    /**
     * 分割字符串
     * @param s
     * @param separator
     * @return
     */
    public static String[] splitString(String s,String separator){
        return StringUtils.splitByWholeSeparator(s,separator);
    }

    /**
     * 首字母大写
     * @param s
     * @return
     */
    public static String firstToUpper(String s){
        return Character.toUpperCase(s.charAt(0))+s.substring(1);
    }


    /**
     * 将分割符转为下划线
     * @param s
     * @param sepertor
     * @return
     */
    public static String toUnderlineStyle(String s,String sepertor){
        s=s.trim().toLowerCase();
        if(s.contains(sepertor)){
            s=s.replace(sepertor,"_");
        }
        return s;
    }

    /**
     * 将分隔符去掉，再转为驼峰
     * @param s
     * @param seperator
     * @return
     */
    public static String toCamelStyle(String s,String seperator){
        return StringUtil.underlineToCamel(toUnderlineStyle(s,seperator));
    }

    /**
     * 转为帕斯卡命名（FooBar）
     * @param s
     * @param sepertor
     * @return
     */
    public static String toPascalStyle(String s,String sepertor){
        return StringUtil.firstToUpper(toCamelStyle(s,sepertor));
    }


    /**
     * 转为显示命名方式（如：Foo Bar）
     * @param s
     * @param seperator
     * @return
     */
    public static String toDisplayStyle(String s,String seperator){
        StringBuilder displayName= new StringBuilder();
        s=s.trim().toLowerCase();
        if(s.contains(seperator)){
            String[] words=StringUtil.splitString(s,seperator);
            for (String word:words){
                displayName.append(StringUtil.firstToUpper(word)).append(" ");
            }
            displayName = new StringBuilder(displayName.toString().trim());
        }else {
            displayName = new StringBuilder(StringUtil.firstToUpper(s));
        }

        return displayName.toString();
    }

}
