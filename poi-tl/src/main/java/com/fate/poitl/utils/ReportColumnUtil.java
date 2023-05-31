package com.fate.poitl.utils;

import com.deepoove.poi.data.Numberings;
import com.deepoove.poi.data.Pictures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/30 18:42
 */
public class ReportColumnUtil {

    /**
     * 合并字段
     * @param key
     * @param value
     * @return
     */
    public static HashMap<String, Object> mergeText(String key, Object value) {
        return new HashMap<String, Object>() {{
            put(key, value);
        }};
    }

    /**
     * 列表
     * @param label
     * @param list
     * @return
     */
    public static HashMap<String, Object> createNumbering(String label, List<Object> list) {
        Numberings.NumberingBuilder builder = Numberings.ofBullet();
        list.forEach(text -> builder.addItem(String.valueOf(text)));
        return new HashMap<>() {
            {
                put(label, builder.create());
            }
        };
    }

    /**
     * 插入图片
     * @param label
     * @param file
     * @param width
     * @param height
     * @return
     * @throws FileNotFoundException
     */
    public static HashMap<String, Object> createPicture(String label, File file, int width, int height) throws FileNotFoundException {
        return new HashMap<>() {{
                put(label, Pictures.ofStream(new FileInputStream(file)).size(width, height).create());
            }};
    }

}
