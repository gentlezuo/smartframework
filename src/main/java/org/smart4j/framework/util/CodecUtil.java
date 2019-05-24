package org.smart4j.framework.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.FrameworkConstant;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

public class CodecUtil {

    private static final Logger logger= LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 进行urlencode  编码
     * @param str
     * @return
     */
    public static String encodeURL(String str){
        String target;
        try{
            target= URLEncoder.encode(str, FrameworkConstant.UTF_8);
        }catch (Exception e){
            logger.error("编码出错",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将url解码
     * @param str
     * @return
     */
    public static String decodeURL(String str){
        String target;
        try {
            target= URLDecoder.decode(str,FrameworkConstant.UTF_8);
        }catch (Exception e){
            logger.error("解码出错",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串base64编码
     * @param str
     * @return
     */
    public static String encodeBASE64(String str){
        String target;
        try{
            target= Base64.encodeBase64URLSafeString(str.getBytes(FrameworkConstant.UTF_8));

        }catch (UnsupportedEncodingException e){
            logger.error("编码出错",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * md5加密
     * @param str
     * @return
     */
    public static String encryptMD5(String str){
        return DigestUtils.md5Hex(str);
    }

    /**
     * SHA加密
     * @param str
     * @return
     */
    public static String encryptSHA(String str){
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 创建随机数
     * @param count
     * @return
     */
    public static String createRandom(int count){
        //UID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取UUID(32位)
     * @return
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }




}
