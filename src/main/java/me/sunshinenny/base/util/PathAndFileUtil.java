package me.sunshinenny.base.util;

import java.io.File;

/**
 * 文件相关操作
 *
 * @author sunshinenny
 * @create 2019-10-24 8:53 上午
 */
public class PathAndFileUtil {
    static {
        File uploadFilesDir = new File(getUploadFilesPath());
        // 判断文件路径是否存在
        if (!uploadFilesDir.exists()) {
            // 不存在创建新的文件
            uploadFilesDir.mkdirs();
        }
        File exportFilesDir = new File(getExportFilesPath());
        if (!exportFilesDir.exists()) {
            // 不存在创建新的文件
            exportFilesDir.mkdirs();
        }
    }

    static public String getResourcesPath() {
        return System.getProperty("user.dir") + "/src/main/resources/";
    }

    static public String getStaticPath() {
        return System.getProperty("user.dir") + "/src/main/resources/static/";
    }

    static public String getUploadFilesPath() {
        return System.getProperty("user.dir") + "/src/main/resources/static/uploadFiles/";
    }

    static public String getExportFilesPath() {
        return System.getProperty("user.dir") + "/src/main/resources/static/exportFiles/";
    }

    static public String getFileFormatSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

