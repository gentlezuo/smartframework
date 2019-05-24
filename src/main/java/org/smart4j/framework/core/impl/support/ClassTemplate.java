package org.smart4j.framework.core.impl.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.ClassUtil;
import org.smart4j.framework.util.StringUtil;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;

/**
 * 用于获取类的模板类
 */
public abstract class ClassTemplate {

    public static final Logger logger= LoggerFactory.getLogger(ClassTemplate.class);

    protected final String packageName;
    protected ClassTemplate(String packageName){
        this.packageName=packageName;
    }

    public final List<Class<?>> getClassList(){
        List<Class<?>> classList=new ArrayList<>();
        try {
            //从包名获取URL类型的资源
            Enumeration<URL> urls= ClassUtil.getClassLoader().getResources(packageName.replace(".","/"));

            //遍历URL资源
            while (urls.hasMoreElements()){
                URL url=urls.nextElement();
                if (url!=null){
                    //获取协议名（分为file和jar）
                    String protocol=url.getProtocol();
                    if (protocol.equals("file")){
                        //若在class目录中，则执行添加类操作
                        String packagePath=url.getPath().replace("%20"," ");
                        addClass(classList,packagePath,packageName);
                    }else if (protocol.equals("jar")){
                        //若在jar中,则解析jar中的entry
                        JarURLConnection jarURLConnection=(JarURLConnection) url.openConnection();
                        JarFile jarFile=jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries=jarFile.entries();
                        while (jarEntries.hasMoreElements()){
                            JarEntry jarEntry=jarEntries.nextElement();
                            String jarEntryName=jarEntry.getName();
                            //判断该entry是否为class
                            if (jarEntryName.endsWith(".class")){
                                //获取类名
                                String className=jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                //添加类操作
                                doAddClass(classList,className);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("获取类错误",e);

        }
        return classList;
    }


    public void addClass(List<Class<?>> classList,String packagePath,String packageName){
        try {
            File[] files=new File(packagePath).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return (file.isFile()&&file.getName().endsWith(".class"))||file.isDirectory();
                }
            });

            //便利文件和目录

            for (File file:files){
                String fileName=file.getName();
                //根据文件或目录操作
                if (file.isFile()){
                    String className=fileName.substring(0,fileName.lastIndexOf("."));
                    if (StringUtil.isNotEmpty(packageName)){
                        className=packageName+"."+className;
                    }
                    //执行添加类操作
                    doAddClass(classList,className);
                }else {
                    //获取子包
                    String subPackagePath=fileName;
                    if (StringUtil.isNotEmpty(packagePath)){
                        subPackagePath=packagePath+"/"+subPackagePath;
                    }
                    //子包名
                    String subPackageName=fileName;
                    if (StringUtil.isNotEmpty(packageName)){
                        subPackageName=packageName+"."+subPackageName;
                    }
                    //递归调用
                    addClass(classList,subPackagePath,subPackageName);
                }
            }
        }catch (Exception e){
            logger.error("添加类出错",e);
        }
    }

    private void doAddClass(List<Class<?>> classList,String className){
        //加载类
        Class<?> cls=ClassUtil.loadClass(className,false);
        //判断是否可以添加类
        if (checkAddClass(cls)){
            //添加类
            classList.add(cls);
        }
    }

    public abstract boolean checkAddClass(Class<?> cls);

}
