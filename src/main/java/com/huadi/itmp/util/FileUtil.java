package com.huadi.itmp.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @program: HomeSchoolCommunication
 * @description:
 * @author: Mr.Zhang
 * @create: 2021-08-28 16:34
 **/
public class FileUtil {
    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
        return filePath+fileName;
    }

    /**
     * 上传文件，并返回图片的路径
     * @param file
     * @param filePath
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String uploadFileReturnCourseUrl(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
        // 这个路径是浏览器可以访问到的地址，而不是生成文件的filePath
        filePath = "/school/images/course/";
        return filePath+fileName;
    }
}
