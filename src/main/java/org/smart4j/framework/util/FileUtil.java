package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.smart4j.framework.FrameworkConstant;

import java.io.*;

/**
 * 文件操作工具类
 */
public class FileUtil {
    private static final Logger logger= LoggerFactory.getLogger(FileUtil.class);

    /**
     * 创建目录
     * @param dirPath
     * @return
     */
    public static File createDir(String dirPath){
        File dir;
        try{
            dir=new File(dirPath);
            if (!dir.exists()){
                FileUtils.forceMkdir(dir);
            }
        }catch (Exception e){
            logger.error("创建目录出错",e);
            throw new RuntimeException(e);
        }
        return dir;
    }

    /**
     * 创建文件
     * @param filePath
     * @return
     */
    public static File createFile(String filePath){
        File file;
        try{
            file=new File(filePath);
            File parentDir=file.getParentFile();
            if (!parentDir.exists()){
                FileUtils.forceMkdir(parentDir);
            }
        }catch (Exception e){
            logger.error("创建文件出错",e);
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 复制目录，忽略空目录
     * @param srcPath
     * @param destPath
     */
    public static void copy(String srcPath,String destPath){
        try{
            File srcDir=new File(srcPath);
            File destDir=new File(destPath);
            if (srcDir.exists()&&srcDir.isDirectory()){
                FileUtils.copyDirectoryToDirectory(srcDir,destDir);
            }
        }catch (Exception e){
            logger.error("复制目录出错",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 复制文件
     * @param srcPath
     * @param destPath
     */
    public static void copyFile(String srcPath,String destPath){
        try {
            File srcFile=new File(srcPath);
            File destDir=new File(destPath);
            if (srcFile.exists()&&srcFile.isFile()){
                FileUtils.copyFileToDirectory(srcFile,destDir);
            }
        }catch (Exception e){
            logger.error("复制文件出错",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除文件夹
     * @param dirPath
     */
    public static void deleteDir(String dirPath){
        try{
            File dir =new File(dirPath);
            if (dir.exists()&&dir.isDirectory()){
                FileUtils.deleteDirectory(dir);
            }
        }catch (Exception e ){
            logger.error("删除目录出错");
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                FileUtils.forceDelete(file);
            }
        } catch (Exception e) {
            logger.error("删除文件出错！", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 重命名文件
     * @param srcPath
     * @param destPaht
     */
    public static void renameFile(String srcPath,String destPaht){
        File srcFile=new File(srcPath);
        if (srcFile.exists()){
            File newFile=new File(destPaht);
            boolean result=srcFile.renameTo(newFile);
            if (!result){
                throw new RuntimeException("重命名文件失败"+newFile);
            }
        }
    }

    public static void writeFile(String filePath,String fileContent){
        OutputStream os=null;
        Writer w=null;
        try {
            FileUtil.createFile(filePath);
            os=new BufferedOutputStream(new FileOutputStream(filePath));
            w=new OutputStreamWriter(os, FrameworkConstant.UTF_8);
            w.write(fileContent);
            w.flush();
        }catch (Exception e ){
            logger.error("写入文件出错",e);
            throw new RuntimeException(e);
        }finally {
            try{
                if (os!=null){
                    os.close();
                }
                if (w!=null){
                    w.close();
                }
            }catch (Exception e){
                logger.error("释放资源出错",e);
            }
        }
    }

    /**
     * 获取真实文件名
     * @param fileName
     * @return
     */
    public static String getRealFileName(String fileName){
        return FilenameUtils.getName(fileName);
    }

    /**
     * 检查文件是否存在
     * @param filePath
     * @return
     */
    public static boolean checkFileExists(String filePath){
        return new File(filePath).exists();
    }
}
