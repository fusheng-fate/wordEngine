package com.fate.poitl.utils;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/19 17:31
 */
public class PathUtils {

    public static String getWordTemplateImageUrl(String fileName) throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:wordTemplate/image/" + fileName).getAbsolutePath();
    }

    public static String getWordTemplateTextUrl(String fileName) throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:wordTemplate/text/" + fileName).getAbsolutePath();
    }

}
