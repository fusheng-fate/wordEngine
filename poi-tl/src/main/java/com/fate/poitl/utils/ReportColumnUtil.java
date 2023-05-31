package com.fate.poitl.utils;

import com.deepoove.poi.data.*;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/30 18:42
 */
public class ReportColumnUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReportColumnUtil.class);

    /**
     * 合并字段
     * @param key
     * @param value
     * @return
     */
    public static KeyValue<String, Object> mergeText(String key, Object value) {
       return new DefaultKeyValue<>(key, value);
    }

    /**
     * 合并段落 段落插件不是默认插件 需手动绑定
     * @param key
     * @param paragraph
     * @param charCount 缩进字符个数 为null时默认制表符 \t
     * @param isNewLine 是否换行
     * @return
     */
    public static KeyValue<String, Object> mergeParagraph(String key, String paragraph, Integer charCount, boolean isNewLine) {
        StringBuilder builder = new StringBuilder("");
        if (charCount != null) {
            builder.append(" " .repeat(Math.max(0, charCount)));
        } else {
           builder.append("\t");
        }
        builder.append(paragraph);
        if (isNewLine) {
            builder.append("\n");
        }
        return new DefaultKeyValue<>(key, Paragraphs.of().addText(builder.toString()).create());
    }

    /**
     *
     * @param key
     * @param paragraph
     * @return
     */
    public static KeyValue<String, Object> mergeParagraph(String key, String paragraph) {
       return mergeParagraph(key, paragraph, null, true);
    }

    /**
     * 插入超链接
     * @param key 标签
     * @param text 链接文字
     * @param link 链接地址
     * @return
     */
    public static KeyValue<String, Object> mergeHyLinkText(String key, String text, String link) {
        return new DefaultKeyValue<>(key, Paragraphs.of().addText(Texts.of(text).link(link).create()));
    }

    /**
     * 列表
     * @param label
     * @param list
     * @return
     */
    public static KeyValue<String, Object> createNumbering(String label, List<Object> list) {
      return createNumbering(label, list, NumberingFormat.BULLET);
    }

    /**
     * 列表
     * @param label
     * @param list
     * @param format 列表样式
     * @return
     */
    public static KeyValue<String, Object> createNumbering(String label, List<Object> list, NumberingFormat format) {
        Numberings.NumberingBuilder builder = Numberings.of(format);
        list.forEach(text -> builder.addItem(String.valueOf(text)));
        return new DefaultKeyValue<>(label, builder.create());
    }

    /**
     * 插入图片
     * @param label
     * @param file
     * @param width
     * @param height
     * @return
     */

    public static KeyValue<String, Object> createPicture(String label, File file, int width, int height) {
        try {
            return new DefaultKeyValue<>(label, Pictures.ofStream(new FileInputStream(file)).size(width, height).create());
        } catch (FileNotFoundException | NullPointerException e) {
            logger.error("image file is not exist or type is not file, [{}]", e.getMessage());
            return new DefaultKeyValue<>(label, "");
        }
    }

}
