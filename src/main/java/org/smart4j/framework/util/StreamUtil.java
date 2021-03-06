package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 流操作工具类
 */
public class StreamUtil {
    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    /**将输入流复制到输出流
     * @param inputStream
     * @param outputStream
     */
    public static void copyStream(InputStream inputStream, OutputStream outputStream){
        try{
            int length;
            byte[] buffer=new byte[4*1024];
            while ((length=inputStream.read(buffer,0,buffer.length))!=-1){
                outputStream.write(buffer,0,length);
            }
            outputStream.flush();
        }catch (Exception e){
            logger.error("复制流出错",e);
            throw new RuntimeException(e);
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            }catch (Exception e){
                logger.error("释放资源出错",e);
            }
        }
    }


    /**
     * 从流中获取字符串
     * @param is
     * @return
     */
    public static String getString(InputStream is){
        StringBuilder sb=new StringBuilder();
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
        }catch (Exception e){
            logger.error("Strean转String出错",e);
            throw new RuntimeException(e);
        }
        return sb.toString();

    }

}
