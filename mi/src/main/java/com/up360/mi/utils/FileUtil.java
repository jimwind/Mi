package com.up360.mi.utils;

import java.io.File;

public class FileUtil {
    /**
     * 创建目录
     *
     * @param folderName
     */
    public static File createDir(String folderName) {
        File dir = new File(folderName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
