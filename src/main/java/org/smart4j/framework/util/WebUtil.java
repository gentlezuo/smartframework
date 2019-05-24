package org.smart4j.framework.util;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.FrameworkConstant;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Web操作工具类
 */
public class WebUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 将数据以json格式写入到响应头中
     * @param response
     * @param data
     */
    public static void writeJSON(HttpServletResponse response,Object data){
        try {
            //设置响应头
            //设置内容为json格式
            response.setContentType("application/json");
            response.setCharacterEncoding(FrameworkConstant.UTF_8);
            //向响应中写入数据
            PrintWriter writer=response.getWriter();
            //转为json字符串
            writer.write(JsonUtil.toJSON(data));
            writer.flush();
            writer.close();
        }catch (Exception e){
            logger.error("在响应中写入数据出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将数据以html格式写入响应中(在js中获取的是json字符串而不是json对象)
     * @param response
     * @param data
     */
    public static void writeHTML(HttpServletResponse response,Object data){
        try {
            //设置响应头
            //设置内容为html
            response.setContentType("text/html");
            response.setCharacterEncoding(FrameworkConstant.UTF_8);
            //向响应中写入数据
            Writer writer=response.getWriter();
            writer.write(JsonUtil.toJSON(data));
            writer.flush();
            writer.close();
        }catch (Exception e){
            logger.error("向响应中写入数据出错",e);
            throw new RuntimeException(e);
        }

    }


    /**
     * 从请求中获取所有参数（当参数名重复时，用后者覆盖前者）
     * @param request
     * @return
     */
    public static Map<String,Object> getRequestParamMap(HttpServletRequest request){
        Map<String,Object> paramMap=new LinkedHashMap<>();
        try {
            String method=request.getMethod();
            if (method.equalsIgnoreCase("put")||method.equalsIgnoreCase("delete")){
                String queryString=CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
                if (StringUtil.isNotEmpty(queryString)){
                    String[] qsArray=StringUtil.splitString(queryString,"&");
                    if (ArrayUtil.isNotEmpty(qsArray)){
                        for (String qs:qsArray){
                            String[] array=StringUtil.splitString(qs,"=");
                            if (ArrayUtil.isNotEmpty(array)&&array.length==2){
                                String paramName=array[0];
                                String paramValue=array[1];
                                if (checkParamName(paramName)){
                                    if (paramMap.containsKey(paramName)){
                                        paramValue=paramMap.get(paramName)+StringUtil.SEPARATOR+paramValue;
                                    }
                                    paramMap.put(paramName,paramValue);
                                }
                            }
                        }
                    }
                }
            }else {
                Enumeration<String> paramNames=request.getHeaderNames();
                while (paramNames.hasMoreElements()){
                    String paramName=paramNames.nextElement();
                    if (checkParamName(paramName)){
                        String[] paramValues=request.getParameterValues(paramName);
                        if (ArrayUtil.isNotEmpty(paramValues)){
                            if (paramValues.length==1){
                                paramMap.put(paramName,paramValues[0]);
                            }else {
                                StringBuilder paramValue=new StringBuilder();
                                for (int i = 0; i <paramValues.length ; i++) {
                                    paramValue.append(paramValues[i]);
                                    if (i!=paramValues.length-1){
                                        paramValue.append(StringUtil.SEPARATOR);
                                    }
                                }
                                paramMap.put(paramName,paramValue.toString());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("获取请求参数出错！",e);
            throw new RuntimeException(e);
        }
        return paramMap;
    }

    /**
     * 忽略jQuery缓存参数
     * @param paramName
     * @return
     */
    private static boolean checkParamName(String paramName){
        return !paramName.equals("_");
    }


    /**
     * 转发请求
     * @param path
     * @param request
     * @param response
     */
    public static void forwardRequest(String path,HttpServletRequest request,HttpServletResponse response){
        try {
            request.getRequestDispatcher(path).forward(request,response);
        }catch (Exception e){
            logger.error("转发请求出错",e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 重定向请求
     * @param path
     * @param request
     * @param response
     */
    public static void redirectRequest(String path,HttpServletRequest request,HttpServletResponse response){
        try {
            response.sendRedirect(request.getContextPath()+path);
        }catch (Exception e){
            logger.error("重定向请求出错",e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 发送错误代码
     */
    public static void sendError(int code, String message, HttpServletResponse response) {
        try {
            response.sendError(code, message);
        } catch (Exception e) {
            logger.error("发送错误代码出错！", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否为ajax请求
     * @param request
     * @return
     */
    public static boolean isAJAX(HttpServletRequest request){
        return request.getHeader("X-Requested-With")!=null;
    }

    /**
     * 获取请求路径
     * @param request
     * @return
     */
    public static String getRequestPath(HttpServletRequest request){
        String servletPath=request.getServletPath();
        String pathInfo=StringUtil.defaultIfEmpty(request.getPathInfo(),"");
        return servletPath+pathInfo;
    }

    /**
     * 从cookie中获取数据
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request,String name){
        String value="";
        try {
            Cookie[] cookieArray=request.getCookies();
            if (cookieArray!=null){
                for (Cookie cookie:cookieArray){
                    if (StringUtil.isNotEmpty(name)&&name.equals(cookie.getName())){
                        value=CodecUtil.decodeURL(cookie.getValue());
                        break;
                    }
                }
            }
        }catch (Exception e){
            logger.error("获取cookie出错",e);
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * 下在文件
     * @param response
     * @param filePath
     */
    public static void downloadFile(HttpServletResponse response,String filePath){
        try {
            String originalFileName= FilenameUtils.getName(filePath);
            String downloadedFileName=new String(originalFileName.getBytes("GBK"),"ISO8859_1");//防止中文乱码
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + downloadedFileName + "\"");
            InputStream inputStream=new BufferedInputStream(new FileInputStream(filePath));
            OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
            StreamUtil.copyStream(inputStream,outputStream);
        }catch (Exception e){
            logger.error("下载文件出错",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置Redirect Url 到Session中
     * @param request
     * @param sessinKey
     */
    public static void setRedirectUrl(HttpServletRequest request,String sessinKey){
        if (!isAJAX(request)){
            String requestPath=getRequestPath(request);
            request.getSession().setAttribute(sessinKey,requestPath);
        }
    }

    public static String createCaptcha(HttpServletResponse response){
        StringBuilder captcha=new StringBuilder();
        try {
            // 参数初始化
            int width = 60;                      // 验证码图片的宽度
            int height = 31;                     // 验证码图片的高度
            int codeCount = 4;                   // 验证码字符个数
            int codeX = width / (codeCount + 1); // 字符横向间距
            int codeY = height - 4;              // 字符纵向间距
            int fontHeight = height - 2;         // 字体高度
            int randomSeed = 36;                 // 随机数种子
            char[] codeSequence = {              // 验证码中可出现的字符
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E',
                    'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J',
                    'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O',
                    'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
                    'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y',
                    'z', 'Z'
            };

            //创建图像
            BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g=bi.createGraphics();
            //将图像填充为白色
            g.setColor(Color.WHITE);
            g.fillRect(0,0,width,height);
            //设置字体
            g.setFont(new Font("Courier New", Font.BOLD, fontHeight));
            //绘制边框
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width - 1, height - 1);
            // 产生随机干扰线（160条）
            g.setColor(Color.WHITE);
            // 创建随机数生成器
            Random random = new Random();
            for (int i = 0; i < 160; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
            // 生成随机验证码
            int red, green, blue;
            for (int i = 0; i < codeCount; i++) {
                // 获取随机验证码
                String validateCode = String.valueOf(codeSequence[random.nextInt(randomSeed)]);
                // 随机构造颜色值
                red = random.nextInt(255);
                green = random.nextInt(255);
                blue = random.nextInt(255);
                // 将带有颜色的验证码绘制到图像中
                g.setColor(new Color(red, green, blue));
                g.drawString(validateCode, (i + 1) * codeX - 6, codeY);
                // 将产生的随机数拼接起来
                captcha.append(validateCode);
            }
            // 禁止图像缓存
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            // 设置响应类型为 JPEG 图片
            response.setContentType("image/jpeg");
            // 将缓冲图像写到 Servlet 输出流中
            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write(bi, "jpeg", sos);
            sos.close();
        } catch (Exception e) {
            logger.error("创建验证码出错！", e);
            throw new RuntimeException(e);
        }
        return captcha.toString();
    }

    /**
     * 是否为 IE 浏览器
     */
    public boolean isIE(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        return agent != null && agent.contains("MSIE");
    }


}
