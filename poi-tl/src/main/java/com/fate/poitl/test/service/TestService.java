package com.fate.poitl.test.service;

import com.alibaba.fastjson2.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.ParagraphRenderPolicy;
import com.fate.poitl.utils.ReportChartUtil;
import com.fate.poitl.utils.ReportColumnUtil;
import com.fate.poitl.utils.ReportTableUtil;
import org.apache.commons.collections4.KeyValue;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author lgb
 * @Description
 * @Date 2023/6/5 11:21
 */
@Service
public class TestService {


    /**
     * 测试 linux环境
     * @return
     */
    public XWPFTemplate generateTestWord() throws IOException {
        Configure config = Configure.builder()
                .bind("styleParagraph", new ParagraphRenderPolicy()).build();

        //data
        HashMap<String, Object> map = new HashMap<>() {{
            //文本
            KeyValue<String, Object> keyValue1 = ReportColumnUtil.mergeText("title", "张三");
            put(keyValue1.getKey(), keyValue1.getValue());
            KeyValue<String, Object> keyValue2 = ReportColumnUtil.mergeText("title", "李四");
            put(keyValue2.getKey(), keyValue2.getValue());
            //段落
            KeyValue<String, Object> styleParagraph1 = ReportColumnUtil.mergeParagraph("styleParagraph", "测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。" +
                    "测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。");
            KeyValue<String, Object> styleParagraph2 = ReportColumnUtil.mergeParagraph("styleParagraph", "测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。" +
                    "测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。");
            put(styleParagraph1.getKey(), styleParagraph1.getValue());
            put(styleParagraph2.getKey(), styleParagraph2.getValue());

            //表格
            KeyValue<String, Object> table = ReportTableUtil.createTable("table1", List.of("姓名", "年龄", "职业"), new ArrayList<>() {{
                List<Object> objects = List.of("张三", 18, "工人");
                add(objects);
                add(objects);
            }});
            put(table.getKey(), table.getValue());

            //图片
            KeyValue<String, Object> watermelon = ReportColumnUtil.createPictureByUrl("watermelon", "http://deepoove.com/images/icecream.png", 300, 180);
            KeyValue<String, Object> lemon = ReportColumnUtil.createPictureByUrl("lemon", "http://deepoove.com/images/icecream.png", 24, 24);
            put(watermelon.getKey(), watermelon.getValue());
            put(lemon.getKey(), lemon.getValue());

            //列表
            List<Object> list = List.of("Plug-in grammar",
                    "Supports word text, pictures, table...",
                    "Template, not just template, but also style template", 18);
            KeyValue<String, Object> list1 = ReportColumnUtil.createNumbering("list", list);
            put(list1.getKey(), list1.getValue());

            //图表

            //饼图
            KeyValue<String, Object> pieChart = ReportChartUtil.createPieChart("pieChart", "测试饼图", new HashMap<>() {{
                put("a", 10);
                put("b", 12.0);
                put("c", 16);
            }});
            put(pieChart.getKey(), pieChart.getValue());

            //柱状图
            String[] strings = new String[] { "a","b","c","d","e" };
            LinkedHashMap<String, JSONObject> map = new LinkedHashMap<>();
            JSONObject object = new JSONObject();
            map.put("一区", object);
            object.put("a", 10);
            object.put("b", 10);
            object.put("c", 8);
            object.put("d", 10);
            object.put("e", 5);
            map.put("二区", object);
            KeyValue<String, Object> columnChart = ReportChartUtil.createColumnChart("columnBarChart", "测试柱状图", strings, map);
            put(columnChart.getKey(), columnChart.getValue());
        }};
        ClassPathResource resource = new ClassPathResource("/wordTemplate/text/test_all.docx");
        return XWPFTemplate.compile(resource.getInputStream(), config).render(map);
    }
}
