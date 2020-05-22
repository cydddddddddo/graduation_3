package com.example.graduation_3.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 将文件重命名并存放至指定路径
 * 返回最终名称
 * @author Cy
 * @data 2020/5/14 - 21:43
 */
public class UploadUtil {

    public static String uploadFile(MultipartFile file,String path,Long userId) throws IOException {
        String name = file.getOriginalFilename();
        String suffixName = name.substring(name.lastIndexOf("."));
        String indexName = ""+userId + "imageface";
        String fileName = indexName + suffixName;
        File tempFile = new File(path,fileName);
        if (tempFile.exists()){
            tempFile.delete();
        }
        tempFile.createNewFile();
        file.transferTo(tempFile);
        return tempFile.getName();
    }
}
