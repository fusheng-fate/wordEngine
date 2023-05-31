package com.fate.poitl.test;

import com.alibaba.fastjson2.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import com.fate.poitl.utils.PathUtils;
import com.fate.poitl.utils.ReportChartUtil;
import com.fate.poitl.utils.ReportColumnUtil;
import com.fate.poitl.utils.ReportTableUtil;
import org.apache.commons.collections4.KeyValue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/30 17:01
 */
@SpringBootTest
public class TestUtils {

    @Test
    public void testText() throws IOException {
        KeyValue<String, Object> title = ReportColumnUtil.mergeText("title", "Hi, poi-tl Word模板引擎");
        XWPFTemplate template = XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx")).render(
               new HashMap<>() {{
                   put(title.getKey(), title.getValue());
               }});
        template.writeAndClose(new FileOutputStream("target/output.docx"));
    }

    @Test
    public void testTable() throws IOException {
        KeyValue<String, Object> table = ReportTableUtil.createTable("table1", List.of("姓名", "年龄", "职业"), new ArrayList<>() {{
            List<Object> objects = List.of("张三", 18, "工人");
            add(objects);
            add(objects);
        }});
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("test_table.docx")).render(
           new HashMap<>() {{
               put(table.getKey(), table.getValue());
           }}
        ).writeToFile("target//output_test_table.docx");
    }

    @Test
    public void testChart() throws IOException {
        KeyValue<String, Object> pieChart = ReportChartUtil.createPieChart("pieChart", "测试饼图", new HashMap<>() {{
            put("a", 10);
            put("b", 12.0);
            put("c", 16);
        }});
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).render(
            new HashMap<>() {{
                put(pieChart.getKey(), pieChart.getValue());
            }}
        ).writeToFile("target/output_chartDemo.docx");
    }

    @Test
    public void testColumnChart() throws IOException {
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
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).
                render(new HashMap<>() {{
                    put(columnChart.getKey(), columnChart.getValue());
                }}).writeToFile("target/output_chartDemo.docx");
    }
}
