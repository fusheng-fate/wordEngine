package com.fate.poitl.utils;


/**
 * @author lgb
 * @Description
 * @Date 2023/5/19 17:31
 */
public class PathUtils {

    public static String getWordTemplateImageUrl(String fileName) {
        return "src/main/resources/wordTemplate/image/" + fileName;
    }

    public static String getWordTemplateTextUrl(String fileName) {
        return "src/main/resources/wordTemplate/text/" + fileName;
    }

}
