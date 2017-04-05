package com.tool.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by fanweidong on 2017/4/5.
 */
public class FwdUtil {

    /**
     * 读文件
     * @param file
     * @param fileCharset
     * @return
     * @throws IOException
     */
    public static String readFile(File file, String fileCharset) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] fileByte = new byte[(int)file.length()];
        int readLen = 0;
        bufferedInputStream.read(fileByte);
        String fileData = new String(fileByte, fileCharset);
        bufferedInputStream.close();
        return fileData;
    }

    /**
     * 读文件
     * @param pathname
     * @param fileCharset
     * @return
     * @throws IOException
     */
    public static String readFile(String pathname, String fileCharset) throws IOException {
        File file = new File(pathname);
        return readFile(file, fileCharset);
    }

}
